package com.example.pokemonapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.PokemartBinding
import android.content.Intent

class Pokemart: AppCompatActivity() {

    private lateinit var binding: PokemartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokemartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pokePokeballBtn.setOnClickListener {
            changeActivityPokeball()
        }

        binding.pokePotionBtn.setOnClickListener {
            changeActivityPotion()
        }
    }

    private fun changeActivityPokeball(){
        var pokeballIntent = Intent(applicationContext, PokemartPokeball::class.java)
        startActivity(pokeballIntent)
    }

    private fun changeActivityPotion(){
        var potionIntent = Intent(applicationContext, PokemartPotion::class.java)
        startActivity(potionIntent)
    }

}