package com.example.databasetutorial.ui.navigation

import android.util.Log.v
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.databasetutorial.data.repository.StudentRepository
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
                    navController.navigate(Routes.FORM)
                }
            )
        }
        composable(Routes.FORM) {

            FormScreen(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}