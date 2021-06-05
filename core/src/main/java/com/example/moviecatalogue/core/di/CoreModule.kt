package com.example.moviecatalogue.core.di

import androidx.room.Room
import com.example.moviecatalogue.core.data.source.remote.network.ApiService
import com.example.moviecatalogue.core.data.source.local.LocalDataSource
import com.example.moviecatalogue.core.data.source.local.room.MovieRoomDatabase
import com.example.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.core.domain.repository.IMovieRepository
import com.example.moviecatalogue.core.data.MovieRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

    val databaseModule = module {
        factory { get<MovieRoomDatabase>().movieDao() }
        single {
            Room.databaseBuilder(
                androidContext(),
                MovieRoomDatabase::class.java,
                "Movie_db"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        }
    }

    val networkModule = module {
        single {
            Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
        }
    }

    val repositoryModule = module {
        single { LocalDataSource(get()) }
        single { RemoteDataSource(get()) }
        single<IMovieRepository> { MovieRepository(get(), get()) }
    }

