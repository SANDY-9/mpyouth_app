package go.kr.mapo.mapoyouth.network.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.response.DonationListResponse
import go.kr.mapo.mapoyouth.util.STARTING_PAGE_INDEX
import java.lang.Exception

class DonationDataSource(
    private val mapoYouthService: MapoYouthService
    ): PagingSource<Int, DonationListResponse.Data.Content>() {

    override fun getRefreshKey(state: PagingState<Int, DonationListResponse.Data.Content>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
    
    //NULL CHECK 다시
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DonationListResponse.Data.Content> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val data = mapoYouthService.getDonationList(page).body()?.data
            LoadResult.Page(
                data = data!!.content,
                prevKey = if(page == STARTING_PAGE_INDEX ) null else page-1,
                nextKey = if(data.last) null else page+1
            )
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}