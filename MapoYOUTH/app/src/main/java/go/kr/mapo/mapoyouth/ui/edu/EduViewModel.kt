package go.kr.mapo.mapoyouth.ui.edu

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.repository.EduDataSource
import go.kr.mapo.mapoyouth.network.repository.EduRepository
import go.kr.mapo.mapoyouth.network.repository.YouthDataSource
import go.kr.mapo.mapoyouth.network.response.EduDetails
import go.kr.mapo.mapoyouth.network.response.YouthDetails
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
class EduViewModel @Inject constructor(
    private val mapoYouthService: MapoYouthService,
    private val eduRepository: EduRepository) : ViewModel() {

    val eduList = Pager(PagingConfig(PAGE_SIZE, prefetchDistance = 1, maxSize = 100)) {
        EduDataSource(mapoYouthService, null)
    }.liveData.cachedIn(viewModelScope)

    private val _eduDetails = MutableLiveData<EduDetails>()
    val eduDetails: LiveData<EduDetails> = _eduDetails

    private val _state = MutableLiveData(false)
    val state : LiveData<Boolean> = _state

    fun setEduDetails(id: Int) {
        _state.value = false
        viewModelScope.launch {
            eduRepository.getVolunteerDetails(id)?.let {
                _eduDetails.value = it.value
                _state.value = true
            }
        }
    }

    private val keyword = MutableLiveData("")

    val eduSearchResult = keyword.switchMap {
        Pager(PagingConfig(PAGE_SIZE, maxSize = 100)) {
            EduDataSource(mapoYouthService, it)
        }.liveData.cachedIn(viewModelScope)
    }

    fun requestSearchEdu(keyword: String){
        this.keyword.value = keyword
    }

}