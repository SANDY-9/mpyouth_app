package go.kr.mapo.mapoyouth.network.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.io.Serializable

data class Organization(
    @SerializedName("organization_id")
    @Expose
    val organizationId: Int = 0, // 1
    @SerializedName("name")
    @Expose
    val name: String = "", // 테스트기관이름
    @SerializedName("address")
    @Expose
    val address: String = "", // 테스트기관주소
    @SerializedName("phone")
    @Expose
    val phone: String = "", // 테스트전화번호
    @SerializedName("representative")
    @Expose
    val representative: String = "", // 테스트대표자 이름
    @SerializedName("homepage")
    @Expose
    val homepage: String = "", // testyouth.com
    @SerializedName("introduce")
    @Expose
    val introduce: String = "" // 테스트기관자기소개
) : Serializable