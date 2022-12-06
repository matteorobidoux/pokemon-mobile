package com.example.pokemonapp

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pokemonapp.databinding.PokecenterBinding
import com.example.pokemonapp.objects.Trainer
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.*


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
        var menuIntent = Intent(this, MenuActivity::class.java)
        menuIntent.putExtra("trainer", trainer)
        this.startActivity(menuIntent)
    }

    private fun heal(){
        lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                trainer.pokemonTeam.pokemons.forEach {
                    it.currentHp = it.baseStatMaxHp
                }
                var mediaPlayer = MediaPlayer.create(applicationContext, R.raw.pokecenter_sound)
                mediaPlayer.start()
                delay(4000)
                var builder = AlertDialog.Builder(this@PokecenterActivity)
                var dialogInflater = layoutInflater
                var dialogView = dialogInflater.inflate(R.layout.trainer_dialog, null)
                dialogView.findViewById<TextView>(R.id.trainer_dialog_text).text = "Your Pokemon Have Been Healed!"
                builder.setView(dialogView)
                var alert = builder.create()

                alert.show()

                dialogView.findViewById<MaterialButton>(R.id.trainer_dialog_button).setOnClickListener{
                    alert.dismiss()
                    backToMain()
                }
            }
        }
    }
}