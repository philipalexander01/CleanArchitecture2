package com.example.moviecatalogue.core.domain.usecase

import com.example.moviecatalogue.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUsecase {
    fun getDataMovie(): Flow<ArrayList<Movie>>
    fun getDataTv(): Flow<ArrayList<Movie>>
    fun getDetailMovie(movieId: Int): Flow<Movie>
    fun getDetailTv(tvId: Int): Flow<Movie>

    //favorite
    fun getAllMovie(type: String): Flow<List<Movie>>
    fun getMovieById(id: Int): Flow<Movie?>
    fun insert(movie: Movie)
    fun delete(id: Int)
}