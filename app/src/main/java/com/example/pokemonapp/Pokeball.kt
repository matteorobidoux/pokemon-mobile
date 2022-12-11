package com.example.pokemonapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.PokeballBinding
import com.example.pokemonapp.databinding.PokemartBinding
import com.example.pokemonapp.objects.Items
import com.example.pokemonapp.objects.Trainer

class Pokeball: AppCompatActivity() {
    private lateinit var binding: PokeballBinding
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
    // Returning to main menu
    private fun changeActivityPokemart(){
        var mainIntent = Intent(applicationContext, Pokemart::class.java)
        mainIntent.putExtra("trainer", trainer)
        startActivity(mainIntent)
    }

    // Selling pokeball and updating money and pokeball amounts
    private fun sellPokeball(){
        if(trainer.items[1].quantity > 0) {
            trainer.items[1].quantity = trainer.items[1].quantity - 1
            trainer.money += pokeball.value
            Toast.makeText(applicationContext, R.string.pokeball_sold, Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(applicationContext, R.string.no_pokeballs, Toast.LENGTH_SHORT).show()
        }
    }

    // Modifying Pokemart money
    private fun changePokemartMoney(){
        if(trainer.money > pokeball.value) {
            trainer.items[1].quantity = trainer.items[1].quantity + 1
            trainer.money -= pokeball.value
            Toast.makeText(applicationContext, R.string.pokeball_purchase, Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(applicationContext, R.string.pokeballs_no_money, Toast.LENGTH_SHORT).show()
        }
    }

}
