package com.example.pokemonapp

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.TeamActivityBinding
import com.example.pokemonapp.objects.Pokemon
import com.example.pokemonapp.objects.Trainer

private lateinit var trainer: Trainer

class TeamActivity : AppCompatActivity(){
    private lateinit var binding: TeamActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TeamActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras

        if (extras != null) {
            trainer = extras.getSerializable("trainer") as Trainer
            Log.d("TRAINER", trainer.pokemonTeam.pokemons.size.toString())
        }

        // for now this is hardcoded for testing purposes
        var teamlist : MutableList<Pokemon> = mutableListOf()
        teamlist = trainer.pokemonTeam.pokemons.toMutableList()
        Log.d("TRAINER", teamlist.size.toString())



        // setting the recycler view for the teams list
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TeamAdapter(this, teamlist)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}