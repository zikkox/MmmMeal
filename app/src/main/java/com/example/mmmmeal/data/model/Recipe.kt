package com.example.mmmmeal.data.model

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    val meals: List<Recipe>
)


data class Recipe(
    @SerializedName("idMeal")
    val id: Int,

    @SerializedName("strMeal")
    val name: String,

    @SerializedName("strCategory")
    val category: String,

    @SerializedName("strArea")
    val origin: String,

    @SerializedName("strInstructions")
    val instructions: String,

    @SerializedName("strMealThumb")
    val thumbnail: String,

    @SerializedName("strTags")
    val tags: String,

    @SerializedName("strYoutube")
    val youtubeLink: String,

    val strIngredient1: String,
    val strIngredient2: String,
    val strIngredient3: String,
    val strIngredient4: String,
    val strIngredient5: String,
    val strIngredient6: String,
    val strIngredient7: String,
    val strIngredient8: String,
    val strIngredient9: String,
    val strIngredient10: String
) {
}