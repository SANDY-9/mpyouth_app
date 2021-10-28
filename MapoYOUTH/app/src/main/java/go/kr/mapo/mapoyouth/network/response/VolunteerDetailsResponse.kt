package go.kr.mapo.mapoyouth.network.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class VolunteerDetailsResponse(
    @SerializedName("data")
    @Expose
    val `data`: VolunteerDetails,
    @SerializedName("message")
    @Expose
    val message: String = "", // string
    @SerializedName("success")
    @Expose
    val success: String = "" // FAIL
)
data class VolunteerDetails(
    @SerializedName("category")
    @Expose
    val category: Category,
    @SerializedName("caution")
    @Expose
    val caution: String = "", // string
    @SerializedName("contents_status")
    @Expose
    val contentsStatus: String = "", // BEFORE
    @SerializedName("description")
    @Expose
    val description: String = "", // 마포구 청소년동아리의 균형적 발전과 청소년문화 환경을 개선하고자 ...
    @SerializedName("end_date")
    @Expose
    val endDate: String = "", // 2021-12-25 00:00:00
    @SerializedName("id")
    @Expose
    val id: Int = 0, // 0
    @SerializedName("location")
    @Expose
    val location: String = "", // 서울특별시 마포구 도화동 353-2
    @SerializedName("manager_contact")
    @Expose
    val managerContact: String = "", // 010-1234-5678
    @SerializedName("manager_name")
    @Expose
    val managerName: String = "", // 김철수
    @SerializedName("organization")
    @Expose
    val organization: Organization,
    @SerializedName("period")
    @Expose
    val period: String = "", // 월|화|수
    @SerializedName("recruit_end_date")
    @Expose
    val recruitEndDate: String = "", // 2021-12-25 00:00:00
    @SerializedName("recruit_number")
    @Expose
    val recruitNumber: Int = 0, // 10
    @SerializedName("recruit_start_date")
    @Expose
    val recruitStartDate: String = "", // 2021-12-25 00:00:00
    @SerializedName("recruit_status")
    @Expose
    val recruitStatus: String = "", // RECRUITING
    @SerializedName("start_date")
    @Expose
    val startDate: String = "", // 2021-12-25 00:00:00
    @SerializedName("title")
    @Expose
    val title: String = "", // 청소년동아리지원사업 '스스로 프로젝트'
    @SerializedName("url")
    @Expose
    val url: String = "", // https://mwyouth.org/
    @SerializedName("volunteer_type")
    @Expose
    val volunteerType: String = "", // INDIVIDUAL
    @SerializedName("entry_fee")
    @Expose
    val entryFee: Int = 0 // INDIVIDUAL
)
