package com.example.pokemonapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokemonapp.objects.Pokemon
import com.example.pokemonapp.objects.Trainer

class CollectionAdapter(var context: Context, var trainer:Trainer, var teamAdapter: TeamAdapter) :
    RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    private var thisContext : Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.collection_recycler, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val collection = trainer.pokemonCollection.pokemons[position]
        val context = holder.view.context
        holder.imageView.load(collection.frontSprite)
        holder.imageView.setOnClickListener {addToTeam(collection)}
    }

    // Adding Pokemon to the team
    fun addToTeam(pokemonToAdd : Pokemon) {
        val id = trainer.pokemonCollection.pokemons.indexOf(pokemonToAdd)
        trainer.pokemonCollection.pokemons.remove(pokemonToAdd)
        notifyItemRemoved(id)
        teamAdapter.addTeam(pokemonToAdd)
    }

    // Adding pokemon collection
    fun addCollection(pokemonToAdd : Pokemon) {
        val id = trainer.pokemonCollection.pokemons.size
        trainer.pokemonCollection.pokemons.add(pokemonToAdd)
        notifyItemInserted(id)
    }

    override fun getItemCount(): Int = trainer.pokemonCollection.pokemons.size

    // Initializing the Views
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.imageView)
    }
}


