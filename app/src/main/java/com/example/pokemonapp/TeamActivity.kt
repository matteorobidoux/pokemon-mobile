package com.example.pokemonapp

import android.content.Intent
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
        }

        teamlist = trainer.pokemonTeam.pokemons.toMutableList()

        collectionlist = trainer.pokemonCollection.pokemons.toMutableList()


        binding.leaveTeam.setOnClickListener {
            var menuIntent = Intent(applicationContext, MenuActivity::class.java)
            menuIntent.putExtra("trainer", trainer)
            startActivity(menuIntent)
        }

        // setting the recycler view for the teams list
        val recyclerViewTeam = findViewById<RecyclerView>(R.id.recyclerviewTeam)
        adapterTeam = TeamAdapter(this, trainer)
        recyclerViewTeam.adapter = adapterTeam
        recyclerViewTeam.layoutManager = GridLayoutManager(this, 3)


        // adding hardcoded data to the colection for testing purposes
        val recyclerViewCollection = findViewById<RecyclerView>(R.id.recyclerviewCollection)
        adapterCollection = CollectionAdapter(this, trainer, adapterTeam)
        recyclerViewCollection.adapter = adapterCollection
        recyclerViewCollection.layoutManager = GridLayoutManager(this, 3)
    }

    // This method will animate the background color change from white to black
    private fun animateBackground(){
        val mColors = arrayOf(ColorDrawable(Color.WHITE), ColorDrawable(Color.BLACK))
        val mTransition = TransitionDrawable(mColors)
        binding.relativeLayout.background = mTransition
        mTransition.startTransition(3000)
    }

   fun addToCollection(pokemonToRemove : Pokemon){
        adapterCollection.addCollection(pokemonToRemove)
   }



}