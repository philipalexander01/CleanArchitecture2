package com.example.moviecatalogue.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie where type = :type ")
    fun getMovie(type: String): Flow<List<com.example.moviecatalogue.core.data.source.local.entity.Movie>>


    @Query("SELECT * FROM movie where id = :id ")
    fun getMovieById(id: Int): Flow<com.example.moviecatalogue.core.data.source.local.entity.Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = com.example.moviecatalogue.core.data.source.local.entity.Movie::class)
    fun insertMovie(movie: com.example.moviecatalogue.core.data.source.local.entity.Movie)

    @Query("Delete from movie where id = :id")
    fun deleteMovie(id:Int)
}