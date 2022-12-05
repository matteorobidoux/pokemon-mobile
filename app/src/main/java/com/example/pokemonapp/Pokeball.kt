package com.example.pokemonapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.PokeballBinding
import com.example.pokemonapp.databinding.PokemartBinding
import com.example.pokemonapp.objects.Items
import com.example.pokemonapp.objects.Trainer

class Pokeball: AppCompatActivity() {
    private lateinit var binding: PokeballBinding
    lateinit var bind: PokemartBinding
    private lateinit var trainer: Trainer

    private val pokeball_txt = "pokeball"
    private val pokeball_quantity = 20
    private val pokeball_value = 200
    private val pokeball_desc = "@string/poke_pokeball_text"
    private val pokeball_sprite = R.drawable.pokeball_image
    private val pokeball = Items(pokeball_txt, pokeball_quantity, pokeball_value, pokeball_desc, pokeball_sprite)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokeballBinding.inflate(layoutInflater)
        bind = PokemartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            trainer = extras.getSerializable("trainer") as Trainer
            Log.d("TRAINER", trainer.pokemonTeam.pokemons.size.toString())
        }

        binding.buyPokeballBtn.setOnClickListener {
            changePokemartMoney()
        }

        binding.sellPokeballBtn.setOnClickListener {
            sellPokeball()
        }

        binding.exitPokeballBtn.setOnClickListener {
            changeActivityPokemart()
        }
    }
    private fun changeActivityPokemart(){
        var mainIntent = Intent(applicationContext, Pokemart::class.java)
        mainIntent.putExtra("trainer", trainer)
        startActivity(mainIntent)
    }

    private fun sellPokeball(){
    }

    private fun changePokemartMoney(){
        Log.d("TAG", pokeball.quantity.toString())
        pokeball.quantity = pokeball.quantity - 1
        Log.d("TAG", pokeball.quantity.toString())
        val bag = bind.bagContent.text.toString()
        Log.d("TAG", bag)
        val bag_int = (bag.toInt() + 1)
        Log.d("TAG", bag_int.toString())
        bind.bagContent.text = bag_int.toString()
    }

}
