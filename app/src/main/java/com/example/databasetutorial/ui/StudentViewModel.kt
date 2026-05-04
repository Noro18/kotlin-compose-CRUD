package com.example.databasetutorial.ui

import android.R.attr.name
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.databasetutorial.data.local.entities.StudentEntity
import com.example.databasetutorial.data.repository.StudentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudentViewModel(private val repository: StudentRepository): ViewModel() {
    var name by mutableStateOf("")
        private set
    var marks by mutableStateOf("")
        private set



    var errorMessage by mutableStateOf<String?>(null)

    val students: StateFlow<List<StudentEntity>> = repository
        .getAllStudents()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // updatere ba form field nian
    fun onNameChange(value: String) { name = value }
    fun onMarksChange(value: String) { marks = value }

    fun insertStudent() {
        val parsedMarks = marks.toIntOrNull()

        if (name.isBlank() && parsedMarks == null) {
            errorMessage = "Name & Marks cannot be empty"
            return
        }
        if (name.isBlank()) { errorMessage = "Name cannot be empty"; return }
        if (parsedMarks == null) { errorMessage = "Marks must be a number"; return }
        errorMessage = null

        viewModelScope.launch {
            repository.insertStudent(name, parsedMarks)
            name = ""
            marks = ""
        }
    }

    fun deleteStudent(student: StudentEntity) {
        viewModelScope.launch {
            repository.deleteStudent(student)
        }
    }
}

class StudentViewModelFactory(private val repository: StudentRepository ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudentViewModel(repository) as T
    }
}