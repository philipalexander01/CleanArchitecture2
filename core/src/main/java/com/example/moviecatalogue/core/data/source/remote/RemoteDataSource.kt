package com.example.moviecatalogue.core.data.source.remote

import android.util.Log
import com.example.moviecatalogue.core.BuildConfig
import com.example.moviecatalogue.core.data.source.remote.network.ApiService
import com.example.moviecatalogue.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService){
    private val API_KEY = BuildConfig.MOVIE
    fun getDataMovie() : Flow<ArrayList<MovieResponse>> {
        return flow {
            try{
                val response = apiService.getMovie("movie", API_KEY)
                emit(response.results)
            } catch (e : Exception){
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO) as Flow<ArrayList<MovieResponse>>
    }

    fun getDataTv():Flow<ArrayList<MovieResponse>> {
        return flow {
            try{
                val response = apiService.getMovie("tv", API_KEY)
                emit(response.results)
            } catch (e : Exception){
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO) as Flow<ArrayList<MovieResponse>>

    }

    fun getDetailMovie(movieId: Int):Flow<MovieResponse> {
        return flow {
            try{
                val response =   apiService
                    .getMovieById(movieId, API_KEY)
                emit(response)
            } catch (e : Exception){
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


    fun getDetailTv(tvId: Int) :Flow<MovieResponse>{
        return flow {
            try{
                val response =   apiService
                    .getTvById(tvId, API_KEY)
                emit(response)
            } catch (e : Exception){
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }



}