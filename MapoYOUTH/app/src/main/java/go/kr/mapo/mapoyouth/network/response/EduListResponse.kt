package go.kr.mapo.mapoyouth.network.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class EduListResponse(
    @SerializedName("success")
    @Expose
    val success: String = "", // SUCCESS
    @SerializedName("message")
    @Expose
    val message: String = "", // 평생교육 전체 조회
    @SerializedName("data")
    @Expose
    val `data`: EduData
)
data class EduData(
    @SerializedName("content")
    @Expose
    val content: List<Edu> = listOf(),
    @SerializedName("pageable")
    @Expose
    val pageable: Pageable,
    @SerializedName("totalPages")
    @Expose
    val totalPages: Int = 0, // 1
    @SerializedName("totalElements")
    @Expose
    val totalElements: Int = 0, // 5
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
    val numberOfElements: Int = 0, // 5
    @SerializedName("first")
    @Expose
    val first: Boolean = false, // true
    @SerializedName("empty")
    @Expose
    val empty: Boolean = false // false
)
data class Edu(
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
    @SerializedName("entry_fee")
    @Expose
    val entryFee: Int = 0, // 0
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
    @SerializedName("target_age")
    @Expose
    val targetAge: String = "", // 초|중|고
    @SerializedName("title")
    @Expose
    val title: String = "", // 청소년동아리지원사업 '스스로 프로젝트'
    @SerializedName("url")
    @Expose
    val url: String = "" // https://mwyouth.org/
)