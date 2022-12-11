package com.example.pokemonapp

import android.content.ClipData
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.PokemartBinding
import com.example.pokemonapp.objects.Items
import com.example.pokemonapp.objects.Trainer

class Pokemart: AppCompatActivity() {
    private lateinit var binding: PokemartBinding
    private lateinit var trainer: Trainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokemartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            trainer = extras.getSerializable("trainer") as Trainer
            binding.moneyTextviewContent.text = trainer.money.toString()
            binding.bagContentPotion.text = trainer.items[0].quantity.toString()
            binding.bagContentPokeball.text = trainer.items[1].quantity.toString()
        }

        binding.pokePokeballBtn.setOnClickListener {
            changeActivityPokeball()
        }

        binding.pokePotionBtn.setOnClickListener {
            changeActivityPotion()
        }

        binding.pokemartBackBtn.setOnClickListener {
            changeActivityBack()
        }
    }

    // View pokeball in detail
    private fun changeActivityPokeball(){
        var pokeballIntent = Intent(applicationContext, Pokeball::class.java)
        pokeballIntent.putExtra("trainer", trainer)
        startActivity(pokeballIntent)
    }

    // View potion in detail
    private fun changeActivityPotion(){
        var potionIntent = Intent(applicationContext, Potion::class.java)
        potionIntent.putExtra("trainer", trainer)
        startActivity(potionIntent)
    }
    // Back to main menu
    private fun changeActivityBack(){
        var backIntent = Intent(applicationContext, MenuActivity::class.java)
        backIntent.putExtra("trainer", trainer)
        startActivity(backIntent)
    }

}
