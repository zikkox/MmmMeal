package com.example.mmmmeal.utils

data class RecipeState<T>(
    val data: T? = null,
    val error: Throwable? = null,
    val isLoading: Boolean = false
)

data class CategoryState<T>(
    val data: T? = null,
    val error: Throwable? = null,
    val isLoading: Boolean = false
)