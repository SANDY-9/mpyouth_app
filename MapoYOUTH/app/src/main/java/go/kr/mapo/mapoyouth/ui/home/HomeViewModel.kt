package go.kr.mapo.mapoyouth.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import go.kr.mapo.mapoyouth.network.NetworkState
import go.kr.mapo.mapoyouth.network.repository.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-14
 * @desc
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkState: NetworkState,
    private val youthRepository: YouthRepository,
    private val volunteerRepository: VolunteerRepository,
    private val eduRepository: EduRepository,
    private val donationRepository: DonationRepository) : ViewModel() {

    private val _isOnlined = MutableLiveData(false)
    val isOnlined = _isOnlined

    fun getNetworkState() {
        _isOnlined.value = networkState.isOnline()
    }

    suspend fun getLatestYouth() = withContext(Dispatchers.IO) {
        youthRepository.getLatestYouth()
    }

    suspend fun getLatestVolunteer() = withContext(Dispatchers.IO) {
        volunteerRepository.getLatestVolunteer()
    }

    suspend fun getLatestEdu() = withContext(Dispatchers.IO) {
        eduRepository.getLatestEdu()
    }

    suspend fun getDonation() = withContext(Dispatchers.IO) {
        donationRepository.fetchLatestDonation()
    }

}