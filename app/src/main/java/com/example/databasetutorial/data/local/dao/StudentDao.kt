package com.example.databasetutorial.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.databasetutorial.data.local.entities.StudentEntity
import com.example.databasetutorial.ui.list.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * FROM students")
    fun getAllStudents(): Flow<List<StudentEntity>>

    @Insert
    suspend fun insertStudent(student: StudentEntity)
}