package com.example.moviecatalogue.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.moviecatalogue.core.domain.model.Movie
import com.example.moviecatalogue.core.domain.usecase.MovieUsecase

class FavoriteViewModel(private val movieUsecase: MovieUsecase) : ViewModel(){
    fun getDataMovie(type:String):LiveData<List<Movie>> = movieUsecase.getAllMovie(type).asLiveData()
}
