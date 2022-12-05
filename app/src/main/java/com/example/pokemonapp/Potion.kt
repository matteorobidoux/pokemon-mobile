package com.example.pokemonapp
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.PotionBinding
import com.example.pokemonapp.objects.Items
import com.example.pokemonapp.objects.Trainer

class Potion: AppCompatActivity() {
    private lateinit var binding: PotionBinding
    private lateinit var trainer: Trainer

    private val potion_txt = "potion"
    private val potion_quantity = 20
    private val potion_value = 200
    private val potion_desc = "@string/poke_potion_text"
    private val potion_sprite = R.drawable.poke_potion
    private val potion = Items(potion_txt, potion_quantity, potion_value, potion_desc, potion_sprite)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PotionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            trainer = extras.getSerializable("trainer") as Trainer
            Log.d("TRAINER", trainer.pokemonTeam.pokemons.size.toString())
        }

        binding.buyPotionBtn.setOnClickListener {
            buyingPotion()
        }

        binding.backPotionBtn.setOnClickListener {
            changeActivityPokemart()
        }
    }

    private fun changeActivityPokemart(){
        var mainIntent = Intent(applicationContext, Pokemart::class.java)
        mainIntent.putExtra("trainer", trainer)
        startActivity(mainIntent)
    }

    private fun buyingPotion(){
        Log.d("TAG", potion.quantity.toString())
        potion.quantity = potion.quantity - 1
        Log.d("TAG", potion.quantity.toString())
    }
}
