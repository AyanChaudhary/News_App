package com.example.newsly.db

import androidx.room.TypeConverter
import com.example.newsly.Model.Source


class Convertors {

    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name!!
    }
    @TypeConverter
    fun toSource(name:String):Source{
        return Source(name,name)
    }
}