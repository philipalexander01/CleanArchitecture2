package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.moviecatalogue.core.domain.model.Movie
import com.example.moviecatalogue.core.domain.usecase.MovieUsecase

class TvViewModel(private val movieUsecase: MovieUsecase) :ViewModel() {
    fun getDataTv(): LiveData<ArrayList<Movie>> = movieUsecase.getDataTv().asLiveData()
    fun getDataTvById(tvId:Int):LiveData<Movie> = movieUsecase.getDetailTv(tvId).asLiveData()
}