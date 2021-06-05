package com.example.moviecatalogue.core.domain.model



data class Movie(
  val id:Int,
  val title:String?=null,
  val overview:String,
  val poster_path:String,
  val date:String?=null,
  val vote_average:Double,
  val type:String?=null,
  var isFavorite:Boolean?=null
)