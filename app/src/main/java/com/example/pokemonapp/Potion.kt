package com.example.pokemonapp
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.PotionBinding

class Potion: AppCompatActivity() {
    private lateinit var binding: PotionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PotionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buyPotionBtn.setOnClickListener {
            buyingPotion()
        }

        binding.backPotionBtn.setOnClickListener {
            changeActivityPokemart()
        }
    }

    private fun changeActivityPokemart(){
        var mainIntent = Intent(applicationContext, Pokemart::class.java)
        startActivity(mainIntent)
    }

    private fun buyingPotion(){
        val potion : TextView =  findViewById(R.id.money_textview_content)
        Log.d("TAG", potion.text.toString())
//        var textPotion = potion.text.toString().toInt()
//        Log.d("TAG", textPotion.toString())
//        var potionNew = (textPotion + 50)
//        textPotion = potionNew
    }
}
