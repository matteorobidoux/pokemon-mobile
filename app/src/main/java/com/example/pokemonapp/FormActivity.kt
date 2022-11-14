package com.example.pokemonapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.FormActivityBinding

class FormActivity: AppCompatActivity() {

    private lateinit var binding: FormActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FormActivityBinding.inflate(layoutInflater)
        binding.profOakText.text = "Hey there trainer! Whats your name?"
        setContentView(binding.root)
    }

    public fun setText(text : String){
        binding.profOakText.text = text
    }
}