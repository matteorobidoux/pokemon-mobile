package com.example.pokemonapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_table")
    fun getPokemons(): List<Pokemon>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pokemon: Pokemon)

    @Query("DELETE FROM pokemon_table")
    suspend fun deleteAll()
}