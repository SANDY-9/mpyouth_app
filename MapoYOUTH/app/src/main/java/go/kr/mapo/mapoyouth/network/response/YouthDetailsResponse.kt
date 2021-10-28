package go.kr.mapo.mapoyouth.network.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class YouthDetailsResponse(
    @SerializedName("data")
    @Expose
    val `data`: YouthDetails,
    @SerializedName("message")
    @Expose
    val message: String = "", // string
    @SerializedName("success")
    @Expose
    val success: String = "" // FAIL
)
data class YouthDetails(
    @SerializedName("category")
    @Expose
    val category: Category = Category(),
    @SerializedName("caution")
    @Expose
    val caution: String = "", // string
    @SerializedName("description")
    @Expose
    val description: String = "", // string
    @SerializedName("endDate")
    @Expose
    val endDate: String = "", // yyyy-MM-dd kk:mm:ss
    @SerializedName("entryFee")
    @Expose
    val entryFee: Int = 0, // 0
    @SerializedName("location")
    @Expose
    val location: String = "", // string
    @SerializedName("managerContact")
    @Expose
    val managerContact: String = "", // string
    @SerializedName("managerName")
    @Expose
    val managerName: String = "", // string
    @SerializedName("organization")
    @Expose
    val organization: Organization = Organization(),
    @SerializedName("period")
    @Expose
    val period: String = "", // string
    @SerializedName("programFiles")
    @Expose
    val programFiles: List<ProgramFile> = listOf(),
    @SerializedName("programId")
    @Expose
    val programId: Int = 0, // 0
    @SerializedName("recruitEndDate")
    @Expose
    val recruitEndDate: String = "", // yyyy-MM-dd kk:mm:ss
    @SerializedName("recruitNumber")
    @Expose
    val recruitNumber: Int = 0, // 0
    @SerializedName("recruitStartDate")
    @Expose
    val recruitStartDate: String = "", // yyyy-MM-dd kk:mm:ss
    @SerializedName("recruitStatus")
    @Expose
    val recruitStatus: String = "", // DONE
    @SerializedName("startDate")
    @Expose
    val startDate: String = "", // yyyy-MM-dd kk:mm:ss
    @SerializedName("targetAge")
    @Expose
    val targetAge: String = "", // string
    @SerializedName("thumbnail")
    @Expose
    val thumbnail: Thumbnail = Thumbnail(),
    @SerializedName("title")
    @Expose
    val title: String = "", // string
    @SerializedName("url")
    @Expose
    val url: String = "", // string
    @SerializedName("volunteerType")
    @Expose
    val volunteerType: String = "" // GROUP
)
