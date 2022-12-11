package com.example.pokemonapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokemonapp.objects.Items
import com.example.pokemonapp.objects.Pokemon

class TeamRecyclerViewAdapter internal constructor(context: Context, private val mData: MutableList<Pokemon>) : RecyclerView.Adapter<TeamRecyclerViewAdapter.ViewHolder>(){
    private val TAG = "ADAPTER"
    private val mInflater: LayoutInflater

    //    private var mClickListener: ItemClickLister? = null
    var onItemClick: ((Pokemon) -> Unit)? = null

    init {
        this.mInflater = LayoutInflater.from(context)
    }

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.team_recyclerview_row, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = mData[position]
        val pokeName = pokemon.name
        val Hp = pokemon.currentHp
        val maxHp = pokemon.baseStatMaxHp
        holder.name.text = "$pokeName"
        holder.stats.text = "Hp:$Hp/$maxHp"
        holder.image.load(pokemon.frontSprite)
    }

    // total number of rows
    override fun getItemCount(): Int = mData.size


    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal var stats: TextView
        internal var name: TextView
        internal var parent: LinearLayout
        internal var image: ImageView

        init {
            image = itemView.findViewById(R.id.team_pokemon_image)
            name = itemView.findViewById(R.id.team_name)
            stats = itemView.findViewById(R.id.team_hp)
            parent = itemView.findViewById(R.id.parent)

            parent.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])

            }
        }
    }

}