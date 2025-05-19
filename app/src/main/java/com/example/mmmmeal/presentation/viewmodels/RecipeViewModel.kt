package com.example.mmmmeal.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mmmmeal.data.model.Category
import com.example.mmmmeal.data.model.Recipe
import com.example.mmmmeal.data.repository.RecipeRepository
import com.example.mmmmeal.utils.CategoryState
import com.example.mmmmeal.utils.RecipeState
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val recipeRepository = RecipeRepository()

    private val _recipesState = MutableLiveData<RecipeState<List<Recipe>>>()
    val recipesState: LiveData<RecipeState<List<Recipe>>> = _recipesState

    private val _categoriesState = MutableLiveData<CategoryState<List<Category>>>()
    val categoriesState: LiveData<CategoryState<List<Category>>> = _categoriesState

    fun loadAllRecipes() {
        viewModelScope.launch {
            _recipesState.value = RecipeState(isLoading = true)
            val result = recipeRepository.getAllRecipes()
            _recipesState.value = result
        }
    }

    fun loadAllCategories() {
        viewModelScope.launch {
            val result = recipeRepository.getAllCategories()
            _categoriesState.value = result
        }
    }
}