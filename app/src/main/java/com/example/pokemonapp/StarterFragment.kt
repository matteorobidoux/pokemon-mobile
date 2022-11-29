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
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import com.example.pokemonapp.databinding.StarterFragmentBinding
import com.google.android.material.button.MaterialButton
import com.google.gson.JsonElement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StarterFragment : Fragment(){
    private var utils: Utils = Utils()
    private lateinit var formActivtiy: FormActivity
    private var pokemonTeam = PokemonTeam()
    private lateinit var trainer: Trainer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        formActivtiy = activity as FormActivity
        val binding = StarterFragmentBinding.inflate(inflater,container,false)

        setFragmentResultListener("requestKey") {key, bundle ->
            trainer = bundle.getSerializable("trainer") as Trainer
            trainer.setPokemonTeam(pokemonTeam)
        }
        //TODO Receive trainer from name fragment and set his values
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
                retrievePokemon("charmander")
                var menuIntent = Intent(activity, MenuActivity::class.java)
                menuIntent.putExtra("trainer", trainer)
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
                retrievePokemon("squirtle")
                var menuIntent = Intent(activity, MenuActivity::class.java)
                menuIntent.putExtra("trainer", trainer)
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
                retrievePokemon("bulbasaur")
                var menuIntent = Intent(activity, MenuActivity::class.java)
                menuIntent.putExtra("trainer", trainer)
                formActivtiy.startActivity(menuIntent)
            }

            dialogView.findViewById<MaterialButton>(R.id.no_pokemon).setOnClickListener{
                alert.dismiss()
            }
        }
        return binding.root
    }


    private fun getTypes(types: JsonElement) : ArrayList<String>{
        var typelist = ArrayList<String>()
        for(type in types.asJsonArray){
            typelist.add(type.asString)
        }
        return typelist
    }

    private fun retrievePokemon(data : String){
        var pokemon: Pokemon
        lifecycleScope.launch(Dispatchers.IO) {
            val jsonObject = Utils().getPokemonJSON(data)

            withContext(Dispatchers.IO) {
                var moveList = ArrayList<Move>()
                if (jsonObject != null) {
                    for(move in jsonObject.get("moves").asJsonArray){
                        val jsonObjectMove = Utils().getMoveJSON(move.asJsonObject.get("move").asString)
                        if (jsonObjectMove != null) {
                            var pokemonMove = Move(
                                jsonObjectMove.get("name").asString,
                                move.asJsonObject.get("level_learned_at").asInt,
                                jsonObjectMove.get("accuracy").asInt,
                                jsonObjectMove.get("ailment").asString,
                                jsonObjectMove.get("ailmentChance").asInt,
                                jsonObjectMove.get("category").asString,
                                jsonObjectMove.get("damageClass").asString,
                                jsonObjectMove.get("heal").asInt,
                                jsonObjectMove.get("maxPP").asInt,
                                jsonObjectMove.get("power").asInt,
                                jsonObjectMove.get("target").asString,
                                jsonObjectMove.get("type").asString
                            )
                            moveList.add(pokemonMove)
                        }
                    }
                    var typeList = ArrayList<String>()
                    for(type in jsonObject.get("types").asJsonArray){
                        typeList.add(type.asString)
                    }
                    pokemon = Pokemon(
                        jsonObject.get("pokemonNumber").asString,
                        jsonObject.get("species").asString,
                        jsonObject.get("baseStateAttack").asInt,
                        jsonObject.get("baseStatDefense").asInt,
                        jsonObject.get("baseStatSpecialAttack").asInt,
                        jsonObject.get("baseStatSpecialDefense").asInt,
                        jsonObject.get("baseStateMaxHp").asInt,
                        jsonObject.get("baseStatSpeed").asInt,
                        jsonObject.get("baseExperienceReward").asInt,
                        typeList,
                        jsonObject.get("sprites").asJsonObject.get("front").asString,
                        jsonObject.get("sprites").asJsonObject.get("back").asString,
                        moveList
                    )
                    pokemonTeam.pokemons.add(pokemon!!)
                }
            }
        }
    }
}