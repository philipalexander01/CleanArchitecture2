package com.example.moviecatalogue.core.data.source.remote.network

import com.example.moviecatalogue.core.data.source.remote.response.Data
import com.example.moviecatalogue.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("{film}/popular")
     suspend fun getMovie(
        @Path("film") film: String,
        @Query("api_key") api_key: String
    ): Data


    @GET("movie/{external_id}")
    suspend   fun getMovieById(
        @Path("external_id") external_id: Int,
        @Query("api_key") api_key: String
    ): MovieResponse


    @GET("tv/{external_id}")
    suspend  fun getTvById(
        @Path("external_id") external_id: Int,
        @Query("api_key") api_key: String
    ): MovieResponse


}