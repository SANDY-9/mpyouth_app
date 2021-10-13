package go.kr.mapo.mapoyouth.network.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class VolunteerListResponse(
    @SerializedName("success")
    @Expose
    val success: String = "", // SUCCESS
    @SerializedName("message")
    @Expose
    val message: String = "", // 자원봉사 전체 조회
    @SerializedName("data")
    @Expose
    val `data`: VolunteerData = VolunteerData()
)
data class VolunteerData(
    @SerializedName("content")
    @Expose
    val content: List<Volunteer> = listOf(),
    @SerializedName("pageable")
    @Expose
    val pageable: Pageable = Pageable(),
    @SerializedName("totalPages")
    @Expose
    val totalPages: Int = 0, // 1
    @SerializedName("totalElements")
    @Expose
    val totalElements: Int = 0, // 3
    @SerializedName("last")
    @Expose
    val last: Boolean = false, // true
    @SerializedName("number")
    @Expose
    val number: Int = 0, // 0
    @SerializedName("sort")
    @Expose
    val sort: Sort = Sort(),
    @SerializedName("size")
    @Expose
    val size: Int = 0, // 10
    @SerializedName("numberOfElements")
    @Expose
    val numberOfElements: Int = 0, // 3
    @SerializedName("first")
    @Expose
    val first: Boolean = false, // true
    @SerializedName("empty")
    @Expose
    val empty: Boolean = false // false
)

data class Volunteer(
    @SerializedName("id")
    @Expose
    val id: Int = 0, // 1
    @SerializedName("title")
    @Expose
    val title: String = "", // 청소년동아리지원사업 '스스로 프로젝트'
    @SerializedName("description")
    @Expose
    val description: String = "", // 마포구 청소년동아리의 균형적 발전과 청소년문화 환경을 개선하고자 ...
    @SerializedName("location")
    @Expose
    val location: String = "", // 서울특별시 마포구 도화동 353-2
    @SerializedName("recruitNumber")
    @Expose
    val recruitNumber: Int = 0, // 10
    @SerializedName("recruitStartDate")
    @Expose
    val recruitStartDate: String = "", // 2021-12-25 24:00:00
    @SerializedName("recruitEndDate")
    @Expose
    val recruitEndDate: String = "", // 2021-12-25 24:00:00
    @SerializedName("startDate")
    @Expose
    val startDate: String = "", // 2021-12-25 24:00:00
    @SerializedName("endDate")
    @Expose
    val endDate: String = "", // 2021-12-25 24:00:00
    @SerializedName("url")
    @Expose
    val url: String = "", // http://mwyouth.org/
    @SerializedName("managerName")
    @Expose
    val managerName: String = "", // 김철수
    @SerializedName("managerContact")
    @Expose
    val managerContact: String = "", // 010-1234-5678
    @SerializedName("recruitStatus")
    @Expose
    val recruitStatus: String = "", // RECRUITING
    @SerializedName("contentsStatus")
    @Expose
    val contentsStatus: String = "", // BEFORE
    @SerializedName("organization")
    @Expose
    val organization: Organization = Organization(),
    @SerializedName("category")
    @Expose
    val category: Category = Category(),
    @SerializedName("volunteerType")
    @Expose
    val volunteerType: String = "", // INDIVIDUAL
    @SerializedName("period")
    @Expose
    val period: String = "" // 월|화|수
)