package com.example.pokemonapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.FragmentFightBinding
import com.example.pokemonapp.objects.Move
import com.example.pokemonapp.objects.Pokemon
import com.example.pokemonapp.objects.Trainer
import kotlin.random.Random
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive


class FightFragment : Fragment() {
    private val TAG = "BATTLE_FIGHT_FRAGMENT"
    private lateinit var adapter: MoveRecyclerViewAdapter
    private lateinit var list: MutableList<Move>
    private val typeCharString: String =  """{
  "normal": {
    "normal": 1,
    "fire": 1,
    "water": 1,
    "electric": 1,
    "grass": 1,
    "ice": 1,
    "fighting": 1,
    "poison": 1,
    "ground": 1,
    "flying": 1,
    "psychic": 1,
    "bug": 1,
    "rock": 0.5,
    "ghost": 0,
    "dragon": 1,
    "dark": 1,
    "steel": 0.5
  },
  "fire": {
    "normal": 1,
    "fire": 0.5,
    "water": 0.5,
    "electric": 1,
    "grass": 2,
    "ice": 2,
    "fighting": 1,
    "poison": 1,
    "ground": 1,
    "flying": 1,
    "psychic": 1,
    "bug": 2,
    "rock": 0.5,
    "ghost": 1,
    "dragon": 0.5,
    "dark": 1,
    "steel": 2
  },
  "water": {
    "normal": 1,
    "fire": 2,
    "water": 0.5,
    "electric": 1,
    "grass": 0.5,
    "ice": 1,
    "fighting": 1,
    "poison": 1,
    "ground": 2,
    "flying": 1,
    "psychic": 1,
    "bug": 1,
    "rock": 2,
    "ghost": 1,
    "dragon": 0.5,
    "dark": 1,
    "steel": 1
  },
  "electric": {
    "normal": 1,
    "fire": 1,
    "water": 2,
    "electric": 0.5,
    "grass": 0.5,
    "ice": 1,
    "fighting": 1,
    "poison": 1,
    "ground": 0,
    "flying": 2,
    "psychic": 1,
    "bug": 1,
    "rock": 1,
    "ghost": 1,
    "dragon": 0.5,
    "dark": 1,
    "steel": 1
  },
  "grass": {
    "normal": 1,
    "fire": 0.5,
    "water": 2,
    "electric": 1,
    "grass": 0.5,
    "ice": 1,
    "fighting": 1,
    "poison": 0.5,
    "ground": 2,
    "flying": 0.5,
    "psychic": 1,
    "bug": 0.5,
    "rock": 2,
    "ghost": 1,
    "dragon": 0.5,
    "dark": 1,
    "steel": 0.5
  },
  "ice": {
    "normal": 1,
    "fire": 0.5,
    "water": 0.5,
    "electric": 1,
    "grass": 2,
    "ice": 0.5,
    "fighting": 1,
    "poison": 1,
    "ground": 2,
    "flying": 2,
    "psychic": 1,
    "bug": 1,
    "rock": 1,
    "ghost": 1,
    "dragon": 2,
    "dark": 1,
    "steel": 0.5
  },
  "fighting": {
    "normal": 2,
    "fire": 1,
    "water": 1,
    "electric": 1,
    "grass": 1,
    "ice": 2,
    "fighting": 1,
    "poison": 0.5,
    "ground": 1,
    "flying": 0.5,
    "psychic": 0.5,
    "bug": 0.5,
    "rock": 2,
    "ghost": 0,
    "dragon": 1,
    "dark": 2,
    "steel": 2
  },
  "poison": {
    "normal": 1,
    "fire": 1,
    "water": 1,
    "electric": 1,
    "grass": 2,
    "ice": 1,
    "fighting": 1,
    "poison": 0.5,
    "ground": 0.5,
    "flying": 1,
    "psychic": 1,
    "bug": 1,
    "rock": 0.5,
    "ghost": 0.5,
    "dragon": 1,
    "dark": 1,
    "steel": 0
  },
  "ground": {
    "normal": 1,
    "fire": 2,
    "water": 1,
    "electric": 2,
    "grass": 0.5,
    "ice": 1,
    "fighting": 1,
    "poison": 2,
    "ground": 1,
    "flying": 0,
    "psychic": 1,
    "bug": 0.5,
    "rock": 2,
    "ghost": 1,
    "dragon": 1,
    "dark": 1,
    "steel": 2
  },
  "flying": {
    "normal": 1,
    "fire": 1,
    "water": 1,
    "electric": 0.5,
    "grass": 2,
    "ice": 1,
    "fighting": 2,
    "poison": 1,
    "ground": 1,
    "flying": 1,
    "psychic": 1,
    "bug": 2,
    "rock": 0.5,
    "ghost": 1,
    "dragon": 1,
    "dark": 1,
    "steel": 0.5
  },
  "psychic": {
    "normal": 1,
    "fire": 1,
    "water": 1,
    "electric": 1,
    "grass": 1,
    "ice": 1,
    "fighting": 2,
    "poison": 2,
    "ground": 1,
    "flying": 1,
    "psychic": 0.5,
    "bug": 1,
    "rock": 1,
    "ghost": 1,
    "dragon": 1,
    "dark": 0,
    "steel": 0.5
  },
  "bug": {
    "normal": 1,
    "fire": 0.5,
    "water": 1,
    "electric": 1,
    "grass": 2,
    "ice": 1,
    "fighting": 0.5,
    "poison": 0.5,
    "ground": 1,
    "flying": 0.5,
    "psychic": 2,
    "bug": 1,
    "rock": 1,
    "ghost": 0.5,
    "dragon": 1,
    "dark": 2,
    "steel": 0.5
  },
  "rock": {
    "normal": 0.5,
    "fire": 2,
    "water": 1,
    "electric": 1,
    "grass": 1,
    "ice": 2,
    "fighting": 0.5,
    "poison": 1,
    "ground": 0.5,
    "flying": 2,
    "psychic": 1,
    "bug": 2,
    "rock": 1,
    "ghost": 1,
    "dragon": 1,
    "dark": 1,
    "steel": 0.5
  },
  "ghost": {
    "normal": 0,
    "fire": 1,
    "water": 1,
    "electric": 1,
    "grass": 1,
    "ice": 1,
    "fighting": 1,
    "poison": 1,
    "ground": 1,
    "flying": 1,
    "psychic": 2,
    "bug": 1,
    "rock": 1,
    "ghost": 2,
    "dragon": 1,
    "dark": 0.5,
    "steel": 0.5
  },
  "dragon": {
    "normal": 1,
    "fire": 1,
    "water": 1,
    "electric": 1,
    "grass": 1,
    "ice": 1,
    "fighting": 1,
    "poison": 1,
    "ground": 1,
    "flying": 1,
    "psychic": 1,
    "bug": 1,
    "rock": 1,
    "ghost": 1,
    "dragon": 2,
    "dark": 1,
    "steel": 0.5
  },
  "dark": {
    "normal": 1,
    "fire": 1,
    "water": 1,
    "electric": 1,
    "grass": 1,
    "ice": 1,
    "fighting": 0.5,
    "poison": 1,
    "ground": 1,
    "flying": 1,
    "psychic": 2,
    "bug": 1,
    "rock": 1,
    "ghost": 2,
    "dragon": 1,
    "dark": 0.5,
    "steel": 0.5
  },
  "steel": {
    "normal": 1,
    "fire": 0.5,
    "water": 0.5,
    "electric": 0.5,
    "grass": 1,
    "ice": 2,
    "fighting": 1,
    "poison": 1,
    "ground": 1,
    "flying": 1,
    "psychic": 1,
    "bug": 1,
    "rock": 2,
    "ghost": 1,
    "dragon": 1,
    "dark": 1,
    "steel": 0.5
  }
}"""
    private lateinit var typeChart: JsonObject


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentFightBinding.inflate(inflater,container,false)

