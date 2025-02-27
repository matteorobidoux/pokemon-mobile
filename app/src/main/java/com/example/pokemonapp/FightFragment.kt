package com.example.pokemonapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.FragmentFightBinding
import com.example.pokemonapp.objects.Move
import com.example.pokemonapp.objects.Pokemon
import com.example.pokemonapp.objects.Trainer
import kotlin.random.Random
import com.google.gson.Gson
import com.google.gson.JsonObject


class FightFragment : Fragment() {
    private val TAG = "BATTLE_FIGHT_FRAGMENT"
    private lateinit var adapter: MoveRecyclerViewAdapter
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
    private lateinit var trainer: Trainer
    private lateinit var oppTrainer: Trainer
    private lateinit var opponent: Pokemon
    private lateinit var battleType: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentFightBinding.inflate(inflater,container,false)

        val gson = Gson()
        typeChart = gson.fromJson(typeCharString, JsonObject::class.java)

        val types = typeChart.getAsJsonObject("normal").get("normal")

        if(arguments != null){
            trainer = arguments?.getSerializable("trainer") as Trainer
            if(arguments?.containsKey("oppTrainer") == true){
                oppTrainer = arguments?.getSerializable("oppTrainer") as Trainer

            }
            battleType = arguments?.getString("battleType") as String

            if(battleType == "TRAINER"){
                opponent = oppTrainer.pokemonTeam.pokemons.last()

            } else {
                opponent= arguments?.getSerializable("opponent") as Pokemon
            }

            val activePokemon: Pokemon = trainer.pokemonTeam.pokemons[0]

            Log.d("LEVEL", "OPPONENT IS : ${opponent.name}")
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
                // check if pokemon are alive
                handleFight(activePokemon, opponent, move)



                val fragment = BattleMenuFragment()
                val dataToSend = Bundle()
                dataToSend.putSerializable("trainer", trainer)
                if(arguments?.containsKey("oppTrainer") == true){
                    dataToSend.putSerializable("oppTrainer", oppTrainer)
                }
                dataToSend.putSerializable("opponent", opponent)
                dataToSend.putString("battleType", battleType)
                fragment.arguments = dataToSend
                val fragmentManager = parentFragmentManager
                fragmentManager.commit {
                    replace(R.id.battle_menu, fragment)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        }

        binding.fightCancelButton.setOnClickListener{
            activity?.onBackPressed()
        }
        return binding.root
    }

