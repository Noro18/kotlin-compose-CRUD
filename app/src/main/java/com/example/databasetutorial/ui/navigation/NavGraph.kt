package com.example.databasetutorial.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.databasetutorial.data.repository.StudentRepository
import com.example.databasetutorial.ui.form.FormScreen
import com.example.databasetutorial.ui.list.ListScreen

object  Routes {
    const val FORM = "form"
    const val LIST = "list"
}

@Composable
fun AppNavHost(repository: StudentRepository) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LIST
    ) {
        composable(Routes.LIST) {
            ListScreen(
                repository = repository,
                onAddStudent = {
                    navController.navigate(Routes.FORM)
                }
            )
        }
        composable(Routes.FORM) {

            FormScreen(
                repository = repository,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}