        val gson = Gson()
        typeChart = gson.fromJson(typeCharString, JsonObject::class.java)

        val types = typeChart.getAsJsonObject("normal").get("normal")

        Log.d(TAG, "arguments: ${arguments?.size()} || types json: $types")

        if(arguments != null){
            val trainer: Trainer = arguments?.getSerializable("trainer") as Trainer
            val opponent: Pokemon = arguments?.getSerializable("opponent") as Pokemon
            val activePokemon: Pokemon = trainer.pokemonTeam.pokemons[0]

            Log.d(TAG, "GOT TRAINER: ${trainer.trainerName}")
            //recycler view Setup
            val activity = activity
            val recyclerView: RecyclerView = binding.moves
            if (activity != null) {
                adapter = MoveRecyclerViewAdapter(activity.applicationContext, activePokemon.moves)
            }
            val layoutManager: GridLayoutManager = GridLayoutManager(activity?.applicationContext, 2)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            //set up fragment on click
            adapter.onItemClick = { move ->
                Log.d(TAG, "clicked ${move.name} || acc: ${move.accuracy} || damageClass: ${move.damageClass} || damage: ${move.power} || type: ${move.type}")
                handleFight(activePokemon, opponent, move)
            }
        }

        binding.fightCancelButton.setOnClickListener{
            activity?.onBackPressed()
        }


        return binding.root
    }

    private fun handleFight(trainer: Pokemon, opponent: Pokemon, move: Move){
        var baseDamage = 0
        //handling accuracy
        val isMiss = Random.nextInt(101)
        //move lands
        if(isMiss <= move.accuracy){
            Log.d(TAG, "${move.name} Landed!")
            // trainer goes first
            if(trainer.baseStatSpeed > opponent.baseStatSpeed){
                Log.d(TAG, "Trainer goes first!")
                val damage = computeDamage(move, trainer, opponent)


            } else {
                //opponent goes first
                Log.d(TAG, "Opponent goes first!")

            }
        }else {
            Toast.makeText(activity?.applicationContext, "move missed", Toast.LENGTH_SHORT).show()
        }


    }

    private fun computeDamage(move:Move, attacker: Pokemon, defender: Pokemon): Int{
        var baseDamage : Double = 0.00
        when(move.damageClass){
            "PHYSICAL" -> {
                Log.d(TAG, "move is physical")
                baseDamage = ((0.05) * ( (((2*attacker.level)/5) + 2))) * (((move.power * attacker.baseStatAttack) / defender.baseStatDefense) + 2)
            }
            "SPECIAL" -> {
                Log.d(TAG, "move is special")
                baseDamage = ((0.05) * (((2*attacker.level)/5) + 2)) * (((move.power * attacker.baseStatSpecialAttack) / defender.baseStatSpecialDefense) + 2)
            }
            else -> {
                //do nothing
            }
        }

        defender.types.forEach{ type ->
            if(type == move.type){
                baseDamage *= 1.5
            }
        }

        //handler type multiplier
        handleTypeMultiplier(move, defender)

        return baseDamage.toInt()
    }

    //move is attacker move and defender is defender type
    private fun handleTypeMultiplier(move: Move, defender: Pokemon): Double{
        var multiplier = 1.0
        defender.types.forEach{ type ->
            multiplier *= typeChart.getAsJsonObject(type).get(move.type).asDouble
            Log.d(TAG, "Defender Types: $type")
        }
        Log.d(TAG, "multplier = $multiplier || move type: ${move.type}")
        return multiplier
    }
}