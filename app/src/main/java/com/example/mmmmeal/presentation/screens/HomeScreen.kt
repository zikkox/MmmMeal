package com.example.mmmmeal.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.mmmmeal.R
import com.example.mmmmeal.data.model.Category
import com.example.mmmmeal.data.model.Recipe
import com.example.mmmmeal.presentation.viewmodels.RecipeViewModel


@Composable
fun HomeScreen(navController: NavController) {

    val viewModel: RecipeViewModel = viewModel()

    val recipesState by viewModel.recipesState.observeAsState()
    val recipes = recipesState?.data ?: emptyList()

    val categoriesState by viewModel.categoriesState.observeAsState()
    val categories = categoriesState?.data ?: emptyList()

    LaunchedEffect(Unit) {
        viewModel.loadAllRecipes()
        viewModel.loadAllCategories()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 50.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            //top logo and header
            item {
                Column {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logo),
                            contentDescription = "Logo",
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = "MmmMeal",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.baloo_medium)),
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.Black
                        )

                    }

                    Text(
                        text = "Find your favorite recipes!",
                        fontFamily = FontFamily(Font(R.font.baloo_medium)),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }

            //search bar
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFFF5F5F5))
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.DarkGray
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Search",
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            //category bar
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(categories) { category ->
                        CategoryItem(category = category)
                    }
                }
            }

            //grid of recipes
            items(recipes.chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(22.dp)
                ) {
                    for (recipe in rowItems) {
                        Box(modifier = Modifier.weight(1f)) {
                            RecipeItem(recipe = recipe)
                        }
                    }

                    //if the row has only 1 item, add empty space for symmetry
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        //loading indicator
        if (recipesState?.isLoading == true) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(strokeWidth = 4.dp)
            }
        }
    }
}



@Composable
fun DisplayAllRecipes(recipes: List<Recipe>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(31.dp),
        horizontalArrangement = Arrangement.spacedBy(22.dp),
        userScrollEnabled = false
    ) {
        items(recipes.size) { index ->
            RecipeItem(recipe = recipes[index])
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF5F5F5))
            .padding(8.dp)
    ) {
        AsyncImage(
            model = category.thumbnail,
            contentDescription = category.name,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = category.name,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}



@Composable
fun RecipeItem(recipe: Recipe) {
    var favorited by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .width(180.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(modifier = Modifier.padding(12.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
            ) {
                //recipe image
                AsyncImage(
                    model = recipe.thumbnail,
                    contentDescription = recipe.name,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(12.dp))

                //title
                Text(
                    text = recipe.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Left
                )

                Spacer(modifier = Modifier.height(2.dp))

                //origin
                Text(
                    text = recipe.origin ?: "No origin",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Left
                )

                //category
                Text(
                    text = recipe.category ?: "No category",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Left
                )
            }

            //favorite icon
            IconButton(
                onClick = { favorited = !favorited },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    painter = painterResource(
                        id = if (favorited) R.drawable.ic_favorite else R.drawable.ic_unfavorite
                    ),
                    contentDescription = "Favorite",
                    tint = Color.Black.copy(alpha = 0.8f)
                )
            }
        }
    }
}


