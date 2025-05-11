package com.example.mmmmeal.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mmmmeal.data.model.Recipe
import com.example.mmmmeal.presentation.viewmodels.RecipeViewModel


@Composable
fun HomeScreen(navController: NavController) {

    val viewModel: RecipeViewModel = viewModel()
    val recipesState by viewModel.recipesState.observeAsState()
    val recipes = recipesState?.data ?: emptyList()

    LaunchedEffect(Unit) {
        viewModel.loadAllRecipes()
    }

    LazyColumn {
        items(recipes.size) { recipe ->
            RecipeItem(recipe = recipes[recipe])
        }
    }
}


@Composable
fun RecipeItem(recipe: Recipe?) {
    if (recipe != null) {
        Text(text = recipe.name, fontSize = 25.sp)
    }
}
