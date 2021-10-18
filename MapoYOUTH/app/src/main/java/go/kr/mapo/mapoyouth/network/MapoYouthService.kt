package go.kr.mapo.mapoyouth.network

import go.kr.mapo.mapoyouth.network.response.*
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

    @GET("volunteer/search")
    suspend fun searchVolunteer(
        @Query("keyword") keyword: String
    ) : Response<VolunteerListResponse>

    @GET("life-long-edu")
    suspend fun getEduList(
        @Query("page") page : Int
    ) : Response<EduListResponse>

    @GET("life-long-edu/{id}")
    suspend fun getEduDetails(
        @Path("id") id : Int
    ) : Response<EduDetailsResponse>

    @GET("life-long-edu/search")
    suspend fun searchEdu(
        @Query("keyword") keyword: String
    ) : Response<EduListResponse>

    @GET("donation")
    suspend fun getDonationList(
        @Query("page") page: Int
    ) : Response<DonationListResponse>

    @GET("donation/{id}")
    suspend fun getDonationDetails(
        @Path("id") id: Int
    ) : Response<DonationDetailsResponse>

}