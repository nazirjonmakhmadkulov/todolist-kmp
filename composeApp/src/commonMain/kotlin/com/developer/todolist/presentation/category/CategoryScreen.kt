package com.developer.todolist.presentation.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.developer.todolist.domain.model.CategoryWithCount
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import todolist.composeapp.generated.resources.Res
import todolist.composeapp.generated.resources.calendar
import todolist.composeapp.generated.resources.star
import todolist.composeapp.generated.resources.sunny
import todolist.composeapp.generated.resources.task

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    onAdd: () -> Unit,
    onItem: (Long) -> Unit
) {
    val viewModel = koinViewModel<CategoryViewModel>()
    val categories by viewModel.categories.collectAsState(emptyList())

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAdd.invoke() },
                modifier = modifier
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(top = 56.dp)) {
            CategoryView(categories = categories, modifier, onItem)
        }
    }
}

@Composable
fun CategoryView(
    categories: List<CategoryWithCount>,
    modifier: Modifier = Modifier,
    onItem: (Long) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        categories.forEach { category ->
            item(key = category.id) {
                Card(
                    modifier = modifier.fillMaxWidth().padding(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = MaterialTheme.shapes.medium,
                    onClick = { onItem.invoke(category.id) }
                ) {
                    Row(
                        modifier = modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val icon = if (category.id == 1L) painterResource(Res.drawable.sunny)
                        else if (category.id == 2L) painterResource(Res.drawable.star)
                        else if (category.id == 3L) painterResource(Res.drawable.calendar)
                        else if (category.id == 4L) painterResource(Res.drawable.task)
                        else painterResource(Res.drawable.task)

                        Image(
                            painter = icon,
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                        Text(
                            category.title, modifier = modifier
                                .weight(1f).padding(horizontal = 12.dp)
                        )
                        Text(category.count.toString())
                    }
                }
            }
        }
    }
}