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

data class Pageable(
    @SerializedName("page")
    @Expose
    val pageNumber: Int = 0, // 0
    @SerializedName("size")
    @Expose
    val pageSize: Int = 0, // 10
    @SerializedName("sort")
    @Expose
    val sort: Sort
)

data class Thumbnail(
    @SerializedName("original_file_name")
    @Expose
    val originalFileName: String = "", // twice_jihyo.png
    @SerializedName("file_name")
    @Expose
    val fileName: String = "", // thumb_a062718d-23cb-4b9d-939a-e05ecef2f4d5.png
    @SerializedName("file_uri")
    @Expose
    val fileUri: String = "", // http://52.78.206.155:8080/file/thumb_a062718d-23cb-4b9d-939a-e05ecef2f4d5.png
    @SerializedName("file_size")
    @Expose
    val fileSize: Int = 0 // 101
)

data class ProgramFile(
    @SerializedName("file_name")
    @Expose
    val fileName: String = "", // string
    @SerializedName("file_size")
    @Expose
    val fileSize: Int = 0, // 0
    @SerializedName("file_uri")
    @Expose
    val fileUri: String = "", // string
    @SerializedName("original_file_name")
    @Expose
    val originalFileName: String = "" // string
)

data class Category(
    @SerializedName("category_id")
    @Expose
    val categoryId: Int = 0, // 0
    @SerializedName("level")
    @Expose
    val level: Int = 0, // 0
    @SerializedName("name")
    @Expose
    val name: String = "", // string
    @SerializedName("parent_name")
    @Expose
    val parentName: String = "" // string
)

data class Sort(
    @SerializedName("sorted")
    @Expose
    val sorted: Boolean = false, // false
    @SerializedName("unsorted")
    @Expose
    val unsorted: Boolean = false, // true
    @SerializedName("empty")
    @Expose
    val empty: Boolean = false // true
)