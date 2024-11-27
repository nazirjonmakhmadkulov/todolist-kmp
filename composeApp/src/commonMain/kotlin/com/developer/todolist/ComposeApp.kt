package com.developer.todolist

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.developer.todolist.presentation.category.CategoryScreen
import com.developer.todolist.presentation.new_category.NewCategoryScreen
import com.developer.todolist.presentation.new_task.NewTaskScreen
import com.developer.todolist.presentation.tasks.TasksScreen
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun ComposeApp() = MaterialTheme { TodoApp() }

enum class TaskScreen(string: String) {
    Categories("Categories"),
    CategoryItem("New category"),
    Tasks("Tasks"),
    TaskItem("New task")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
//    currentScreen: TaskScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text("", color = Color.Gray) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        })
}

@Composable
fun TodoApp() = PreComposeApp {
    KoinContext {
        val navigator = rememberNavigator()
        val modifier = Modifier
        val backStackEntry by navigator.currentEntry.collectAsState(null)
//    val currentScreen = TaskScreen.valueOf(
//        backStackEntry?.route?.route ?: TaskScreen.Categories.name
//    )
        val canNavigateBack by navigator.canGoBack.collectAsState(false)
        Scaffold(
            topBar = {
                AppBar(
//                currentScreen = currentScreen,
                    canNavigateBack = canNavigateBack,
                    navigateUp = { navigator.goBack() }
                )
            }
        ) { paddingValues ->
            NavHost(
                navigator = navigator,
                navTransition = NavTransition(),
                initialRoute = TaskScreen.Categories.name
            ) {
                scene(
                    route = TaskScreen.Categories.name,
                    navTransition = NavTransition()
                ) {
                    CategoryScreen(modifier,
                        onAdd = { navigator.navigate(TaskScreen.CategoryItem.name) },
                        onItem = { navigator.navigate(TaskScreen.Tasks.name + "/${it}") }
                    )
                }
                scene(
                    route = TaskScreen.CategoryItem.name,
                    navTransition = NavTransition()
                ) {
                    NewCategoryScreen(modifier) {
                        navigator.goBack()
                    }
                }
                scene(
                    route = TaskScreen.Tasks.name + "/{id}",
                    navTransition = NavTransition()
                ) { backStackEntry ->
                    backStackEntry.path("id", 1)?.let { id ->
                        TasksScreen(modifier, id) {
                            navigator.navigate(TaskScreen.TaskItem.name + "/${it}")
                        }
                    }
                }
                scene(
                    route = TaskScreen.TaskItem.name + "/{id}",
                    navTransition = NavTransition()
                ) { backStackEntry ->
                    backStackEntry.path("id", 1)?.let { id ->
                        NewTaskScreen(modifier, id) {
                            navigator.goBack()
                        }
                    }
                }
            }
        }
    }
}