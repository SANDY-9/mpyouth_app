package go.kr.mapo.mapoyouth.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.response.DonationDetailsResponse
import go.kr.mapo.mapoyouth.network.response.DonationListResponse
import go.kr.mapo.mapoyouth.util.STARTING_PAGE_INDEX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.Exception

typealias LatestDonation = List<DonationListResponse.Data.Content>

class DonationDataSource(
    private val mapoYouthService: MapoYouthService,
    private val keyword: String?): PagingSource<Int, DonationListResponse.Data.Content>() {

    private val _downloadedDonationDetails = MutableLiveData<DonationDetailsResponse.Data>()
    val downloadedDonationDetails : LiveData<DonationDetailsResponse.Data> = _downloadedDonationDetails

    private val _downloadedLatestDonation = MutableLiveData<LatestDonation>()
    val downloadedLatestDonation : LiveData<LatestDonation> = _downloadedLatestDonation

    override fun getRefreshKey(state: PagingState<Int, DonationListResponse.Data.Content>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
    
    //NULL CHECK 다시
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DonationListResponse.Data.Content> {
        return try {
            val page = params.key ?: STARTING_PAGE_INDEX
            val data = 
                if (keyword != null) {
                    mapoYouthService.searchDonation(keyword).body()?.data
                } else {
                    mapoYouthService.getDonationList(page).body()?.data
                }
            LoadResult.Page(
                data = data!!.content,
                prevKey = if(page == STARTING_PAGE_INDEX ) null else page-1,
                nextKey = if(data.last) null else page+1
            )
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    suspend fun fetchDonationDetails(id:Int) {
        val response = mapoYouthService.getDonationDetails(id)
        if(response.isSuccessful) {
            _downloadedDonationDetails.value = response.body()!!.data
        }
    }

    suspend fun fetchLatestDonation() {
        val response = mapoYouthService.getDonationList(STARTING_PAGE_INDEX)
        CoroutineScope(Dispatchers.Main).launch {
            if(response.isSuccessful) _downloadedLatestDonation.value = response.body()!!.data.content
            cancel()
        }
    }
}