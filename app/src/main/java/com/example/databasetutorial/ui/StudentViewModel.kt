package com.example.databasetutorial.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databasetutorial.data.local.entities.StudentEntity
import com.example.databasetutorial.data.repository.StudentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn

class StudentViewModel(private val repository: StudentRepository): ViewModel() {
    var name by mutableStateOf("")
        private set
    var marks by mutableStateOf("")
        private set

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


}