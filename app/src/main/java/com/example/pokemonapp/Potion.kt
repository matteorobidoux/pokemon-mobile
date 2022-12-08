package com.example.pokemonapp
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
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

    private fun changeActivityPokemart(){
        var mainIntent = Intent(applicationContext, Pokemart::class.java)
        mainIntent.putExtra("trainer", trainer)
        startActivity(mainIntent)
    }

    private fun sellPotion(){
        Log.d("TAG", potion.quantity.toString())
        potion.quantity = potion.quantity + 1
        val funds = bind.moneyContent.text.toString()
        val newFunds = funds + 20;
        bind.moneyContent.text.toString() = newFunds
        Log.d("TAG", potion.quantity.toString())
    }

    private fun buyingPotion(){
        Log.d("TAG", potion.quantity.toString())
        potion.quantity = potion.quantity - 1
        Log.d("TAG", potion.quantity.toString())
        val bag = bind.bagContent.text.toString()
        Log.d("TAG", bag)
//        val bag_int = (bag.toInt() + 1)
//        Log.d("TAG", bag_int.toString())
//        bind.bagContent.text = bag_int.toString()
        val new_quantity = trainer.items[1].quantity + 1
        bind.bagContent.text = new_quantity.toString()
    }
}
