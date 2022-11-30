package com.example.pokemonapp

import androidx.room.*

@Dao
interface PokemonWithMovesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemonWithMoves: PokemonAndMoves)

    @Transaction
    @Query("SELECT * FROM pokemon_table")
    fun getPokemonWithMoves(): List<PokemonWithMoves>

    @Delete
    fun delete(pokemonWithMoves: PokemonAndMoves)
}