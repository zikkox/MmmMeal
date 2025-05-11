package com.example.mmmmeal.data.repository

import com.example.mmmmeal.data.model.Recipe
import com.example.mmmmeal.data.remote.RecipeApiService
import com.example.mmmmeal.utils.RecipeState
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeRepository {

    private val recipeApi: RecipeApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        recipeApi = retrofit.create(RecipeApiService::class.java)
    }

    suspend fun getAllRecipes(): RecipeState<List<Recipe>> {
        return try {
            val letters = 'a'..'z'
            val allMeals = mutableListOf<Recipe>()

            for (letter in letters) {
                val response = recipeApi.getRecipesByLetter(letter.toString())
                response?.meals?.let {
                    allMeals.addAll(it)
                }
            }

            RecipeState(data = allMeals)

        } catch (e: Exception) {
            RecipeState(error = e)
        }
    }
}
