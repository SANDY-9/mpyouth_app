package go.kr.mapo.mapoyouth.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.response.Volunteer
import go.kr.mapo.mapoyouth.network.response.VolunteerDetails
import go.kr.mapo.mapoyouth.network.response.YouthDetails
import go.kr.mapo.mapoyouth.util.STARTING_PAGE_INDEX
import java.lang.Exception

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-10-12
 * @desc
 */
class VolunteerDataSource(
    private val mapoYouthService: MapoYouthService,
    private val keyword: String?) : PagingSource<Int, Volunteer>() {

    private val _downloadedVolunteerDetails = MutableLiveData<VolunteerDetails>()
    val downloadedVolunteerDetails : LiveData<VolunteerDetails> = _downloadedVolunteerDetails

    override fun getRefreshKey(state: PagingState<Int, Volunteer>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Volunteer> {
        return try {
            val page = params.key ?: STARTING_PAGE_INDEX
            val data = mapoYouthService.getVolunteerList(page).body()?.data
            if(data != null) {
                LoadResult.Page(
                    data = data.content,
                    prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                    nextKey = if (data.last) null else page + 1
                )
            } else {
                LoadResult.Invalid()
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    suspend fun fetchVolunteerDetails(id: Int) {
        val response = mapoYouthService.getVolunteerDetails(id)
        if(response.isSuccessful) _downloadedVolunteerDetails.value = response.body()!!.data
    }
}