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

        var teamlist : MutableList<Pokemon> = mutableListOf()
        teamlist = trainer.pokemonTeam.pokemons.toMutableList()
        Log.d("TRAINER", teamlist.size.toString())

        var collectionlist : MutableList<Pokemon> = mutableListOf()
        collectionlist = trainer.pokemonCollection.pokemons.toMutableList()
        Log.d("TRAINER", collectionlist.size.toString())


        // setting the recycler view for the teams list
        val recyclerViewTeam = findViewById<RecyclerView>(R.id.recyclerviewTeam)
        val adapterTeam = TeamAdapter(this, teamlist)
        recyclerViewTeam.adapter = adapterTeam
        recyclerViewTeam.layoutManager = LinearLayoutManager(this)

        // setting the recycler view for the collection list
        val recyclerViewCollection = findViewById<RecyclerView>(R.id.recyclerviewCollection)
        val adapterCollection = CollectionAdapter(this, collectionlist)
        recyclerViewCollection.adapter = adapterCollection
        recyclerViewCollection.layoutManager = LinearLayoutManager(this)


    }
}