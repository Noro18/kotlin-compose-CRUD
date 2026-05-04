package com.example.databasetutorial.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.databasetutorial.ui.StudentViewModel
import com.example.databasetutorial.ui.form.FormScreen
import com.example.databasetutorial.ui.list.ListScreen

object  Routes {
    const val FORM = "form"
    const val LIST = "list"
}

@Composable
fun AppNavHost(viewModel: StudentViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LIST
    ) {
        composable(Routes.LIST) {
            ListScreen(
                viewModel = viewModel,
                onAddStudent = {
                    viewModel.clearEditing()
                    navController.navigate(Routes.FORM)
                },
                onEditStudent = { student ->
                    viewModel.startEditing(student)
                    navController.navigate(Routes.FORM)
                }
            )
        }
        composable(Routes.FORM) {

            FormScreen(
                viewModel = viewModel,
                onBack = {
                    viewModel.clearEditing() // hamos tia lai
                    navController.popBackStack()
                },

            )
        }
    }
}