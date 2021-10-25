package go.kr.mapo.mapoyouth.ui.volunteer

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.repository.VolunteerDataSource
import go.kr.mapo.mapoyouth.network.repository.VolunteerRepository
import go.kr.mapo.mapoyouth.network.response.VolunteerDetails
import go.kr.mapo.mapoyouth.util.PAGE_SIZE
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-14
 * @desc
 */

@HiltViewModel
class VolunteerViewModel @Inject constructor(
    val mapoYouthService: MapoYouthService,
    val volunteerRepository: VolunteerRepository) : ViewModel() {

    val volunteerList = Pager(PagingConfig(PAGE_SIZE, 1, maxSize = 100)) {
        VolunteerDataSource(mapoYouthService, null)
    }.liveData.cachedIn(viewModelScope)

    private val _volunteerDetails = MutableLiveData<VolunteerDetails>()
    val volunteerDetails : LiveData<VolunteerDetails> = _volunteerDetails

    private val _state = MutableLiveData(false)
    val state : LiveData<Boolean> = _state

    fun setVolunteerDetails(id : Int) {
        _state.value = false
        viewModelScope.launch {
            volunteerRepository.getVolunteerDetails(id).also {
                _volunteerDetails.value = it.value
                _state.value = true
            }
        }
    }

    private val keyword = MutableLiveData("")
    val volunteerSearchResult = keyword.switchMap {
        Pager(PagingConfig(PAGE_SIZE, maxSize = 100)) {
            VolunteerDataSource(mapoYouthService, it)
        }.liveData.cachedIn(viewModelScope)
    }

    fun requestSearchVolunteer(keyword: String) {
        this.keyword.value = keyword
    }



}