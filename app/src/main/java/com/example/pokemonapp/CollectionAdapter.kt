package com.example.pokemonapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokemonapp.objects.Pokemon

class CollectionAdapter(var context: Context, var collectionList: MutableList<Pokemon>) :
    RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    private var collectionlist : MutableList<Pokemon> = collectionList.toMutableList()
    private var thisContext : Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.collection_recycler, parent, false)
        return ViewHolder(layout)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val collection = collectionlist[position]
        val context = holder.view.context
        holder.imageView.load(collection.frontSprite)
        holder.textiew.text=position.toString()
    }


    override fun getItemCount(): Int = collectionlist.size


    // Initializing the Views
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val textiew = view.findViewById<TextView>(R.id.number)

    }
}


