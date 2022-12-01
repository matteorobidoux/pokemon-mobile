package com.example.pokemonapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonapp.objects.Pokemon

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_table")
    fun getPokemons(): List<Pokemon>

    @Query("SELECT * FROM pokemon_table WHERE species == :pokemonName")
    fun getPokemonWithName(pokemonName : String): Pokemon

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pokemon: Pokemon)

    @Query("DELETE FROM pokemon_table")
    suspend fun deleteAll()
}