package com.example.pokemonapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.TeamActivityBinding

class TeamActivity : AppCompatActivity(){
    private lateinit var binding: TeamActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TeamActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // for now this is hardcoded for testing purposes
        var teamlist : MutableList<String> = mutableListOf()
        teamlist.add("")

        // setting the recycler view for the teams list
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TeamAdapter(this, teamlist)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}