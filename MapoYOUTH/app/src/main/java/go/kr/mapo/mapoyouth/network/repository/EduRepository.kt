package go.kr.mapo.mapoyouth.network.repository

import androidx.lifecycle.LiveData
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.response.EduDetails
import javax.inject.Inject

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-10-13
 * @desc
 */
class EduRepository @Inject constructor(private val mapoYouthService: MapoYouthService) {

    suspend fun getEduDetails(id: Int) : LiveData<EduDetails>? {
        val eduDataSource = EduDataSource(mapoYouthService, null)
        eduDataSource.fetchEduDetails(id)
        return eduDataSource.downloadedEduDetails
    }

    suspend fun getLatestEdu() : LiveData<LatestEdu>? {
        val eduDataSource = EduDataSource(mapoYouthService, null)
        eduDataSource.fetchLatestEdu()
        return eduDataSource.downloadedLatestEdu
    }
}