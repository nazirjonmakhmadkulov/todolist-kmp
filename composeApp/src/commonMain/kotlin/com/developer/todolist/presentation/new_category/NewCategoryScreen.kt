package com.developer.todolist.presentation.new_category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developer.todolist.presentation.category.CategoryViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NewCategoryScreen(
    modifier: Modifier = Modifier,
    back: () -> Unit
) {
    val viewModel = koinViewModel<NewCategoryViewModel>()
    val title by viewModel.titleText.collectAsState()

    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Add Todo", fontSize = 22.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        TextField(
            value = title,
            modifier = modifier.fillMaxWidth().padding(vertical = 8.dp),
            onValueChange = { viewModel.titleText.value = it }
        )

        Button(
            onClick = {
                if (!viewModel.titleText.value.isBlank()) {
                    viewModel.onConfirm()
                    back.invoke()
                }
            },
            modifier = Modifier.padding(vertical = 16.dp),
        ) {
            Text(text = "Add")
        }
    }
}