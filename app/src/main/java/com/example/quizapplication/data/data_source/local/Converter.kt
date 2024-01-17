package com.example.quizapplication.data.data_source.local

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converter {

    @TypeConverter
    fun jsonToList(value: String?):List<String>? = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun listToJson(value: List<String>) = Gson().toJson(value)
}