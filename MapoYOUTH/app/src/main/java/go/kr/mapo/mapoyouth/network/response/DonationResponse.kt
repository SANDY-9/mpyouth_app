package go.kr.mapo.mapoyouth.network.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class DonationResponse(
    @SerializedName("data")
    @Expose
    val `data`: List<Data> = listOf(),
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
        val caution: String = "",
        @SerializedName("contentsStatus")
        @Expose
        val contentsStatus: String = "",
        @SerializedName("description")
        @Expose
        val description: String = "",
        @SerializedName("endDate")
        @Expose
        val endDate: String = "",
        @SerializedName("entryFee")
        @Expose
        val entryFee: Int = 0,
        @SerializedName("id")
        @Expose
        val id: Int = 0,
        @SerializedName("location")
        @Expose
        val location: String = "",
        @SerializedName("managerContact")
        @Expose
        val managerContact: String = "",
        @SerializedName("managerName")
        @Expose
        val managerName: String = "",
        @SerializedName("mentor")
        @Expose
        val mentor: String = "",
        @SerializedName("organization")
        @Expose
        val organization: Organization = Organization(),
        @SerializedName("recruitEndDate")
        @Expose
        val recruitEndDate: String = "",
        @SerializedName("recruitNumber")
        @Expose
        val recruitNumber: Int = 0,
        @SerializedName("recruitStartDate")
        @Expose
        val recruitStartDate: String = "",
        @SerializedName("recruitStatus")
        @Expose
        val recruitStatus: String = "",
        @SerializedName("startDate")
        @Expose
        val startDate: String = "",
        @SerializedName("targetAge")
        @Expose
        val targetAge: String = "",
        @SerializedName("title")
        @Expose
        val title: String = "",
        @SerializedName("url")
        @Expose
        val url: String = ""
    )
}