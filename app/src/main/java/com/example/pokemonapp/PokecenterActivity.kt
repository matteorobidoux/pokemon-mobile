package com.example.pokemonapp

import android.R
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.PokecenterBinding


class PokecenterActivity : AppCompatActivity() {

    private lateinit var binding: PokecenterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokecenterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var mediaPlayer = MediaPlayer.create(applicationContext, com.example.pokemonapp.R.raw.title_screen_music)
        mediaPlayer.start()
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
        // loop through teams and put health to 100
        backToMain()
    }
}