package com.example.mmmmeal.data.remote

import com.example.mmmmeal.data.model.CategoryResponse
import com.example.mmmmeal.data.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiService {

    @GET("search.php")
    suspend fun getRecipesByLetter(@Query("f") letter: String): RecipeResponse?

    @GET("categories.php")
    suspend fun getAllCategories(): CategoryResponse?

}