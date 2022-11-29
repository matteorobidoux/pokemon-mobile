package com.example.pokemonapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.MenuActivityBinding

class MenuActivity: AppCompatActivity() {

    private lateinit var binding: MenuActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MenuActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pokecenterBtn.setOnClickListener{
            changeActivityPokecenter()
        }
        binding.changeTeamBtn.setOnClickListener{
            changeActivityChangeTeam()
        }
    }

    private fun changeActivityPokecenter(){
        var formIntent = Intent(applicationContext, PokecenterActivity::class.java)
        startActivity(formIntent)
    }

    private fun changeActivityChangeTeam(){
        var formIntent = Intent(applicationContext, TeamActivity::class.java)
        startActivity(formIntent)
    }
}