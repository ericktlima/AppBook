package com.example.bookapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: FavoriteBookEntity)

    @Delete
    suspend fun delete(book: FavoriteBookEntity)

    @Query("SELECT * FROM favorite_books")
    fun getAll(): Flow<List<FavoriteBookEntity>>

    @Query("SELECT * FROM favorite_books WHERE id = :id")
    suspend fun getById(id: String): FavoriteBookEntity?
}
