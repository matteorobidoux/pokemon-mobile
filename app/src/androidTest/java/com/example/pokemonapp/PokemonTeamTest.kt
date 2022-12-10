package com.example.pokemonapp

import com.example.pokemonapp.objects.Move
import com.example.pokemonapp.objects.Pokemon
import com.example.pokemonapp.objects.PokemonCollection
import com.example.pokemonapp.objects.PokemonTeam
import org.junit.Assert
import org.junit.Test

class PokemonTeamTest {

    @Test
    fun PokemonTeamObjectTest(){
        var team = PokemonTeam()
        Assert.assertNotNull(team)
    }

    @Test
    fun AddToPokemonTeamTest(){
        var moves =  ArrayList<Move>()
        var types = ArrayList<String>()
        types.add("fire")
        var move = Move("flamethrower",1,10,"BURN", 10, "physicial", "test", 0, 10, 10, "test", "fire")
        moves.add(move)
        var pokemon = Pokemon("1","charmander", 10,10,10,10,10,10,10,types,"test", "test",moves)

        var team = PokemonTeam()
        team.pokemons.add(pokemon)

        Assert.assertEquals(1, team.pokemons.size)
    }
}