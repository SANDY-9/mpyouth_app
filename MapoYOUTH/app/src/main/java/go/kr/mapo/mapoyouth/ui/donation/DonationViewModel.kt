package go.kr.mapo.mapoyouth.ui.donation

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.repository.DonationDataSource
import go.kr.mapo.mapoyouth.network.repository.DonationRepository
import go.kr.mapo.mapoyouth.network.response.DonationDetailsResponse
import go.kr.mapo.mapoyouth.util.PAGE_SIZE
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DonationViewModel @Inject constructor(
    private val mapoYouthService: MapoYouthService,
    private val donationRepository: DonationRepository): ViewModel() {

    val donationList = Pager(PagingConfig(PAGE_SIZE, prefetchDistance = 1, maxSize = 100)) {
        DonationDataSource(mapoYouthService, null)
    }.liveData.cachedIn(viewModelScope)

    private val _donationDetails = MutableLiveData<DonationDetailsResponse.Data>()
    val donationDetails : LiveData<DonationDetailsResponse.Data> = _donationDetails

    private val _state = MutableLiveData(false)
    val state : LiveData<Boolean> = _state

    fun setDonationDetails(id:Int) {
        _state.value = false
        viewModelScope.launch {
            donationRepository.getDonationDetails(id).let {
                _donationDetails.value = it.value
                _state.value = true
            }
        }
    }

    private val keyword = MutableLiveData("")

    val donationSearchResult = keyword.switchMap {
        Pager(PagingConfig(PAGE_SIZE, maxSize = 100)) {
            DonationDataSource(mapoYouthService, it)
        }.liveData.cachedIn(viewModelScope)
    }

    fun requestSearchDonation(keyword : String){
        this.keyword.value = keyword
    }
}