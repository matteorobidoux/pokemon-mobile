package com.example.pokemonapp

import com.example.pokemonapp.objects.Move
import com.example.pokemonapp.objects.Pokemon
import com.example.pokemonapp.objects.PokemonCollection
import com.example.pokemonapp.objects.PokemonTeam
import org.junit.Assert
import org.junit.Test

class PokemonCollectionTest {

    @Test
    fun PokemonCollectionObjectTest(){
        var collection = PokemonCollection()
        Assert.assertNotNull(collection)
    }

    @Test
    fun AddToPokemonCollectionTest(){
        var moves =  ArrayList<Move>()
        var types = ArrayList<String>()
        types.add("fire")
        var move = Move("flamethrower",1,10,"BURN", 10, "physicial", "test", 0, 10, 10, "test", "fire")
        moves.add(move)
        var pokemon = Pokemon("1","charmander", 10,10,10,10,10,10,10,types,"test", "test",moves)

        var collection = PokemonCollection()
        collection.pokemons.add(pokemon)

        Assert.assertEquals(1, collection.pokemons.size)
    }
}