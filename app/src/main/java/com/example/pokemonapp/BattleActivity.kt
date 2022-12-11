package com.example.pokemonapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.pokemonapp.database.PokemonRoomDatabase
import com.example.pokemonapp.databinding.ActivityBattleBinding
import com.example.pokemonapp.objects.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private val TAG = "BATTLE"


class BattleActivity : AppCompatActivity() {
    lateinit var binding: ActivityBattleBinding
    private var utils: Utils = Utils()
    private lateinit var pokemonRoomDatabase: PokemonRoomDatabase
    private lateinit var opponent: Pokemon
    private lateinit var trainer : Trainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBattleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        trainer = intent.extras?.getSerializable("trainer") as Trainer

        //set up db
        pokemonRoomDatabase = PokemonRoomDatabase.getDatabase(applicationContext)

        Log.d(TAG, "trainer: ${trainer.pokemonTeam.pokemons}")

        getOpponent()
//        Log.d(TAG, "main: opponent: ${opponent?.name}")
        val backUri = Uri.parse(trainer.pokemonTeam.pokemons[0].backSprite)
        binding.trainerPokemon.load(backUri)

    }

    private fun getOpponent(){
        Log.d(TAG, "fetching opponent")
        val rand = java.util.Random()
        val pokemonToFetch: Int = rand.nextInt(151 - 1) + 1;
        Log.d(TAG, "fetching #$pokemonToFetch")
        var pokemon: Pokemon
        lifecycleScope.launch(Dispatchers.IO) {
            val databasePokemon = pokemonRoomDatabase.pokemonDao().getPokemonWithNumber(pokemonToFetch.toString())

            if(databasePokemon == null){
                        //fetching pokemon from api
                        val jsonObject = Utils().getPokemonJSON(pokemonToFetch.toString())
                        var moveList = ArrayList<Move>()
                        if (jsonObject != null) {
                            for (move in jsonObject.get("moves").asJsonArray) {
                                val jsonObjectMove =
                                    Utils().getMoveJSON(move.asJsonObject.get("move").asString)
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
                            for (type in jsonObject.get("types").asJsonArray) {
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
//                        trainer.addPokemon(pokemon!!)
                        SaveToDatabase(pokemon, moveList)
                            //change ui
                            withContext(Dispatchers.Main){

                                setOpponent(pokemon)
                                handleTextBoxes(trainer.pokemonTeam.pokemons[0], pokemon)
                            }
                        }
                } else {
                    //setting pokemon from db
                    pokemon = Pokemon(
                    databasePokemon.pokemonNumber,
                    databasePokemon.species,
                    databasePokemon.pokemonBaseStateAttack,
                    databasePokemon.pokemonBaseStatDefense,
                    databasePokemon.pokemonBaseStatSpecialAttack,
                    databasePokemon.pokemonBaseStatSpecialDefense,
                    databasePokemon.pokemonBaseStateMaxHp,
                    databasePokemon.pokemonBaseStatSpeed,
                    databasePokemon.baseExperienceReward,
                    databasePokemon.types,
                    databasePokemon.frontSprite,
                    databasePokemon.backSprite,
                    databasePokemon.pokemonMoves
                )
                    //change ui
                    withContext(Dispatchers.Main){
                        setOpponent(pokemon)
                        handleTextBoxes(trainer.pokemonTeam.pokemons[0], pokemon)
                    }
                }


        }
    }

    // Saving to database
    private fun SaveToDatabase(pokemon: Pokemon, moveList: List<Move>){
        lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                moveList.forEach {
                    var pokemonWithMove = PokemonMoveRef(pokemon.pokemonNumber, it.name)
                    pokemonRoomDatabase.pokemonWithMoves().insert(pokemonWithMove)
                    pokemonRoomDatabase.moveDao().insert(it)
                }
                pokemonRoomDatabase.pokemonDao().insert(pokemon)
            }
        }
    }

    //Setting opponent
    private fun setOpponent(pokemon: Pokemon){

        var highestLevel: Int = 0
        var lowestLevel: Int = 1
        //getting highest level pokemon on trainer team
        trainer.pokemonTeam.pokemons.forEach {  poke ->
            if(poke.level > highestLevel){
                highestLevel = poke.level
            }
        }
        trainer.pokemonTeam.pokemons.forEach {  poke ->
            if(poke.level < highestLevel){
                lowestLevel = poke.level
            }
        }
        var oppLevel = ((lowestLevel - 5)..(highestLevel + 5)).random()
        if(oppLevel <= 0){
            oppLevel = 1
        }
        pokemon.updateLevel(oppLevel)
        Log.d("OPPONENT_POKE", "CURRENT HP: ${pokemon.currentHp} || MAX HP: ${pokemon.baseStatMaxHp}")
        val frontUri = Uri.parse(pokemon.frontSprite)
        binding.enemyPokemon.load(frontUri)
        opponent = pokemon
        Log.d(TAG, "fetched: ${opponent.name}")

        handleBattleMenu(trainer, pokemon)
    }

    // Handling text boxes
    private fun handleTextBoxes(trainer: Pokemon, opponent: Pokemon){
        val oppName = opponent.name
        val oppLevel = opponent.level
        val oppHp = opponent.currentHp
        val oppMaxHp = opponent.baseStatMaxHp
        val oppText = "$oppName Lv$oppLevel\nHP: $oppHp/$oppMaxHp"
        binding.enemyTextBox.text = oppText

        val name = trainer.name
        val level = trainer.level
        val Hp = trainer.currentHp
        val maxHp = trainer.baseStatMaxHp
        val text = "$name Lv$level\nHP: $Hp/$maxHp"
        binding.trainerTextBox.text = text

    }

    // Handling the battle menu
    private fun handleBattleMenu(trainer:Trainer, opponent:Pokemon){
        val fragment: BattleMenuFragment = BattleMenuFragment()
        val dataToSend: Bundle = Bundle()
        dataToSend.putSerializable("trainer", trainer)
        dataToSend.putSerializable("opponent", opponent)
        fragment.arguments = dataToSend
        Log.d(TAG, "main: ${fragment.requireArguments().size()}")
            supportFragmentManager.commit {
                replace(R.id.battle_menu, fragment)
                setReorderingAllowed(true)
                addToBackStack(null)
            }

    }
}
