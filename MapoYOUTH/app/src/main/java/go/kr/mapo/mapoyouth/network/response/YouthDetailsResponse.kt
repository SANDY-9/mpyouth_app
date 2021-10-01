package go.kr.mapo.mapoyouth.network.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class YouthDetailsResponse(
    @SerializedName("success")
    @Expose
    val success: String = "", // SUCCESS
    @SerializedName("message")
    @Expose
    val message: String = "", // Program 단일 조회
    @SerializedName("data")
    @Expose
    val `data`: YouthDetails = YouthDetails()
)
data class YouthDetails(
    @SerializedName("program_id")
    @Expose
    val programId: Int = 0, // 1
    @SerializedName("title")
    @Expose
    val title: String = "", // 제목1
    @SerializedName("description")
    @Expose
    val description: String = "", // 내용1
    @SerializedName("start_date")
    @Expose
    val startDate: String = "", // 2021-10-10 01:14:55
    @SerializedName("end_date")
    @Expose
    val endDate: String = "", // 2021-12-14 16:34:22
    @SerializedName("recruit_start_date")
    @Expose
    val recruitStartDate: String = "", // 2021-12-14 16:34:23
    @SerializedName("recruit_end_date")
    @Expose
    val recruitEndDate: String = "", // 2021-12-14 16:34:24
    @SerializedName("recruit_number")
    @Expose
    val recruitNumber: Int = 0, // 10
    @SerializedName("location")
    @Expose
    val location: String = "", // 서울
    @SerializedName("manager_name")
    @Expose
    val managerName: String = "", // 매니저 이름
    @SerializedName("manager_contact")
    @Expose
    val managerContact: String = "", // 010-4123-4123
    @SerializedName("url")
    @Expose
    val url: String = "", // test.com
    @SerializedName("recruit_status")
    @Expose
    val recruitStatus: String = "", // RECRUITING
    @SerializedName("entry_fee")
    @Expose
    val entryFee: Int = 0, // 100
    @SerializedName("target_age")
    @Expose
    val targetAge: String = "", // 초중고
    @SerializedName("caution")
    @Expose
    val caution: String = "", // 주의사항
    @SerializedName("period")
    @Expose
    val period: String = "", // 주기
    @SerializedName("volunteer_type")
    @Expose
    val volunteerType: String = "", // INDIVIDUAL
    @SerializedName("organization_id")
    @Expose
    val organizationId: Int = 0, // 1
    @SerializedName("organization_name")
    @Expose
    val organizationName: String = "", // 테스트기관이름
    @SerializedName("program_files")
    @Expose
    val programFiles: List<Any> = listOf()
)