package com.example.pokemonapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.pokemonapp.databinding.PokemonRenameDialogueBinding
import com.example.pokemonapp.objects.Pokemon
import com.example.pokemonapp.objects.Trainer

class RenameFragment : DialogFragment() {
    var TAG = "DIALOGUE"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = PokemonRenameDialogueBinding.inflate(inflater, container, false)

        if(arguments != null){
            val pokemon: Pokemon = arguments?.getSerializable("pokemon") as Pokemon
            val trainer: Trainer = arguments?.getSerializable("trainer") as Trainer

            Log.d(TAG, "arguments in dialogue: ${pokemon.name}")


            binding.confirmName.setOnClickListener{
                pokemon.name = binding.pokemonName.text.toString()
                Log.d(TAG, "confirmed name: ${binding.pokemonName.text.toString()} || pokemon name: ${pokemon.name}")
                Toast.makeText(activity?.applicationContext, "${pokemon.name} has been added to your team!", Toast.LENGTH_SHORT).show()
                trainer.addPokemon(pokemon)
                dismiss()
                //pass trainer + pokemon to menu activity
                var menuIntent = Intent(activity, MenuActivity::class.java)
                menuIntent.putExtra("trainer", trainer)
                activity?.startActivity(menuIntent)

            }
        }


        return binding.root
    }
}