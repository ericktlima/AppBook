package com.example.bookapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteBookEntity::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun favoriteBookDao(): FavoriteBookDao
}
