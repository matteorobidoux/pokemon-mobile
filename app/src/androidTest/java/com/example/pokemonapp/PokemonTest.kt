package com.example.pokemonapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pokemonapp.objects.Move
import com.example.pokemonapp.objects.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PokemonTest {

    @Test
    fun PokemonObjectTest(){

        var moves =  ArrayList<Move>()
        var types = ArrayList<String>()
        types.add("fire")
        var move = Move("flamethrower",1,10,"BURN", 10, "physicial", "test", 0, 10, 10, "test", "fire")
        moves.add(move)
        var pokemon = Pokemon("1","charmander", 10,10,10,10,10,10,10,types,"test", "test",moves)

        assertNotNull(pokemon)
    }

    //Ensures the 4 moves set by the init (constructor) works
    @Test
    fun PokemonInitTest(){

        var moves =  ArrayList<Move>()
        var types = ArrayList<String>()
        types.add("fire")
        var move = Move("flamethrower",1,10,"BURN", 10, "physicial", "test", 0, 10, 10, "test", "fire")
        moves.add(move)
        var pokemon = Pokemon("1","charmander", 10,10,10,10,10,10,10,types,"test", "test",moves)

        assertNotEquals(pokemon.moves, 0)
    }

    @Test
    fun PokemonExperienceGainedTest(){

        var moves =  ArrayList<Move>()
        var types = ArrayList<String>()
        types.add("fire")
        var move = Move("flamethrower",1,10,"BURN", 10, "physicial", "test", 0, 10, 10, "test", "fire")
        moves.add(move)
        var pokemon = Pokemon("1","charmander", 10,10,10,10,10,10,10,types,"test", "test",moves)
        var opposingPokemon = Pokemon("1","squirtle", 10,10,10,10,10,10,10,types,"test", "test",moves)


        val prevExperience = pokemon.experience
        pokemon.calculateExperienceGained(opposingPokemon)
        assertNotEquals(prevExperience, pokemon.experience)
    }
}