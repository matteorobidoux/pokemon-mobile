package com.example.pokemonapp

import com.example.pokemonapp.objects.Utils
import org.junit.Assert
import org.junit.Test

class UtilsTest {

    @Test
    fun GetPokemonJSONTest(){
        var result = Utils().getPokemonJSON("squirtle")
        Assert.assertNotNull(result)
    }

    @Test
    fun GetMoveJSONTest(){
        var result = Utils().getMoveJSON("tackle")
        Assert.assertNotNull(result)
    }
}