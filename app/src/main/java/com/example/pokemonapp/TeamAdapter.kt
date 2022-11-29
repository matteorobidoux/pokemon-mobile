package com.example.pokemonapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TeamAdapter(var context: Context, var teamList: MutableList<String>) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    private var teamlist : MutableList<String> = teamList.toMutableList()
    private var thisContext : Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.team_recycler, parent, false)
        return ViewHolder(layout)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val food = teamlist[position]
        val context = holder.view.context
        holder.imageView.setImageResource(R.drawable.charmander)
       //set image to url received

    }


    override fun getItemCount(): Int = teamlist.size


    // Initializing the Views
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.imageView)
    }
}
