package com.example.pokemonapp

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokemonapp.objects.Move

class MoveRecyclerViewAdapter internal constructor(context: Context, private val mData: MutableList<Move>) : RecyclerView.Adapter<MoveRecyclerViewAdapter.ViewHolder>() {
    private val TAG = "ADAPTER"
    private val mInflater: LayoutInflater

    //    private var mClickListener: ItemClickLister? = null
    var onItemClick: ((Move) -> Unit)? = null

    init {
        this.mInflater = LayoutInflater.from(context)
    }

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.recyclerview_row, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val move = mData[position]
        val name = move.name
        holder.tv.text = name

    }

    // total number of rows
    override fun getItemCount(): Int = mData.size


    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal var tv: TextView
        internal var parent: LinearLayout

        init {
//            image = itemView.findViewById(R.id.pokemon)
            tv = itemView.findViewById(R.id.move_name)
            parent = itemView.findViewById(R.id.parent)

            parent.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])

            }
        }
    }
}