    // Handle fight functionality
    private fun handleFight(trainer: Pokemon, opponent: Pokemon, move: Move){
        //handling accuracy
        val isMiss = Random.nextInt(101)

        if(trainer.baseStatSpeed > opponent.baseStatSpeed){
            if(trainer.currentHp > 0){
                //alive

                // trainer goes first
                Log.d(TAG, "Trainer goes first!")
                if(isMiss <= move.accuracy){
                    //landed
                    Log.d(TAG, "${move.name} Landed!")
                    val damage = computeDamage(move, trainer, opponent)
                    val opponentTv: TextView? = activity?.findViewById(R.id.enemy_text_box)
                    if(!isFaint(damage, opponent)){
                        handleHit(damage, opponent, opponentTv)
                    }else {
                        //opponent fainted
                        handleFaint(opponent, opponentTv)
                        //handle win
                        handleWin(trainer, opponent, battleType)
                    }
                } else {
                    //missed
                    Toast.makeText(activity?.applicationContext, "move missed", Toast.LENGTH_SHORT).show()
                }

                //opponent turn
                //choose random move for opponent
                val oppMove: Move = handleOpponentMove(opponent)
                if(isMiss <= oppMove.accuracy){
                    //landed
                    Log.d(TAG, "Opponent ${oppMove.name} Landed!")
                    val damage = computeDamage(oppMove, opponent, trainer)
                    val trainerTv: TextView? = activity?.findViewById(R.id.trainer_text_box)
                    if(!isFaint(damage,trainer)){
                        handleHit(damage, trainer, trainerTv)
                    } else {
                        //trainer fainted
                        handleFaint(trainer, trainerTv)
                    }

                } else {
                    //missed
                    Toast.makeText(activity?.applicationContext, "opponent missed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Log.d(TAG, "${trainer.name} is FAINTED")
            }

            } else {
                //opponent goes first
                if(opponent.currentHp > 0){
                    //is alive

                    Log.d(TAG, "Opponent goes first!")
                    //choose random move for opponent
                    val oppMove: Move = handleOpponentMove(opponent)
                    if(isMiss <= oppMove.accuracy){
                        //landed
                        Log.d(TAG, "Opponent ${oppMove.name} Landed!")
                        val damage = computeDamage(oppMove, opponent, trainer)
                        val trainerTv: TextView? = activity?.findViewById(R.id.trainer_text_box)
                        if(!isFaint(damage,trainer)){
                            handleHit(damage, trainer, trainerTv)
                        } else {
                            //trainer fainted
                            handleFaint(trainer, trainerTv)
                        }
                    } else {
                        //missed
                        Toast.makeText(activity?.applicationContext, "opponent missed", Toast.LENGTH_SHORT).show()
                    }

                    //trainer turn
                    if(isMiss <= move.accuracy){
                        //landed
                        Log.d(TAG, "${move.name} Landed!")
                        val damage = computeDamage(move, trainer, opponent)
                        val opponentTv: TextView? = activity?.findViewById(R.id.enemy_text_box)
                        if(!isFaint(damage, opponent)){
                            handleHit(damage, opponent, opponentTv)

                        }else {
                            //opponent fainted
                            handleFaint(opponent, opponentTv)
                            //handle win
                            handleWin(trainer, opponent, battleType)
                        }
                    } else {
                        //missed
                        Toast.makeText(activity?.applicationContext, "move missed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.d(TAG, "${opponent.name} is Fainted")
                }


            }
    }

    // Handle win functionality
    private fun handleWin(winner: Pokemon, opponent: Pokemon, battleType: String){
        val expGain : Int = (0.3 * opponent.baseExperienceReward * opponent.level).toInt()
        winner.calculateExperienceGained(opponent)
        Toast.makeText(activity?.applicationContext, "${winner.name} Gained $expGain!", Toast.LENGTH_SHORT).show()
        when(battleType){
                "WILD" -> {var menuIntent = Intent(activity, MenuActivity::class.java)
                    menuIntent.putExtra("trainer", trainer)
                    activity?.startActivity(menuIntent)}
                "TRAINER" -> {
                    if(oppTrainer.pokemonTeam.pokemons.size <= 1){
                        var menuIntent = Intent(activity, MenuActivity::class.java)
                        menuIntent.putExtra("trainer", trainer)
                        activity?.startActivity(menuIntent)
                    }
                }
        }

    }

    // Function checking if the Pokemon is faint
    private fun isFaint(damage: Int, pokemon:Pokemon): Boolean{
        if(pokemon.currentHp - damage <= 0){
            return true
        }
        return false
    }

    //Handling faint Pokemon
    private fun handleFaint(pokemon: Pokemon, pokemonTextBox: TextView?){
        Log.d("OPPONENT_TRAINER", "battle type: $battleType")
        when(battleType){
            "TRAINER" -> {

                //check if there are other pokemon in the party
                Log.d("LEVEL", "FAINTED: ${pokemon.name}")
                Toast.makeText(activity?.applicationContext, "${pokemon.name} HAS FAINTED", Toast.LENGTH_SHORT).show()
                val textBox: TextView? = activity?.findViewById(R.id.battle_text_box)
                textBox?.text = "${pokemon.name} has fainted!"
                pokemon.currentHp = 0
                pokemonTextBox?.text = "${pokemon.name} Lv${pokemon.level}\nHP: ${pokemon.currentHp}/${pokemon.baseStatMaxHp}"

            }
            "WILD" -> {val textBox: TextView? = activity?.findViewById(R.id.battle_text_box)
                Toast.makeText(activity?.applicationContext, "${pokemon.name} HAS FAINTED", Toast.LENGTH_SHORT).show()
                textBox?.text = "${pokemon.name} has fainted!"
                pokemon.currentHp = 0
                pokemonTextBox?.text = "${pokemon.name} Lv${pokemon.level}\nHP: ${pokemon.currentHp}/${pokemon.baseStatMaxHp}"
            }
        }
    }

    // Handling opponent moves
    private fun handleOpponentMove(opponent: Pokemon): Move {
        val availableMoves = opponent.moves.size
        return opponent.moves[Random.nextInt(availableMoves)]
    }

    // Computing damage functionality
    private fun computeDamage(move:Move, attacker: Pokemon, defender: Pokemon): Int{
        val textBox: TextView? = activity?.findViewById(R.id.battle_text_box)
//        fix text box only printing for one pokemon
        textBox?.text = "${attacker.name} used ${move.name}"
        Toast.makeText(activity?.applicationContext, "${attacker.name} used ${move.name}", Toast.LENGTH_SHORT).show()
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
        //check if attack is same as attacker type
        attacker.types.forEach{ type ->
            if(type == move.type){
                baseDamage *= 1.5
            }
        }

        //handler type multiplier
        baseDamage *= handleTypeMultiplier(move, defender)
        Log.d(TAG, "${attacker.name} did ${baseDamage.toInt()} DAMAGE")
        return baseDamage.toInt()
    }

    //move is attacker move and defender is defender type
    //type json is formatted such that you look for the attackers move type compared to the defender type
    private fun handleTypeMultiplier(move: Move, defender: Pokemon): Double{
        val textBox: TextView? = activity?.findViewById(R.id.battle_text_box)
        var multiplier = 1.0
        defender.types.forEach{ type ->
            multiplier *= typeChart.getAsJsonObject(move.type).get(type).asDouble
            Log.d(TAG, "Defender Types: $type")
        }
            if(multiplier > 1){
                textBox?.text = "${move.name} IS SUPER EFFECTIVE!"

            } else if(multiplier < 1){
                textBox?.text = "${move.name} IS SUPER WEAK!"
            }

        Thread.sleep(500)
        Log.d(TAG, "multplier = $multiplier || move type: ${move.type} || move: ${move.name}")
        return multiplier
    }

    // Handling hit functionality
    private fun handleHit(damage: Int, defender: Pokemon, textBox: TextView?){
        if(defender.currentHp - damage <= 0){
            Log.d(TAG, "${defender.name} HAS FAINTED")
            defender.currentHp = 0
        } else {
            Log.d(TAG, "current HP: ${defender.currentHp} || damage: $damage")
            defender.currentHp = defender.currentHp - damage
        }

        //change ui
        textBox?.text = "${defender.name} Lv${defender.level}\nHP: ${defender.currentHp}/${defender.baseStatMaxHp}"
    }
}