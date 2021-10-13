package go.kr.mapo.mapoyouth.network.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class DonationListResponse(
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
        @SerializedName("content")
        @Expose
        val content: List<Content> = listOf(),
        @SerializedName("empty")
        @Expose
        val empty: Boolean = false,
        @SerializedName("first")
        @Expose
        val first: Boolean = false,
        @SerializedName("last")
        @Expose
        val last: Boolean = false,
        @SerializedName("number")
        @Expose
        val number: Int = 0,
        @SerializedName("numberOfElements")
        @Expose
        val numberOfElements: Int = 0,
        @SerializedName("pageable")
        @Expose
        val pageable: Pageable = Pageable(),
        @SerializedName("size")
        @Expose
        val size: Int = 0,
        @SerializedName("sort")
        @Expose
        val sort: Sort = Sort(),
        @SerializedName("totalElements")
        @Expose
        val totalElements: Int = 0,
        @SerializedName("totalPages")
        @Expose
        val totalPages: Int = 0
    ) {
        data class Content(
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
}