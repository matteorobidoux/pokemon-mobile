package com.example.pokemonapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.PokemartPotionBinding

class PokemartPotion: AppCompatActivity()  {
    private lateinit var binding: PokemartPotionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokemartPotionBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.buyPotionBtn.setOnClickListener {
//            changeActivityPokeball()
//        }

        binding.backPotionBtn.setOnClickListener {
            changeActivityPokemart()
        }
    }

    private fun changeActivityPokemart(){
        var mainIntent = Intent(applicationContext, Pokemart::class.java)
        startActivity(mainIntent)
    }
}