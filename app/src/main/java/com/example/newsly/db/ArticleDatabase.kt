package com.example.newsly.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsly.Model.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Convertors::class)
abstract class ArticleDatabase:RoomDatabase() {
    abstract fun articleDao():ArticleDao

    companion object{
        @Volatile
        private var INSTANCE : ArticleDatabase?=null

        fun getInstance(context:Context):ArticleDatabase{
            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE=Room.databaseBuilder(context,
                        ArticleDatabase::class.java,
                        "aricle_db").build()
                }
            }
            return INSTANCE!!
        }
    }
}