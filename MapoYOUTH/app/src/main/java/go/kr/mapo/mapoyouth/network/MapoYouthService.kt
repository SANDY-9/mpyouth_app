package go.kr.mapo.mapoyouth.network

import go.kr.mapo.mapoyouth.network.response.YouthDetailsResponse
import go.kr.mapo.mapoyouth.network.response.YouthListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-10-01
 * @desc
 */
interface MapoYouthService {

    @GET("program")
    suspend fun getYouthList() : Response<YouthListResponse>

    @GET("program/{id}")
    suspend fun getYouthDetails(
        @Path("id")id : Int
    ) : Response<YouthDetailsResponse>

}