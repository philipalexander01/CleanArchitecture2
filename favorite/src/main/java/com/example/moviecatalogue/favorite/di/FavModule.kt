package com.example.moviecatalogue.favorite.di

import com.example.moviecatalogue.favorite.viewmodel.FavoriteViewModel
import org.koin.dsl.module

val favoriteModule = module {
    single(override=true) { FavoriteViewModel(get()) }
    }

