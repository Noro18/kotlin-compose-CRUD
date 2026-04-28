package com.example.databasetutorial.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.databasetutorial.data.local.dao.StudentDao
import com.example.databasetutorial.data.local.entities.StudentEntity
import kotlin.jvm.java

@Database(
    entities = [StudentEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDao // Bolu no kria Dao iha ne'e
//   ^^^^
//    nia funsaun atu nune'e nia body ita la presiza implement rasik

    companion object{
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "student_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}