package com.example.pokemonapp

import com.example.pokemonapp.objects.Move
import com.example.pokemonapp.objects.Pokemon
import com.example.pokemonapp.objects.Trainer
import org.junit.Assert
import org.junit.Test

class TrainerTest {

    @Test
    fun TrainerObjectTest(){
        var trainer = Trainer("Matteo")
        Assert.assertNotNull(trainer)
    }

    @Test
    fun AddPokemonTeamTest(){
        var moves =  ArrayList<Move>()
        var types = ArrayList<String>()
        types.add("fire")
        var move = Move("flamethrower",1,10,"BURN", 10, "physicial", "test", 0, 10, 10, "test", "fire")
        moves.add(move)
        var pokemon = Pokemon("1","charmander", 10,10,10,10,10,10,10,types,"test", "test",moves)
        var trainer = Trainer("Matteo")
        trainer.addPokemon(pokemon)
        Assert.assertNotEquals(0, trainer.pokemonTeam.pokemons.size)
    }

    @Test
    fun AddPokemonMaxTeamTest(){
        var moves =  ArrayList<Move>()
        var types = ArrayList<String>()
        types.add("fire")
        var move = Move("flamethrower",1,10,"BURN", 10, "physicial", "test", 0, 10, 10, "test", "fire")
        moves.add(move)
        var pokemon = Pokemon("1","charmander", 10,10,10,10,10,10,10,types,"test", "test",moves)

        var trainer = Trainer("Matteo")

        trainer.addPokemon(pokemon)
        trainer.addPokemon(pokemon)
        trainer.addPokemon(pokemon)
        trainer.addPokemon(pokemon)
        trainer.addPokemon(pokemon)
        trainer.addPokemon(pokemon)
        trainer.addPokemon(pokemon)

        Assert.assertEquals(6, trainer.pokemonTeam.pokemons.size)
    }

    @Test
    fun AddPokemonCollectionTest(){
        var moves =  ArrayList<Move>()
        var types = ArrayList<String>()
        types.add("fire")
        var move = Move("flamethrower",1,10,"BURN", 10, "physicial", "test", 0, 10, 10, "test", "fire")
        moves.add(move)
        var pokemon = Pokemon("1","charmander", 10,10,10,10,10,10,10,types,"test", "test",moves)

        var trainer = Trainer("Matteo")

        trainer.addPokemon(pokemon)
        trainer.addPokemon(pokemon)
        trainer.addPokemon(pokemon)
        trainer.addPokemon(pokemon)
        trainer.addPokemon(pokemon)
        trainer.addPokemon(pokemon)
        trainer.addPokemon(pokemon)

        Assert.assertEquals(0, trainer.pokemonCollection.pokemons.size)
    }
}