package com.example.pokemonapp.database

import androidx.room.*
import com.example.pokemonapp.objects.PokemonMoveRef

@Dao
interface PokemonWithMovesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemonWithMoves: PokemonMoveRef)

    @Transaction
    @Query("SELECT * FROM pokemon_table")
    fun getPokemonWithMoves(): List<PokemonWithMoves>

    @Delete
    fun delete(pokemonWithMoves: PokemonMoveRef)
}