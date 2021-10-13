package go.kr.mapo.mapoyouth.ui.donation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.repository.DonationRepository
import javax.inject.Inject

@HiltViewModel
class DonationViewModel @Inject constructor(
    private val mapoYouthService: MapoYouthService,
    private val donationRepository: DonationRepository): ViewModel() {


}