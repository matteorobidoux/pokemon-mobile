package com.example.pokemonapp

import android.content.Intent
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
import com.example.pokemonapp.databinding.BagFragmentBinding
import com.example.pokemonapp.databinding.BattleStartFragmentBinding
import com.example.pokemonapp.objects.Items
import com.example.pokemonapp.objects.Pokemon
import com.example.pokemonapp.objects.Trainer

class BagFragment : Fragment(){
    private val TAG = "BAG_FRAGMENT"
    lateinit var trainer: Trainer
    lateinit var oppTrainer: Trainer
    lateinit var opponent: Pokemon
    lateinit var adapter: ItemRecyclerViewAdapter
    lateinit var binding: BagFragmentBinding
    private lateinit var battleType: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BagFragmentBinding.inflate(inflater,container,false)



        Log.d(TAG, "arguments: ${arguments?.size()}")

        if(arguments != null){
            val trainer: Trainer = arguments?.getSerializable("trainer") as Trainer

            if(arguments?.containsKey("oppTrainer") == true){
                oppTrainer = arguments?.getSerializable("oppTrainer") as Trainer
                battleType = "TRAINER"
            }else {
                battleType = "WILD"
            }
            //TODO implement entire team
            val activePokemon = trainer.pokemonTeam.pokemons[0]
            val opponent: Pokemon = arguments?.getSerializable("opponent") as Pokemon

            Log.d(TAG, "GOT TRAINER: ${trainer.trainerName}")
            //recycler view Setup
            val activity = activity
            val recyclerView: RecyclerView = binding.items
            if (activity != null) {
                adapter = ItemRecyclerViewAdapter(activity.applicationContext, trainer.items)
            }
            val layoutManager: GridLayoutManager = GridLayoutManager(activity?.applicationContext, 2)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter

            val trainerTv: TextView? = activity?.findViewById(R.id.trainer_text_box)
            val opponentImage: ImageView? = activity?.findViewById(R.id.enemy_pokemon)
            //set up fragment on click
            adapter.onItemClick = { item ->
                Log.d(TAG, "clicked ${item.name} || quantity: ${item.quantity} || desc: ${item.description}")

                if(item.quantity > 0){
                    when(item.name){
                        "potion" -> handleHeal(item,activePokemon,trainerTv)
                        "pokeball" -> {
                            when(battleType){
                                "TRAINER" -> { Toast.makeText(activity?.applicationContext, "CANNOT CATCH TRAINER POKEMON", Toast.LENGTH_SHORT).show()}
                                "WILD" -> {handleCatch(item, opponent, trainer, opponentImage) }
                            }
                        }
                    }
                }

                //after press
                activity?.onBackPressed()
            }
        }

        binding.bagCancelButton.setOnClickListener{
            activity?.onBackPressed()
        }

        return binding.root

    }

    private fun handleHeal(potion: Items, pokemon: Pokemon, textView: TextView?){
        if(pokemon.currentHp < pokemon.baseStatMaxHp){
            pokemon.currentHp += 20
            Toast.makeText(activity?.applicationContext, "${pokemon.name} was healed by 20!", Toast.LENGTH_SHORT).show()
            potion.quantity -= 1
            textView?.text = "${pokemon.name} Lv${pokemon.level}\nHP: ${pokemon.currentHp}/${pokemon.baseStatMaxHp}"
        } else {
            Toast.makeText(activity?.applicationContext, "Cant Heal Pokemon already max HP", Toast.LENGTH_SHORT).show()
        }

    }

    private fun handleCatch(pokeball: Items, pokemon: Pokemon, trainer: Trainer, imageView: ImageView?){
        val chance: Double = 100.0 * ( 1.0 - ((pokemon.currentHp).toDouble() / (pokemon.baseStatMaxHp).toDouble()))
        val range = (1..100).random()

        if(chance >= range){
            //catches
            Toast.makeText(activity?.applicationContext, "Caught ${pokemon.name}", Toast.LENGTH_SHORT).show()
            val bundle = Bundle()
            bundle.putSerializable("trainer", trainer)
            bundle.putSerializable("pokemon", pokemon)
            val rename = RenameFragment()
            rename.arguments = bundle
            Log.d(TAG, "arguments passing: ${rename.arguments}")
            rename.show(parentFragmentManager, "Rename")

            Log.d(TAG, "trainer team size: ${trainer.pokemonTeam.pokemons.size}")

        } else {
            Toast.makeText(activity?.applicationContext, "PokeBall missed", Toast.LENGTH_SHORT).show()
            //misses
        }

        pokeball.quantity -= 1
        Log.d(TAG, "quantity = ${pokeball.quantity}")
    }
}