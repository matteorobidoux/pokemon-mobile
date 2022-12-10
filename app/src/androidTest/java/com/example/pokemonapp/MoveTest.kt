package com.example.pokemonapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pokemonapp.objects.Move
import com.example.pokemonapp.objects.Pokemon
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoveTest {

    @Test
    fun MoveObjectTest(){

        var move = Move("flamethrower",1,10,"BURN", 10, "physicial", "test", 0, 10, 10, "test", "fire")
        Assert.assertNotNull(move)
    }
}