package com.example.moviecatalogue.core.data

import com.example.moviecatalogue.core.data.source.local.LocalDataSource
import com.example.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.core.domain.model.Movie
import com.example.moviecatalogue.core.domain.repository.IMovieRepository
import com.example.moviecatalogue.core.utils.Utility
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMovieRepository {
    override fun getDataMovie(): Flow<ArrayList<Movie>> {
        return remoteDataSource.getDataMovie().map{Utility().mapResponsesToDomain(it) }
    }

    override fun getDataTv(): Flow<ArrayList<Movie>> {
        return remoteDataSource.getDataTv().map{Utility().mapResponsesToDomain(it) }
    }


    override fun getDetailMovie(movieId: Int): Flow<Movie> {
        return remoteDataSource.getDetailMovie(movieId).map { Utility().mapResponseToDomain(it) }
    }

    override fun getDetailTv(tvId: Int): Flow<Movie> {
        return remoteDataSource.getDetailTv(tvId) .map { Utility().mapResponseToDomain(it) }
    }



    override fun getAllMovie(type: String): Flow<List<Movie>> =
        localDataSource.getMovie(type).map { Utility().mapEntitiesToDomain(it) }

    override fun getMovieById(id: Int): Flow<Movie?> {
        return localDataSource.getMovieById(id).map { Utility().mapEntityToModel(it) }
    }

    override fun insert(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            val dataMovie = Utility().mapModelToEntity(movie)
            localDataSource.insert(dataMovie)
        }
    }

    override fun delete(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.delete(id)
        }
    }
}

