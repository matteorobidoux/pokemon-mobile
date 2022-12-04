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
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter

private lateinit var trainer: Trainer
private var teamlist : MutableList<Pokemon> = mutableListOf()
private var collectionlist : MutableList<Pokemon> = mutableListOf()
private lateinit var adapterTeam : TeamAdapter
private lateinit var adapterCollection: CollectionAdapter


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

        teamlist = trainer.pokemonTeam.pokemons.toMutableList()
        Log.d("TRAINER", teamlist.size.toString())

        collectionlist = trainer.pokemonCollection.pokemons.toMutableList()


        // setting the recycler view for the teams list
        val recyclerViewTeam = findViewById<RecyclerView>(R.id.recyclerviewTeam)
        adapterTeam = TeamAdapter(this, teamlist)
        recyclerViewTeam.adapter = adapterTeam
        recyclerViewTeam.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // setting the recycler view for the collection list
        collectionlist.add(teamlist[0])
        collectionlist.add(teamlist[0])
        Log.d("TRAINER", collectionlist.size.toString())

        // adding hardcoded data to the colection for testing purposes
        val recyclerViewCollection = findViewById<RecyclerView>(R.id.recyclerviewCollection)
        adapterCollection = CollectionAdapter(this, collectionlist, teamlist, adapterTeam)
        recyclerViewCollection.adapter = adapterCollection
        recyclerViewCollection.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    fun addToTeamActivity(pokemonToAdd : Pokemon) {
        val id = teamlist.size
        teamlist.add(pokemonToAdd)
        //adapterTeam.notifyItemInserted(id)
        adapterTeam.addTeam(pokemonToAdd)
        Log.d("TRAINER", "updating " + teamlist.size)
    }


}