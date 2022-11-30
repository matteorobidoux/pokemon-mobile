package com.example.pokemonapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoveDao {

    @Query("SELECT * FROM move_table")
    fun getMoves(): List<Move>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(move: Move)

    @Query("DELETE FROM move_table")
    suspend fun deleteAll()
}