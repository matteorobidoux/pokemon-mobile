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
import com.example.pokemonapp.objects.Pokemon

class PokeballsRecyclerViewAdapter internal constructor(context: Context, private val mData: MutableList<Pokemon>) : RecyclerView.Adapter<PokeballsRecyclerViewAdapter.ViewHolder>(){

    private val TAG = "ADAPTER"
    private val mInflater: LayoutInflater

    //    private var mClickListener: ItemClickLister? = null
    var onItemClick: ((Pokemon) -> Unit)? = null

    init {
        this.mInflater = LayoutInflater.from(context)
    }

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.pokeballs_recyclerview_row, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = mData[position]
        val image = holder.image
//        val parent = holder.parent
//        image.load(R.drawable.pokeball)
//        image.setBackgroundColor(android.R.color.transparent)
    }

    // total number of rows
    override fun getItemCount(): Int = mData.size


    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
//        internal var tv: TextView
//        internal var parent: LinearLayout
        internal var image: ImageView

        init {
            image = itemView.findViewById(R.id.pokeball_image)
//            tv = itemView.findViewById(R.id.move_name)
//            parent = itemView.findViewById(R.id.parent)
//
//            parent.setOnClickListener {
//                Log.d(TAG, "in adapter clicking")
//                onItemClick?.invoke(mData[adapterPosition])
//
//            }
        }
    }
}