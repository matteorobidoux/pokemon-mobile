package com.example.pokemonapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.MenuActivityBinding
import com.example.pokemonapp.objects.Trainer

class MenuActivity: AppCompatActivity() {

    private lateinit var binding: MenuActivityBinding
    private lateinit var trainer: Trainer
    private val TAG = "MENU"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MenuActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            trainer = extras.getSerializable("trainer") as Trainer
            Log.d(TAG, "trainer: ${trainer.pokemonTeam.pokemons[0].currentHp}")
        }

        binding.pokecenterBtn.setOnClickListener{
            changeActivityPokecenter()
        }

        binding.pokemartBtn.setOnClickListener {
            changeActivityPokemart()
        }

        binding.wildPokeBattleBtn.setOnClickListener{
            changeActivityWildBattle()
        }

    }

    private fun changeActivityPokecenter(){
        var pokeCenter = Intent(applicationContext, PokecenterActivity::class.java)
        pokeCenter.putExtra("trainer", trainer)
        startActivity(pokeCenter)
    }

    private fun changeActivityPokemart(){
        var pokeMart = Intent(applicationContext, Pokemart::class.java)
        pokeMart.putExtra("trainer", trainer)
        startActivity(pokeMart)
    }

    private fun changeActivityWildBattle(){
        var battle = Intent(applicationContext, BattleActivity::class.java)
        battle.putExtra("trainer", trainer)
        startActivity(battle)
    }
}