package com.example.databasetutorial.data.repository

import com.example.databasetutorial.data.local.dao.StudentDao
import com.example.databasetutorial.data.local.entities.StudentEntity
import kotlinx.coroutines.flow.Flow

class StudentRepository(private val studentDao: StudentDao) {
    fun getAllStudents(): Flow<List<StudentEntity>> = studentDao.getAllStudents()

    suspend fun insertStudent(name: String, marks: Int) {
        studentDao.insertStudent(StudentEntity(name = name, marks = marks))
    }

    suspend fun deleteStudent(student: StudentEntity) {
        studentDao.deleteStudent(student)
    }
}

