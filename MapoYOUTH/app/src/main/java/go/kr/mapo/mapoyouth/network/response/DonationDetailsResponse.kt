package go.kr.mapo.mapoyouth.network.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class DonationDetailsResponse(
    @SerializedName("data")
    @Expose
    val `data`: Data = Data(),
    @SerializedName("message")
    @Expose
    val message: String = "",
    @SerializedName("success")
    @Expose
    val success: String = ""
) {
    data class Data(
        @SerializedName("category")
        @Expose
        val category: Category = Category(),
        @SerializedName("caution")
        @Expose
        val caution: String = "", // 참여하시기 전에 다음 사항을 숙지해주세요...
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
        @SerializedName("mentor")
        @Expose
        val mentor: String = "", // 이영희
        @SerializedName("organization")
        @Expose
        val organization: Organization = Organization(),
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
        val url: String = "" // http://mwyouth.org/
    )
}