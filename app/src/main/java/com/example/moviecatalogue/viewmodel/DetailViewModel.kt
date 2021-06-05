package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.moviecatalogue.core.domain.model.Movie
import com.example.moviecatalogue.core.domain.usecase.MovieUsecase

class DetailViewModel(private val movieUsecase: MovieUsecase) : ViewModel(){

    fun getDataMovieById(movieId:Int):LiveData<Movie?> = movieUsecase.getMovieById(movieId).asLiveData()

    fun insert(movie: Movie) {
        movieUsecase.insert(movie)
    }

    fun delete(id: Int){
        movieUsecase.delete(id)
    }
}