package com.example.moviecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

    data class Data(
        val results:List<MovieResponse>
    )
    data class MovieResponse(
        val id:Int,
        @field:SerializedName("original_name", alternate = ["original_title"])
        val title:String?=null,
        val overview:String,
        val poster_path:String,
        @field:SerializedName("release_date", alternate = ["first_air_date"])
        val date:String?=null,
        val vote_average:Double,
        val type:String?=null,
        var isFavorite:Boolean?=null
    )
