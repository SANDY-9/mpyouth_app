package go.kr.mapo.mapoyouth.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Category : ArrayList<Category.CategoryItem>(){
    data class CategoryItem(
        @SerializedName("category_id")
        @Expose
        val categoryId: Int = 0,
        @SerializedName("name")
        @Expose
        val name: String = "",
        @SerializedName("level")
        @Expose
        val level: Int = 0,
        @SerializedName("parent_name")
        @Expose
        val parentName: String = ""
    )
}