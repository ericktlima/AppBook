package com.example.bookapp.di

import GetBookByIdUseCase
import android.app.Application
import com.example.bookapp.data.repository.BookRepositoryImpl
import com.example.bookapp.domain.repository.BookRepository
import com.example.bookapp.domain.usecase.SearchBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.room.Room
import com.example.bookapp.data.local.BookDatabase
import com.example.bookapp.data.local.FavoriteBookDao
import com.example.bookapp.data.repository.FavoriteRepositoryImpl
import com.example.bookapp.domain.repository.FavoriteRepository
import com.example.bookapp.domain.usecase.AddToFavoritesUseCase
import com.example.bookapp.domain.usecase.IsFavoriteUseCase
import com.example.bookapp.domain.usecase.RemoveFromFavoritesUseCase

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBookRepository(
        impl: BookRepositoryImpl
    ): BookRepository = impl

    @Provides
    @Singleton
    fun provideSearchBooksUseCase(
        repo: BookRepository
    ): SearchBooksUseCase = SearchBooksUseCase(repo)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): BookDatabase =
        Room.databaseBuilder(app, BookDatabase::class.java, "book_db").build()

    @Provides
    @Singleton
    fun provideFavoriteBookDao(db: BookDatabase): FavoriteBookDao = db.favoriteBookDao()

    @Provides
    @Singleton
    fun bindFavoriteRepository(
        impl: FavoriteRepositoryImpl
    ): FavoriteRepository = impl

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        dao: FavoriteBookDao
    ): FavoriteRepositoryImpl = FavoriteRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideAddToFavoritesUseCase(
        repository: FavoriteRepository
    ): AddToFavoritesUseCase = AddToFavoritesUseCase(repository)

    @Provides
    @Singleton
    fun provideRemoveFromFavoritesUseCase(
        repository: FavoriteRepository
    ): RemoveFromFavoritesUseCase = RemoveFromFavoritesUseCase(repository)

    @Provides
    @Singleton
    fun provideIsFavoriteUseCase(
        repository: FavoriteRepository
    ): IsFavoriteUseCase = IsFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun provideGetBookByIdUseCase(
        repository: BookRepository
    ): GetBookByIdUseCase = GetBookByIdUseCase(repository)

}
