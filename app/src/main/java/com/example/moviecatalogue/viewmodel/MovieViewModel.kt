package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.moviecatalogue.core.domain.usecase.MovieUsecase
import com.example.moviecatalogue.core.domain.model.Movie

class MovieViewModel(private val movieUsecase: MovieUsecase) :ViewModel(){
     fun getDataMovie():LiveData<ArrayList<Movie>> = movieUsecase.getDataMovie().asLiveData()

    fun getDataMovieById(movieId:Int):LiveData<Movie> = movieUsecase.getDetailMovie(movieId).asLiveData()
}