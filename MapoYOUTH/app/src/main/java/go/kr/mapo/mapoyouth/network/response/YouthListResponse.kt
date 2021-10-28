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
    val `data`: YouthData,
)
data class YouthData(
    @SerializedName("content")
    @Expose
    val content: List<Youth> = listOf(),
    @SerializedName("empty")
    @Expose
    val empty: Boolean = false, // true
    @SerializedName("first")
    @Expose
    val first: Boolean = false, // true
    @SerializedName("last")
    @Expose
    val last: Boolean = false, // true
    @SerializedName("number")
    @Expose
    val number: Int = 0, // 0
    @SerializedName("numberOfElements")
    @Expose
    val numberOfElements: Int = 0, // 0
    @SerializedName("pageable")
    @Expose
    val pageable: Pageable,
    @SerializedName("size")
    @Expose
    val size: Int = 0, // 0
    @SerializedName("sort")
    @Expose
    val sort: Sort,
    @SerializedName("totalElements")
    @Expose
    val totalElements: Int = 0, // 0
    @SerializedName("totalPages")
    @Expose
    val totalPages: Int = 0 // 0
)
data class Youth(
    @SerializedName("category")
    @Expose
    val category: Category,
    @SerializedName("description")
    @Expose
    val description: String = "", // string
    @SerializedName("end_date")
    @Expose
    val endDate: String = "", // yyyy-MM-dd kk:mm:ss
    @SerializedName("entry_fee")
    @Expose
    val entryFee: Int = 0, // 0
    @SerializedName("organization")
    @Expose
    val organization: Organization,
    @SerializedName("program_id")
    @Expose
    val programId: Int = 0, // 0
    @SerializedName("recruit_end_date")
    @Expose
    val recruitEndDate: String = "", // yyyy-MM-dd kk:mm:ss
    @SerializedName("recruit_number")
    @Expose
    val recruitNumber: Int = 0, // 0
    @SerializedName("recruit_start_date")
    @Expose
    val recruitStartDate: String = "", // yyyy-MM-dd kk:mm:ss
    @SerializedName("recruit_status")
    @Expose
    val recruitStatus: String = "", // DONE
    @SerializedName("start_date")
    @Expose
    val startDate: String = "", // yyyy-MM-dd kk:mm:ss
    @SerializedName("target_age")
    @Expose
    val targetAge: String = "", // string
    @SerializedName("thumbnail")
    @Expose
    val thumbnail: Thumbnail,
    @SerializedName("title")
    @Expose
    val title: String = "" // string
)