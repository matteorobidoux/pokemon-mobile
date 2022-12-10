package com.example.pokemonapp

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokemonapp.database.PokemonRoomDatabase
import com.example.pokemonapp.databinding.ActivityTrainerBattleBinding
import com.example.pokemonapp.objects.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrainerBattleActivity : AppCompatActivity() {
    private var TAG = "OPPONENT_TRAINER"
    lateinit var binding: ActivityTrainerBattleBinding
    private var utils: Utils = Utils()
    private lateinit var pokemonRoomDatabase: PokemonRoomDatabase
    private lateinit var opponent: Trainer
    private lateinit var opponentPokemon: Pokemon
    private lateinit var trainer : Trainer
    private lateinit var adapter : PokeballsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainerBattleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        trainer = intent.extras?.getSerializable("trainer") as Trainer
        opponent = Trainer("Ash")

        //set up db
        pokemonRoomDatabase = PokemonRoomDatabase.getDatabase(applicationContext)

        Log.d(TAG, "trainer: ${trainer.pokemonTeam.pokemons}")
        generateTrainer()
        val backUri = Uri.parse(trainer.pokemonTeam.pokemons[0].backSprite)
        binding.trainerPokemon.load(backUri)
    }

    private fun generateTrainer(){

        val teamSize: Int = (1 .. 6).random()

        if(teamSize > 1){
            for(i in (1..teamSize)){
                getOpponent()
            }
        }else {
            getOpponent()
        }
    }

    private fun handlePokeballRecycler(){
        Log.d(TAG, "opponent team size: ${opponent.pokemonTeam.pokemons.size}")
        val recyclerView: RecyclerView = binding.pokeballs
        adapter = PokeballsRecyclerViewAdapter(applicationContext, opponent.pokemonTeam.pokemons)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        handleBattleMenu(trainer, opponentPokemon, opponent)

    }

    //generate a trainer with up to six pokemon
    @SuppressLint("SuspiciousIndentation")
    private fun getOpponent(){
        Log.d(TAG, "fetching opponent")
        val pokemonToFetch: Int = (1..150).random()
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
                    SaveToDatabase(pokemon, moveList)
                    //change ui
                    withContext(Dispatchers.Main){
                        setOpponent(pokemon)
                        handleTextBoxes(trainer.pokemonTeam.pokemons[0], pokemon)
                        handlePokeballRecycler()

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
                    handlePokeballRecycler()

                }
            }
        }
    }

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

    private fun handleBattleMenu(trainer: Trainer, opponent: Pokemon, oppTrainer: Trainer){
        val fragment: BattleMenuFragment = BattleMenuFragment()
        val dataToSend: Bundle = Bundle()
        dataToSend.putSerializable("trainer", trainer)
        dataToSend.putSerializable("opponent", opponent)
        dataToSend.putSerializable("oppTrainer", oppTrainer)
        fragment.arguments = dataToSend
        supportFragmentManager.commit {
            replace(R.id.battle_menu, fragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }

    }

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
        val frontUri = Uri.parse(pokemon.frontSprite)
        binding.enemyPokemon.load(frontUri)
        opponent.addPokemon(pokemon)
        opponentPokemon = opponent.pokemonTeam.pokemons.last()

    }
}
