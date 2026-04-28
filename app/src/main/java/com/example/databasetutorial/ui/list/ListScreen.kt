package com.example.databasetutorial.ui.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.databasetutorial.data.repository.StudentRepository
import com.example.databasetutorial.ui.theme.DatabaseTutorialTheme

data class Student(val name: String, val marks: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    repository: StudentRepository,
    modifier: Modifier = Modifier,
    onAddStudent: () -> Unit = {}
) {
    val students by repository.getAllStudents().collectAsStateWithLifecycle(initialValue = emptyList())
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Student Marks") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddStudent) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Student")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            items(students) { student ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                ) {
                    ListItem(
                        headlineContent = {
                            Text(text = student.name)
                        },
                        supportingContent = {
                            Text(text = "Marks: ${student.marks}")
                        }
                    )
                }
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    DatabaseTutorialTheme {
        ListScreen()
    }
}*/
