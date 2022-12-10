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
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokemonapp.databinding.TeamFragmentBinding
import com.example.pokemonapp.objects.Pokemon
import com.example.pokemonapp.objects.Trainer

class TeamFragment: Fragment() {
    private val TAG = "TEAM_FRAGMENT"
    lateinit var trainer: Trainer
    private lateinit var oppTrainer: Trainer
    lateinit var opponent: Pokemon
    lateinit var adapter: TeamRecyclerViewAdapter
    lateinit var binding: TeamFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TeamFragmentBinding.inflate(inflater,container,false)

        if(arguments != null){
            trainer= arguments?.getSerializable("trainer") as Trainer
            opponent = arguments?.getSerializable("opponent") as Pokemon

            if(arguments?.containsKey("oppTrainer") == true){
                oppTrainer = arguments?.getSerializable("oppTrainer") as Trainer
            }

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

                handleSwap(pokemon)
                handleUI(pokemon)
                //after press
                val fragment = BattleMenuFragment()
                val dataToSend = Bundle()
                dataToSend.putSerializable("trainer", trainer)
                dataToSend.putSerializable("opponent", opponent)
                if(arguments?.containsKey("oppTrainer") == true){
                    dataToSend.putSerializable("oppTrainer", oppTrainer)
                }
                fragment.arguments = dataToSend
                val fragmentManager = parentFragmentManager
                fragmentManager.commit {
                    replace(R.id.battle_menu, fragment)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        }

        binding.bagCancelButton.setOnClickListener{
            activity?.onBackPressed()
        }

        return binding.root

    }

    fun handleSwap(pokemon: Pokemon){
        val index = trainer.pokemonTeam.pokemons.indexOf(pokemon)
        trainer.pokemonTeam.pokemons.removeAt(index)
        trainer.pokemonTeam.pokemons.add(0, pokemon)
    }

    fun handleUI(pokemon: Pokemon){
        val pokeImg: ImageView? = activity?.findViewById(R.id.trainer_pokemon)
        val pokeText: TextView? = activity?.findViewById(R.id.trainer_text_box)

        pokeImg?.load(pokemon.backSprite)
        pokeText?.text = "${pokemon.name} Lv${pokemon.level}\nHP: ${pokemon.currentHp}/${pokemon.baseStatMaxHp}"
    }
}