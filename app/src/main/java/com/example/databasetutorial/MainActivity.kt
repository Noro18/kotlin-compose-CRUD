package com.example.databasetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.databasetutorial.data.AppDatabase
import com.example.databasetutorial.data.repository.StudentRepository
import com.example.databasetutorial.ui.StudentViewModel
import com.example.databasetutorial.ui.StudentViewModelFactory
import com.example.databasetutorial.ui.list.ListScreen
import com.example.databasetutorial.ui.navigation.AppNavHost
import com.example.databasetutorial.ui.theme.DatabaseTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(applicationContext)
        val repository = StudentRepository(db.studentDao())
        val viewModel = ViewModelProvider( // vieModelPrivier ne mak keep track ba lifecylce no activity ida ne'eb mak atu hare no VieModel ida ne'be mak atu uza
            this,
            StudentViewModelFactory(repository)
        )[StudentViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            DatabaseTutorialTheme {
                AppNavHost(viewModel = viewModel)
            }
        }
    }
}
