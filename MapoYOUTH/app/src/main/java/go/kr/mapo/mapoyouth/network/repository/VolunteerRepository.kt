package go.kr.mapo.mapoyouth.network.repository

import androidx.lifecycle.LiveData
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.response.VolunteerDetails
import javax.inject.Inject

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-10-12
 * @desc
 */
class VolunteerRepository @Inject constructor(private val mapoYouthService: MapoYouthService) {

    suspend fun getVolunteerDetails(id: Int) : LiveData<VolunteerDetails>? {
        val volunteerDataSource = VolunteerDataSource(mapoYouthService, null)
        volunteerDataSource.fetchVolunteerDetails(id)
        return volunteerDataSource.downloadedVolunteerDetails
    }

}