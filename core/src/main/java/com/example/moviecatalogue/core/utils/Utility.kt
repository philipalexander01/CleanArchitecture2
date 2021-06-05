package com.example.moviecatalogue.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.moviecatalogue.core.data.source.local.entity.Movie
import com.example.moviecatalogue.core.data.source.remote.response.MovieResponse

class Utility {

    @SuppressLint("MissingPermission")
    fun checkInternetConnection(context: Context): NetworkCapabilities? {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork
        return connectivityManager.getNetworkCapabilities(nw)
    }

    fun mapResponsesToDomain(input: ArrayList<MovieResponse>): ArrayList<com.example.moviecatalogue.core.domain.model.Movie> =
        input.map {
            com.example.moviecatalogue.core.domain.model.Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                date = it.date,
                vote_average = it.vote_average,
                type = it.type
            )
        } as ArrayList<com.example.moviecatalogue.core.domain.model.Movie>


    fun mapResponseToDomain(it: MovieResponse)=
        it.let {
            com.example.moviecatalogue.core.domain.model.Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                date = it.date,
                vote_average = it.vote_average,
                type = it.type.toString()
            )
        }


    fun mapEntitiesToDomain(input: List<Movie>): List<com.example.moviecatalogue.core.domain.model.Movie> =
        input.map {
            com.example.moviecatalogue.core.domain.model.Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                date = it.date,
                vote_average = it.vote_average,
                type = it.type
            )
        }

    fun mapModelToEntity(it: com.example.moviecatalogue.core.domain.model.Movie)=
        it.let {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                date = it.date,
                vote_average = it.vote_average,
                type = it.type.toString()
            )
        }



    fun mapEntityToModel(it: Movie?)=
        it?.let { it1 ->
            com.example.moviecatalogue.core.domain.model.Movie(
                id = it1.id,
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                date = it.date,
                vote_average = it.vote_average,
                type = it.type
            )
        }


}

