package com.example.pokemonapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.MenuActivityBinding
import com.example.pokemonapp.objects.Trainer

class MenuActivity: AppCompatActivity() {

    private lateinit var binding: MenuActivityBinding
    private lateinit var trainer: Trainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MenuActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            trainer = extras.getSerializable("trainer") as Trainer
        }

        binding.pokecenterBtn.setOnClickListener{
            changeActivityPokecenter()
        }

        binding.pokemartBtn.setOnClickListener {
            changeActivityPokemart()
        }
    }

    private fun changeActivityPokecenter(){
        var pokeCenter = Intent(applicationContext, PokecenterActivity::class.java)
        pokeCenter.putExtra("trainer", trainer)
        startActivity(pokeCenter)
    }

    private fun changeActivityPokemart(){
        var martIntent = Intent(applicationContext, Pokemart::class.java)
        startActivity(martIntent)
    }
}