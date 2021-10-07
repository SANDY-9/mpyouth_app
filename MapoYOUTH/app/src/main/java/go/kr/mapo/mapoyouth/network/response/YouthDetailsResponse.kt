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
    @SerializedName("end_date")
    @Expose
    val endDate: String = "", // yyyy-MM-dd kk:mm:ss
    @SerializedName("entry_fee")
    @Expose
    val entryFee: Int = 0, // 0
    @SerializedName("location")
    @Expose
    val location: String = "", // string
    @SerializedName("manager_contact")
    @Expose
    val managerContact: String = "", // string
    @SerializedName("manager_name")
    @Expose
    val managerName: String = "", // string
    @SerializedName("organization")
    @Expose
    val organization: Organization = Organization(),
    @SerializedName("period")
    @Expose
    val period: String = "", // string
    @SerializedName("program_files")
    @Expose
    val programFiles: List<ProgramFile> = listOf(),
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
    val thumbnail: Thumbnail = Thumbnail(),
    @SerializedName("title")
    @Expose
    val title: String = "", // string
    @SerializedName("url")
    @Expose
    val url: String = "", // string
    @SerializedName("volunteer_type")
    @Expose
    val volunteerType: String = "" // GROUP
)
