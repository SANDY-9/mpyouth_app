package go.kr.mapo.mapoyouth.network.repository

import androidx.lifecycle.LiveData
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.response.DonationDetailsResponse
import javax.inject.Inject

class DonationRepository @Inject constructor(private val mapoYouthService: MapoYouthService) {

    suspend fun getDonationDetails(id:Int) : LiveData<DonationDetailsResponse.Data> {
        val donationData = DonationDataSource(mapoYouthService)
        donationData.fetchDonationDetails(id)
        return donationData.downloadedDonationDetails
    }
}