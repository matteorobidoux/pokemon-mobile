package com.example.pokemonapp
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.PokemartBinding
import com.example.pokemonapp.databinding.PotionBinding
import com.example.pokemonapp.objects.Items
import com.example.pokemonapp.objects.Trainer
import org.w3c.dom.Text

class Potion: AppCompatActivity() {
    private lateinit var binding: PotionBinding
    lateinit var bind: PokemartBinding
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
        bind = PokemartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            trainer = extras.getSerializable("trainer") as Trainer
            Log.d("TRAINER", trainer.pokemonTeam.pokemons.size.toString())
        }

        binding.buyPotionBtn.setOnClickListener {
            buyingPotion()
        }

        binding.sellPotionBtn.setOnClickListener {
            sellPotion()
        }

        binding.exitPotionBtn.setOnClickListener {
            changeActivityPokemart()
        }
    }

    // Back to Pokemart
    private fun changeActivityPokemart(){
        var mainIntent = Intent(applicationContext, Pokemart::class.java)
        mainIntent.putExtra("trainer", trainer)
        startActivity(mainIntent)
    }

    // Selling Potion
    private fun sellPotion(){
        if(trainer.items[0].quantity > 0) {
            trainer.items[0].quantity = trainer.items[0].quantity - 1
            trainer.money += potion.value
            Toast.makeText(applicationContext, R.string.potion_sold, Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(applicationContext, R.string.no_potions, Toast.LENGTH_SHORT).show()
        }
    }
    //Buying Potion
    private fun buyingPotion(){
        if(trainer.money > potion.value) {
            trainer.items[0].quantity = trainer.items[0].quantity + 1
            trainer.money -= potion.value
            Toast.makeText(applicationContext, R.string.potion_purchase, Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(applicationContext, R.string.potion_no_money, Toast.LENGTH_SHORT).show()
        }
    }
}
