package com.example.pokemonapp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.PokecenterBinding
import com.example.pokemonapp.objects.Trainer


class PokecenterActivity : AppCompatActivity() {

    private lateinit var binding: PokecenterBinding
    private lateinit var trainer: Trainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokecenterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            trainer = extras.getSerializable("trainer") as Trainer
            Log.d("TRAINER", trainer.pokemonTeam.pokemons.size.toString())
        }

        animateBackground()
        setListeners()
    }

    // This method will animate the background color change from white to black
    private fun animateBackground(){
        val mColors = arrayOf(ColorDrawable(Color.WHITE), ColorDrawable(Color.BLACK))
        val mTransition = TransitionDrawable(mColors)
        binding.relativeLayout.background = mTransition
        mTransition.startTransition(2000)

    }

    private fun setListeners(){
        binding.yesButton.setOnClickListener{
            heal()
            // alert, display message saying "pokemon has been healed"
        }

        binding.noButton.setOnClickListener{
            // alert, display message saying "pokemon has not been healed"
            backToMain()
        }
    }

    private fun backToMain(){
        // go back to main
        finish();
    }

    private fun heal(){
        trainer.pokemonTeam.pokemons.forEach {
            it.currentHp = it.baseStatMaxHp
        }
        backToMain()
    }
}