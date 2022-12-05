package com.example.pokemonapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.TeamFragmentBinding
import com.example.pokemonapp.objects.Pokemon
import com.example.pokemonapp.objects.Trainer

class TeamFragment: Fragment() {
    private val TAG = "TEAM_FRAGMENT"
    lateinit var trainer: Trainer
    lateinit var adapter: TeamRecyclerViewAdapter
    lateinit var binding: TeamFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TeamFragmentBinding.inflate(inflater,container,false)



        Log.d(TAG, "arguments: ${arguments?.size()}")

        if(arguments != null){
            val trainer: Trainer = arguments?.getSerializable("trainer") as Trainer
            //TODO implement entire team

            Log.d(TAG, "GOT TRAINER: ${trainer.trainerName}")
            //recycler view Setup
            val activity = activity
            val recyclerView: RecyclerView = binding.pokemons
            if (activity != null) {
                adapter = TeamRecyclerViewAdapter(activity.applicationContext, trainer.pokemonTeam.pokemons)
            }
            val layoutManager: GridLayoutManager = GridLayoutManager(activity?.applicationContext, 2)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter

            //set up fragment on click
            adapter.onItemClick = { pokemon ->
                Log.d(TAG, "clicked ${pokemon.name} || hp: ${pokemon.currentHp}")

                //after press
                activity?.onBackPressed()
            }
        }

        binding.bagCancelButton.setOnClickListener{
            activity?.onBackPressed()
        }

        return binding.root

    }
}