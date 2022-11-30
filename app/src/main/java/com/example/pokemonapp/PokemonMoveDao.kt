package com.example.pokemonapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonMoveDao {

    @Query("SELECT * FROM pokemon_move_table")
    fun getPokemonMoves(): List<PokemonAndMoves>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pokemonMobe: PokemonAndMoves)

    @Query("DELETE FROM pokemon_move_table")
    suspend fun deleteAll()
}