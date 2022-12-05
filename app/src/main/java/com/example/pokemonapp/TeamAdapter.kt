package com.example.pokemonapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokemonapp.objects.Pokemon

class TeamAdapter(var context: Context, var teamList: MutableList<Pokemon>) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    private var teamlist : MutableList<Pokemon> = teamList.toMutableList()
    private var thisContext : Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.team_recycler, parent, false)
        return ViewHolder(layout)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val team = teamlist[position]
        val context = holder.view.context
        holder.imageView.load(team.frontSprite)
        holder.imageView.setOnClickListener {removeTeam(team)}
        holder.textView.text=(position+1).toString()
    }

    fun addTeam(pokemonToAdd : Pokemon) {
        if (teamlist.size >=6){
            Toast.makeText(thisContext, "A team can not have more than 6 Pokémons", Toast.LENGTH_LONG).show()
        } else {
            val id = teamlist.size
            teamlist.add(pokemonToAdd)
            notifyItemInserted(id)

        }
    }

    fun removeTeam(pokemonToRemove : Pokemon) {
        if (teamlist.size <= 1){
            Toast.makeText(thisContext, "A team can not have less than 1 Pokémon", Toast.LENGTH_LONG).show()
        } else {
            val id = teamlist.indexOf(pokemonToRemove)
            teamlist.remove(pokemonToRemove)
            notifyDataSetChanged()
            (context as TeamActivity).addToCollection(pokemonToRemove)
        }
    }


    override fun getItemCount(): Int = teamlist.size


    // Initializing the Views
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val textView = view.findViewById<TextView>(R.id.number)

    }
}


