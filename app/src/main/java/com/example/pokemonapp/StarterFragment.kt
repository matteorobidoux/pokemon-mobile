package com.example.pokemonapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pokemonapp.databinding.StarterFragmentBinding
import com.google.android.material.button.MaterialButton
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.json.JSONObject

class StarterFragment : Fragment(){
    private var utils: Utils = Utils()
    private lateinit var formActivtiy: FormActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        formActivtiy = activity as FormActivity
        val binding = StarterFragmentBinding.inflate(inflater,container,false)
        formActivtiy.setText("To start your adventure, you must first chose your starter pokemon!")
        binding.firePokeball.setOnClickListener{
            var builder = AlertDialog.Builder(context)
            var dialogInflater = layoutInflater
            var dialogView = dialogInflater.inflate(R.layout.pokemon_dialog, null)
            dialogView.findViewById<TextView>(R.id.pokemon_dialog_check).text = "Do you want Charmander as your starter?"
            dialogView.findViewById<ImageView>(R.id.dialog_pokemon).setImageResource(R.drawable.charmander)

            builder.setView(dialogView)
            var alert = builder.create()

            alert.show()

            dialogView.findViewById<MaterialButton>(R.id.yes_pokemon).setOnClickListener{
//                var starterPokemon = getPokemon("charmander")
//                var pokemonTeam = PokemonTeam()
//                pokemonTeam?.pokemons?.add(starterPokemon)
                var menuIntent = Intent(activity, MenuActivity::class.java)
                formActivtiy.startActivity(menuIntent)
            }

            dialogView.findViewById<MaterialButton>(R.id.no_pokemon).setOnClickListener{
                alert.dismiss()
            }
        }

        binding.waterPokeball.setOnClickListener{
            var builder = AlertDialog.Builder(context)
            var dialogInflater = layoutInflater
            var dialogView = dialogInflater.inflate(R.layout.pokemon_dialog, null)
            dialogView.findViewById<TextView>(R.id.pokemon_dialog_check).text = "Do you want Squirtle as your starter?"
            dialogView.findViewById<ImageView>(R.id.dialog_pokemon).setImageResource(R.drawable.squirtle)

            builder.setView(dialogView)
            var alert = builder.create()

            alert.show()

            dialogView.findViewById<MaterialButton>(R.id.yes_pokemon).setOnClickListener{
//                var starterPokemon = getPokemon("squirtle")
//                var pokemonTeam = PokemonTeam()
//                pokemonTeam?.pokemons?.add(starterPokemon)
                var menuIntent = Intent(activity, MenuActivity::class.java)
                formActivtiy.startActivity(menuIntent)
            }

            dialogView.findViewById<MaterialButton>(R.id.no_pokemon).setOnClickListener{
                alert.dismiss()
            }
        }

        binding.grassPokeball.setOnClickListener{
            var builder = AlertDialog.Builder(context)
            var dialogInflater = layoutInflater
            var dialogView = dialogInflater.inflate(R.layout.pokemon_dialog, null)
            dialogView.findViewById<TextView>(R.id.pokemon_dialog_check).text = "Do you want Bulbasaur as your starter?"
            dialogView.findViewById<ImageView>(R.id.dialog_pokemon).setImageResource(R.drawable.bulbasaur)

            builder.setView(dialogView)
            var alert = builder.create()

            alert.show()

            dialogView.findViewById<MaterialButton>(R.id.yes_pokemon).setOnClickListener{
                var pokemon = getPokemon("bulbasaur")
                Log.d("TAG", pokemon.toString())
                var pokemonTeam = PokemonTeam()
                pokemonTeam?.pokemons?.add(pokemon)
                var menuIntent = Intent(activity, MenuActivity::class.java)
                formActivtiy.startActivity(menuIntent)
            }

            dialogView.findViewById<MaterialButton>(R.id.no_pokemon).setOnClickListener{
                alert.dismiss()
            }
        }
        return binding.root
    }

    private fun getPokemon(pokemon: String): Pokemon {
        val jsonFileString = context?.let { utils.getJsonData(it, "$pokemon.json") }
        val jsonParser = JsonParser()
        var jsonObject = jsonParser.parse(jsonFileString).asJsonObject
        var pokemon = Pokemon(jsonObject.get("baseExperienceReward").asInt, jsonObject.get("baseStateAttack").asInt, jsonObject.get("baseStatDefense").asInt, jsonObject.get("baseStateMaxHp").asInt, jsonObject.get("baseStatSpecialAttack").asInt, jsonObject.get("baseStatSpecialDefense").asInt, jsonObject.get("baseStatSpeed").asInt, jsonObject.get("pokemonNumber").asInt, jsonObject.get("species").asString, getTypes(jsonObject.get("types")), getMoves(jsonObject.get("moves")))
        return pokemon
    }

    private fun getMoves(moves: JsonElement) : ArrayList<Move>{
        var moveList = ArrayList<Move>()
        for(move in moves.asJsonArray){
            val jsonFileString = context?.let { utils.getJsonData(it, "${move.asJsonObject.get("move").asString}.json") }
            val jsonParser = JsonParser()
            var jsonObject = jsonParser.parse(jsonFileString).asJsonObject
            if(jsonObject.get("ailment") == null){
                var pokemonMove = Move(move.asJsonObject.get("level_learned_at").asInt, jsonObject.get("accuracy").asInt, "Nothing", jsonObject.get("ailmentChance").asInt, jsonObject.get("category").asString, jsonObject.get("damageClass").asString, jsonObject.get("heal").asInt, jsonObject.get("maxPP").asInt, jsonObject.get("name").asString, jsonObject.get("power").asInt, jsonObject.get("target").asString, jsonObject.get("type").asString)
                moveList.add(pokemonMove)
            } else {
                var pokemonMove = Move(move.asJsonObject.get("level_learned_at").asInt, jsonObject.get("accuracy").asInt, jsonObject.get("ailment").asString, jsonObject.get("ailmentChance").asInt, jsonObject.get("category").asString, jsonObject.get("damageClass").asString, jsonObject.get("heal").asInt, jsonObject.get("maxPP").asInt, jsonObject.get("name").asString, jsonObject.get("power").asInt, jsonObject.get("target").asString, jsonObject.get("type").asString)
                moveList.add(pokemonMove)
            }
        }
        return moveList
    }

    private fun getTypes(types: JsonElement) : ArrayList<String>{
        var typelist = ArrayList<String>()
        for(type in types.asJsonArray){
            typelist.add(type.asString)
        }
        return typelist
    }
}