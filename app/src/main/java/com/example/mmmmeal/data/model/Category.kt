package com.example.mmmmeal.data.model

import com.google.gson.annotations.SerializedName
import com.example.mmmmeal.data.model.Category

data class CategoryResponse(
    val categories: List<Category>
)


data class Category(
    @SerializedName("idCategory")
    val id: String,
    @SerializedName("strCategory")
    val name: String,
    @SerializedName("strCategoryThumb")
    val thumbnail: String,
    @SerializedName("strCategoryDescription")
    val description: String
)
