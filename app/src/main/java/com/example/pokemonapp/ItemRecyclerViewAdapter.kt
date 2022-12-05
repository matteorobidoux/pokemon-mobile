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
import com.example.pokemonapp.objects.Items

class ItemRecyclerViewAdapter internal constructor(context: Context, private val mData: MutableList<Items>) : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>(){
    private val TAG = "ADAPTER"
    private val mInflater: LayoutInflater

    //    private var mClickListener: ItemClickLister? = null
    var onItemClick: ((Items) -> Unit)? = null

    init {
        this.mInflater = LayoutInflater.from(context)
    }

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_recyclerview_row, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData[position]
        val name = item.name
        holder.tv.text = "$name x ${item.quantity}"
        holder.image.setImageResource(item.sprite)
    }

    // total number of rows
    override fun getItemCount(): Int = mData.size


    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal var tv: TextView
        internal var parent: LinearLayout
        internal var image: ImageView

        init {
            image = itemView.findViewById(R.id.item_image)
            tv = itemView.findViewById(R.id.move_name)
            parent = itemView.findViewById(R.id.parent)

            parent.setOnClickListener {
                Log.d(TAG, "in adapter clicking")
                onItemClick?.invoke(mData[adapterPosition])

            }
        }
    }

}