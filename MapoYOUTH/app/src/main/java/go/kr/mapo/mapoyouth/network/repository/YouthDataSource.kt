package go.kr.mapo.mapoyouth.network.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.response.Youth
import go.kr.mapo.mapoyouth.network.response.YouthDetails
import go.kr.mapo.mapoyouth.util.STARTING_PAGE_INDEX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Singleton

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-10-01
 * @desc
 */

class YouthDataSource(private val mapoYouthService: MapoYouthService) : PagingSource<Int, Youth>() {

    private val _downloadedYouthDetails = MutableLiveData<YouthDetails>()
    val downloadedYouthDetails : LiveData<YouthDetails> = _downloadedYouthDetails

    override fun getRefreshKey(state: PagingState<Int, Youth>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Youth> {
        return try {
            val page = params.key ?: STARTING_PAGE_INDEX
            val data = mapoYouthService.getYouthList(page).body()?.data
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

    suspend fun fetchYouthDetails(id : Int) {
        val response = mapoYouthService.getYouthDetails(id)
        if(response.isSuccessful) _downloadedYouthDetails.value = response.body()!!.data
    }

}