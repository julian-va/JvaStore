package jva.cloud.jvastore.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DateConverter {
    @TypeConverter
    fun fromListToStringList(list: List<String>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringListToList(value: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}