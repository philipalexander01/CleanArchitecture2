package com.example.moviecatalogue.core.data.source.local

import com.example.moviecatalogue.core.data.source.local.entity.Movie
import com.example.moviecatalogue.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun insert(movie: Movie){
        movieDao.insertMovie(movie)
    }

    fun delete(id:Int){
        movieDao.deleteMovie(id)
    }

    fun getMovie(type:String): Flow<List<Movie>>
    = movieDao.getMovie(type)

    fun getMovieById(id:Int):Flow<Movie>{
        return movieDao.getMovieById(id)
    }
}