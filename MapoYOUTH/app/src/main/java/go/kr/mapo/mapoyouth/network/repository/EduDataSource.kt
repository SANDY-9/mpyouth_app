package go.kr.mapo.mapoyouth.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.response.Edu
import go.kr.mapo.mapoyouth.network.response.EduDetails
import go.kr.mapo.mapoyouth.util.STARTING_PAGE_INDEX
import java.lang.Exception

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-10-13
 * @desc
 */
class EduDataSource (
    private val mapoYouthService: MapoYouthService,
    private val keyword: String?
    ) : PagingSource<Int, Edu>() {

    private val _downloadedEduDetails = MutableLiveData<EduDetails>()
    val downloadedEduDetails : LiveData<EduDetails> = _downloadedEduDetails

    override fun getRefreshKey(state: PagingState<Int, Edu>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Edu> {
        return try {
            val page = params.key ?: STARTING_PAGE_INDEX
            val data = mapoYouthService.getEduList(page).body()?.data
            if(data != null) {
                LoadResult.Page(
                    data.content,
                    if(page == STARTING_PAGE_INDEX) null else page - 1,
                    if(data.last) null else page + 1
                )
            } else {
                LoadResult.Invalid()
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    suspend fun fetchEduDetails(id: Int) {
        val response = mapoYouthService.getEduDetails(id)
        if(response.isSuccessful) _downloadedEduDetails.value = response.body()!!.data
    }

}