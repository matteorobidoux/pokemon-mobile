package com.example.pokemonapp

import android.app.AlertDialog
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
import com.example.pokemonapp.objects.Trainer
import com.google.android.material.button.MaterialButton


class TeamAdapter(var context: Context, var trainer: Trainer) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    private var thisContext : Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.team_recycler, parent, false)
        return ViewHolder(layout)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val team = trainer.pokemonTeam.pokemons[position]
        val context = holder.view.context
        holder.imageView.load(team.frontSprite)
        holder.imageView.setOnClickListener {removeTeam(team)}
        holder.textView.text=(position+1).toString()
    }

    fun addTeam(pokemonToAdd : Pokemon) {
        if (trainer.pokemonTeam.pokemons.size >=6){
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            var builder = AlertDialog.Builder(thisContext)
            var dialogInflater = inflater
            var dialogView = dialogInflater.inflate(R.layout.trainer_dialog, null)
            dialogView.findViewById<TextView>(R.id.trainer_dialog_text).text =
                "Pokemon Team Cannot Have More Than 6 Pokemon!"
            builder.setView(dialogView)
            var alert = builder.create()

            alert.show()

            dialogView.findViewById<MaterialButton>(R.id.trainer_dialog_button)
                .setOnClickListener {
                    alert.dismiss()
                }
        } else {
            val id = trainer.pokemonTeam.pokemons.size
            trainer.pokemonTeam.pokemons.add(pokemonToAdd)
            notifyItemInserted(id)

        }
    }

    fun removeTeam(pokemonToRemove : Pokemon) {
        if (trainer.pokemonTeam.pokemons.size <= 1){
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            var builder = AlertDialog.Builder(thisContext)
            var dialogInflater = inflater
            var dialogView = dialogInflater.inflate(R.layout.trainer_dialog, null)
            dialogView.findViewById<TextView>(R.id.trainer_dialog_text).text =
                "Pokemon Team Cannot Have Less Than 1 Pokemon!"
            builder.setView(dialogView)
            var alert = builder.create()

            alert.show()

            dialogView.findViewById<MaterialButton>(R.id.trainer_dialog_button)
                .setOnClickListener {
                    alert.dismiss()
                }
        } else {
            val id = trainer.pokemonTeam.pokemons.indexOf(pokemonToRemove)
            trainer.pokemonTeam.pokemons.remove(pokemonToRemove)
            notifyDataSetChanged()
            (context as TeamActivity).addToCollection(pokemonToRemove)
        }
    }


    override fun getItemCount(): Int = trainer.pokemonTeam.pokemons.size


    // Initializing the Views
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val textView = view.findViewById<TextView>(R.id.number)

    }
}


