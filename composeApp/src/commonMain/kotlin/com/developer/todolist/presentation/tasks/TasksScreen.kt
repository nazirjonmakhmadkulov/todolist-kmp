package com.developer.todolist.presentation.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.unit.dp
import com.developer.todolist.core.theme.theme_light_green
import com.developer.todolist.core.theme.theme_light_yellow
import com.developer.todolist.data.entity.TaskEntity
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun TasksScreen(modifier: Modifier = Modifier, id: Int, onAdd: (Int) -> Unit) {
    val viewModel = koinViewModel<TasksViewModel> { parametersOf(id) }
    val tasks by viewModel.tasks.collectAsState(emptyList())

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAdd.invoke(id) },
                modifier = modifier
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(top = 56.dp)) {
            TaskView(tasks = tasks, modifier)
        }
    }
}

@Composable
fun TaskView(tasks: List<TaskEntity>, modifier: Modifier = Modifier) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        items(tasks) {
            Card(
                modifier = modifier.fillMaxWidth().padding(12.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Row(
                    modifier = modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.CheckCircle, "", tint = theme_light_green)
                    Text(
                        it.title, modifier = modifier
                            .weight(1f).padding(horizontal = 12.dp)
                    )
                    Icon(Icons.Filled.Star, "", tint = theme_light_yellow)
                }
            }
        }
    }
}