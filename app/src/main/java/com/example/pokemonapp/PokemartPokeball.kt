package com.example.pokemonapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.PokemartPokeballBinding

class PokemartPokeball: AppCompatActivity()  {

    private lateinit var binding: PokemartPokeballBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokemartPokeballBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.buyPokeballBtn.setOnClickListener {
//            changeActivityPokeball()
//        }

        binding.backPokeballBtn.setOnClickListener {
            changeActivityPokemart()
        }
    }
    private fun changeActivityPokemart(){
        var mainIntent = Intent(applicationContext, Pokemart::class.java)
        startActivity(mainIntent)
    }

}