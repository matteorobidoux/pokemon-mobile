package com.example.pokemonapp

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.pokemonapp.databinding.ActivityBattleBinding
import com.example.pokemonapp.objects.Pokemon
import com.example.pokemonapp.objects.PokemonTeam
import com.example.pokemonapp.objects.Trainer


private val TAG = "BATTLE"


class BattleActivity : AppCompatActivity() {
    lateinit var binding: ActivityBattleBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBattleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val trainer: Trainer = intent.extras?.getSerializable("trainer") as Trainer
        //dummy data
        val ash: Trainer = Trainer("Ash")
        val pokemons: ArrayList<Pokemon> = ArrayList<Pokemon>()
//        val bulb: Pokemon = Pokemon("1", "bulbasaur", 49, 49, 65, 65, 45, 49, 64, "grass", "https://pokeapi.co/api/v2/pokemon-form/1/", "https://pokeapi.co/api/v2/pokemon-form/1/")
        Log.d(TAG, "trainer: ${trainer.pokemonTeam.pokemons}")

        supportFragmentManager.commit {
            replace<BattleMenuFragment>(R.id.battle_menu)
            setReorderingAllowed(true)
            addToBackStack(null)
        }

    }
}