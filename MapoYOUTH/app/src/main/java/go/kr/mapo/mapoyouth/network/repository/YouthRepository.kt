package go.kr.mapo.mapoyouth.network.repository

import androidx.lifecycle.LiveData
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.response.YouthDetails
import javax.inject.Inject

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-10-01
 * @desc
 */
class YouthRepository @Inject constructor(private val mapoYouthService: MapoYouthService) {

    suspend fun getYouthDetails(id: Int) : LiveData<YouthDetails>? {
        val youthDataSource = YouthDataSource(mapoYouthService, null)
        youthDataSource.fetchYouthDetails(id)
        return youthDataSource.downloadedYouthDetails
    }

    suspend fun getLatestYouth() : LiveData<LatestYouth>? {
        val youthDataSource = YouthDataSource(mapoYouthService, null)
        youthDataSource.fetchLatestYouth()
        return youthDataSource.downloadedLatestYouth
    }

}