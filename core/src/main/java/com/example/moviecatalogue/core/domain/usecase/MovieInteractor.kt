package com.example.moviecatalogue.core.domain.usecase

import com.example.moviecatalogue.core.domain.model.Movie
import com.example.moviecatalogue.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val repository: IMovieRepository): MovieUsecase {
    override  fun getDataMovie(): Flow<ArrayList<Movie>> = repository.getDataMovie()

    override fun getDataTv(): Flow<ArrayList<Movie>> = repository.getDataTv()

    override fun getDetailMovie(movieId: Int): Flow<Movie> = repository.getDetailMovie(movieId)

    override fun getDetailTv(tvId: Int): Flow<Movie> = repository.getDetailTv(tvId)

    //favorite
    override fun getAllMovie(type: String): Flow<List<Movie>> = repository.getAllMovie(type)

    override fun getMovieById(id: Int): Flow<Movie?> = repository.getMovieById(id)

    override fun insert(movie: Movie) = repository.insert(movie)

    override fun delete(id: Int) = repository.delete(id)

}