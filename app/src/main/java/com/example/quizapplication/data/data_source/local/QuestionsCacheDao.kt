package com.example.quizapplication.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.quizapplication.domain.model.Question


@Database(
    entities =  [Question::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class QuestionsCacheDao: RoomDatabase() {
    abstract val questionCacheDao: QuestionCacheDao
    companion object {
        const val DATABASE_NAME = "questions_db"
    }
}