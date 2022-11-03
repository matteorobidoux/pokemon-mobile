package com.example.pokemonapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.FormActivityBinding

class FormActivity: AppCompatActivity() {

    private lateinit var binding: FormActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FormActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}