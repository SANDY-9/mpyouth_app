package go.kr.mapo.mapoyouth.network

import go.kr.mapo.mapoyouth.network.response.VolunteerDetailsResponse
import go.kr.mapo.mapoyouth.network.response.VolunteerListResponse
import go.kr.mapo.mapoyouth.network.response.YouthDetailsResponse
import go.kr.mapo.mapoyouth.network.response.YouthListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-10-01
 * @desc
 */
interface MapoYouthService {

    @GET("program")
    suspend fun getYouthList(
        @Query("page") page : Int
    ) : Response<YouthListResponse>

    @GET("program/{id}")
    suspend fun getYouthDetails(
        @Path("id")id : Int
    ) : Response<YouthDetailsResponse>

    @GET("program/search")
    suspend fun searchYouth(
        @Query("keyword") keyword: String
    ) : Response<YouthListResponse>

    @GET("volunteer")
    suspend fun getVolunteerList(
        @Query("page") page : Int
    ) : Response<VolunteerListResponse>

    @GET("volunteer/{id}")
    suspend fun getVolunteerDetails(
        @Path("id")id : Int
    ) : Response<VolunteerDetailsResponse>



}