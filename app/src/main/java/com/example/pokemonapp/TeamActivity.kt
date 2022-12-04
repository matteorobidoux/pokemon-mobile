package com.example.pokemonapp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
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

        animateBackground()

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
        recyclerViewTeam.layoutManager = GridLayoutManager(this, 3)

        // setting the recycler view for the collection list
        collectionlist.add(teamlist[0])
        collectionlist.add(teamlist[0])
        Log.d("TRAINER", collectionlist.size.toString())

        // adding hardcoded data to the colection for testing purposes
        val recyclerViewCollection = findViewById<RecyclerView>(R.id.recyclerviewCollection)
        adapterCollection = CollectionAdapter(this, collectionlist, adapterTeam)
        recyclerViewCollection.adapter = adapterCollection
        recyclerViewCollection.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    // This method will animate the background color change from white to black
    private fun animateBackground(){
        val mColors = arrayOf(ColorDrawable(Color.WHITE), ColorDrawable(Color.BLACK))
        val mTransition = TransitionDrawable(mColors)
        binding.relativeLayout.background = mTransition
        mTransition.startTransition(3000)

    }



}