package go.kr.mapo.mapoyouth.network.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class YouthListResponse(
    @SerializedName("success")
    @Expose
    val success: String = "", // SUCCESS
    @SerializedName("message")
    @Expose
    val message: String = "", // Program 전체 조회
    @SerializedName("data")
    @Expose
    val `data`: YouthData = YouthData()
)
data class YouthData(
    @SerializedName("content")
    @Expose
    val content: List<Youth> = listOf(),
    @SerializedName("pageable")
    @Expose
    val pageable: Pageable = Pageable(),
    @SerializedName("totalPages")
    @Expose
    val totalPages: Int = 0, // 2
    @SerializedName("totalElements")
    @Expose
    val totalElements: Int = 0, // 11
    @SerializedName("last")
    @Expose
    val last: Boolean = false, // false
    @SerializedName("number")
    @Expose
    val number: Int = 0, // 0
    @SerializedName("size")
    @Expose
    val size: Int = 0, // 10
    @SerializedName("numberOfElements")
    @Expose
    val numberOfElements: Int = 0, // 10
    @SerializedName("first")
    @Expose
    val first: Boolean = false, // true
    @SerializedName("empty")
    @Expose
    val empty: Boolean = false // false
)
data class Youth(
    @SerializedName("programId")
    @Expose
    val programId: Int, // 1
    @SerializedName("title")
    @Expose
    val title: String = "", // 테스트 제목
    @SerializedName("startDate")
    @Expose
    val startDate: String = "", // 2021-10-10 01:14:55
    @SerializedName("endDate")
    @Expose
    val endDate: String = "", // 2021-12-14 16:34:22
    @SerializedName("recruitStartDate")
    @Expose
    val recruitStartDate: String = "", // 2021-12-14 16:34:23
    @SerializedName("recruitEndDate")
    @Expose
    val recruitEndDate: String = "", // 2021-12-14 16:34:24
    @SerializedName("recruitNumber")
    @Expose
    val recruitNumber: Int = 0, // 10
    @SerializedName("recruitStatus")
    @Expose
    val recruitStatus: String = "", // RECRUITING
    @SerializedName("targetAge")
    @Expose
    val targetAge: String = "", // 초|중|고
    @SerializedName("entryFee")
    @Expose
    val entryFee: Int = 0, // 100
    @SerializedName("caution")
    @Expose
    val caution: String = "", // 주의사항
    @SerializedName("period")
    @Expose
    val period: String = "", // 주기
    @SerializedName("volunteerType")
    @Expose
    val volunteerType: String = "", // INDIVIDUAL
    @SerializedName("organization")
    @Expose
    val organization: Organization = Organization(),
    @SerializedName("thumbnail")
    @Expose
    val thumbnail: Thumbnail = Thumbnail(),
    @SerializedName("category")
    @Expose
    val category: Category = Category(),
    @SerializedName("description")
    @Expose
    val description: String = "", // string
)