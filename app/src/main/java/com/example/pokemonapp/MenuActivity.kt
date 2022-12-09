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
            Log.d(TAG, "trainer team size: ${trainer.pokemonTeam.pokemons.size}")
        }

        binding.pokecenterBtn.setOnClickListener{
            changeActivityPokecenter()
        }

        binding.wildPokeBattleBtn.setOnClickListener{
            changeActivityWildBattle()
        }

        binding.trainerBattleBtn.setOnClickListener{
            changeActivityTrainerBattle()
        }


    }

    private fun changeActivityPokecenter(){
        var pokeCenter = Intent(applicationContext, PokecenterActivity::class.java)
        pokeCenter.putExtra("trainer", trainer)
        startActivity(pokeCenter)
    }

    private fun changeActivityWildBattle(){
        var battle = Intent(applicationContext, BattleActivity::class.java)
        battle.putExtra("trainer", trainer)
        startActivity(battle)
    }

    private fun changeActivityTrainerBattle(){
        var battle = Intent(applicationContext, TrainerBattleActivity::class.java)
        battle.putExtra("trainer", trainer)
        startActivity(battle)
    }
}