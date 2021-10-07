package go.kr.mapo.mapoyouth.ui.youth

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.repository.YouthDataSource
import go.kr.mapo.mapoyouth.network.repository.YouthRepository
import go.kr.mapo.mapoyouth.network.response.Organization
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
class YouthViewModel @Inject constructor(
    private val mapoYouthService: MapoYouthService,
    private val youthRepository: YouthRepository) : ViewModel() {

    val youthList = Pager(PagingConfig(PAGE_SIZE, prefetchDistance = 1)) {
        YouthDataSource(mapoYouthService)
    }.liveData.cachedIn(viewModelScope)

    private val _youthDetails = MutableLiveData<YouthDetails>()
    val youthDetails : LiveData<YouthDetails> = _youthDetails

    private val _state = MutableLiveData(false)
    val state : LiveData<Boolean> = _state

    fun setYouthDetails(id : Int) {
        _state.value = false
        viewModelScope.launch {
            youthRepository.getYouthDetails(id)?.let {
                _youthDetails.value = it.value
                _state.value = true
            }
        }
    }

}