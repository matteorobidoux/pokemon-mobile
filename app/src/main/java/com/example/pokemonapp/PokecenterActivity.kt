package com.example.pokemonapp

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.pokemonapp.databinding.PokecenterBinding
import kotlinx.coroutines.delay

class PokecenterActivity : AppCompatActivity() {

    private lateinit var binding: PokecenterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokecenterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners(){
        binding.yesButton.setOnClickListener{
            heal()
        }

        binding.noButton.setOnClickListener{
            backToMain()
        }
    }

    private fun backToMain(){
        // go back to main
    }

    private fun heal(){
        // loop through teams and put health to 100
        // heal then redirect back to main
        backToMain()
    }
}