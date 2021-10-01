package go.kr.mapo.mapoyouth.network.repository

import androidx.lifecycle.LiveData
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.response.YouthDetails
import java.lang.Exception

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-10-01
 * @desc
 */
class YouthRepository(private val mapoYouthService: MapoYouthService) {

    private lateinit var youthDataSource: YouthDataSource

    suspend fun getYouthDetails(id: Int) : LiveData<YouthDetails> {
        youthDataSource = YouthDataSource(mapoYouthService)
        youthDataSource.fetchYouthDetails(id)
        return youthDataSource.downloadedYouthDetails
    }

}