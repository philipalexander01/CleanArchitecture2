package com.example.moviecatalogue.di

import com.example.moviecatalogue.core.domain.usecase.MovieInteractor
import com.example.moviecatalogue.core.domain.usecase.MovieUsecase
import com.example.moviecatalogue.viewmodel.DetailViewModel

import com.example.moviecatalogue.viewmodel.MovieViewModel
import com.example.moviecatalogue.viewmodel.TvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

    val useCaseModule = module {
        factory<MovieUsecase> { MovieInteractor(get()) }
    }

    val viewModelModule = module {
        viewModel { MovieViewModel(get()) }
        viewModel { TvViewModel(get()) }
        viewModel { DetailViewModel(get()) }
    }

