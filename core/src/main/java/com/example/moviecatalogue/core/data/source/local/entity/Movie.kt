package com.example.moviecatalogue.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    var id:Int=0,
    var title:String?=null,
    var overview:String,
    var poster_path:String,
    var date:String?=null,
    var vote_average:Double,
    var type:String,
)
