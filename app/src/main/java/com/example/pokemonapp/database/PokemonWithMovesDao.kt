package com.example.pokemonapp.database

import androidx.room.*
import com.example.pokemonapp.objects.PokemonAndMoves
import com.example.pokemonapp.objects.PokemonWithMoves

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