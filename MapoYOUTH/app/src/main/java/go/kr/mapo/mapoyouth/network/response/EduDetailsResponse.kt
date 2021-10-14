package go.kr.mapo.mapoyouth.network.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class EduDetailsResponse(
    @SerializedName("success")
    @Expose
    val success: String = "", // SUCCESS
    @SerializedName("message")
    @Expose
    val message: String = "", // 평생교육 단일 조회
    @SerializedName("data")
    @Expose
    val `data`: EduDetails = EduDetails()
)
data class EduDetails(
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
    @SerializedName("targetAge")
    @Expose
    val targetAge: String = "", // 초|중|고
    @SerializedName("entryFee")
    @Expose
    val entryFee: Int = 0, // 0
    @SerializedName("caution")
    @Expose
    val caution: String = "" // string
)