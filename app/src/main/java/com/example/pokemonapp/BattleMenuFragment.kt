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
import androidx.fragment.app.replace
import coil.load
import com.example.pokemonapp.databinding.BattleStartFragmentBinding
import com.example.pokemonapp.objects.Pokemon
import com.example.pokemonapp.objects.Trainer

class BattleMenuFragment : Fragment() {
    private val TAG = "BATTLE_FRAGMENT"
    lateinit var trainer: Trainer
    lateinit var opponent: Pokemon
    lateinit var oppTrainer: Trainer
    lateinit var battleType: String



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = BattleStartFragmentBinding.inflate(inflater,container,false)



        Log.d(TAG, "arguments: ${arguments?.size()}")

        if(arguments != null){
            trainer = arguments?.getSerializable("trainer") as Trainer
            opponent = arguments?.getSerializable("opponent") as Pokemon

            if(arguments?.containsKey("oppTrainer") == true){
                battleType = "TRAINER"
                oppTrainer = arguments?.getSerializable("oppTrainer") as Trainer
                Log.d("OPPONENT_TRAINER", "size: ${oppTrainer.pokemonTeam.pokemons.size}")
                val oppActivePokemon: Pokemon = oppTrainer.pokemonTeam.pokemons[oppTrainer.pokemonTeam.pokemons.size-1]
                oppTrainer.pokemonTeam.pokemons.forEach { poke ->
                    Log.d("OPPONENT_TRAINER", "name: ${poke.name}, index: ${oppTrainer.pokemonTeam.pokemons.indexOf(poke)}")
                }
                Log.d("OPPONENT_TRAINER", "current health: ${oppActivePokemon.currentHp}, name: ${oppActivePokemon.name}")

                if(oppActivePokemon.currentHp <=0){
                    //fainted
                    Toast.makeText(activity?.applicationContext, "${oppActivePokemon.name} HAS FAINTED!", Toast.LENGTH_SHORT).show()
                    if(!handleSwap(oppTrainer, "OPPONENT")){
                        Toast.makeText(activity?.applicationContext, "YOU WON THE BATTLE!", Toast.LENGTH_SHORT).show()
                        var menu = Intent(activity?.applicationContext, MenuActivity::class.java)
                        menu.putExtra("trainer", trainer)
                        startActivity(menu)
                    }else {
                        opponent = oppTrainer.pokemonTeam.pokemons.last()
                    }
                }
            } else {
                battleType = "WILD"
            }

            Log.d(TAG, "trainer: ${trainer.trainerName}")

            Log.d(TAG, "active pokemon: ${trainer.pokemonTeam.pokemons[0].currentHp}")
            //starter
            val activePokemon: Pokemon = trainer.pokemonTeam.pokemons[0]


            if(activePokemon.currentHp <= 0){
                //fainted
                Toast.makeText(activity?.applicationContext, "${activePokemon.name} HAS FAINTED!", Toast.LENGTH_SHORT).show()
                if(!handleSwap(trainer, "TRAINER")){
                    var menu = Intent(activity?.applicationContext, MenuActivity::class.java)
                    menu.putExtra("trainer", trainer)
                    startActivity(menu)
                }
            }

            //setting text box
            handleBattleDialogue("WHAT WILL ${activePokemon.name} DO?")

            //handle fighting
            binding.fight.setOnClickListener{
                Log.d(TAG, "clicked fight in the fragment")

                val fragment = FightFragment()
                val dataToSend = Bundle()
                dataToSend.putSerializable("trainer", trainer)
//                only for the trainer battles
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

            //handle bag
            binding.bag.setOnClickListener{
                Log.d(TAG, "TEAM SIZE BEFORE BAG: " + trainer.pokemonTeam.pokemons.size)
                Log.d(TAG, "clicked on bag")
                val fragment = BagFragment()
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


            //handle running
            binding.run.setOnClickListener {
                when(battleType){
                 "WILD" -> {
                     Log.d(TAG, "clicked on run")
                     val menu = Intent(activity?.applicationContext, MenuActivity::class.java)
                     menu.putExtra("trainer", trainer)
                     startActivity(menu)
                     activity?.finish()
                 }
                 "TRAINER" -> {
                     Toast.makeText(activity?.applicationContext, "CANNOT RUN FROM TRAINER", Toast.LENGTH_SHORT).show()
                 }
                }

            }

            //handle team
            binding.team.setOnClickListener {
                Log.d(TAG, "clicked on pokemon button")
                val fragment = TeamFragment()
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




        return binding.root
    }

    fun handleBattleDialogue(text: String){
        val textbox: TextView? = activity?.findViewById(R.id.battle_text_box)
        textbox?.text = text
    }

    fun handleSwap(trainer: Trainer, trainerType: String): Boolean{
        val pokemons = trainer.pokemonTeam.pokemons
        if(trainerType == "OPPONENT"){
            Log.d("OPPONENT_TRAINER", "IN SWAP FOR OPPONENT")
            if(pokemons.size > 1){
                //popping last pokemon

                pokemons.removeAt(pokemons.size-1)
                Log.d("OPPONENT_TRAINER", "after swap: ${pokemons[pokemons.size-1].name}")
                Log.d("OPPONENT_TRAINER", "OPPONENT TEAM SIZE: ${pokemons.size}")

                handleUI(pokemons[pokemons.size-1], trainerType)
                return true
            }
            return false
        }else {
            if(trainer.pokemonTeam.pokemons.size > 1){
                //grab next alive pokemon


                var nextPokemon: Pokemon? = null
                pokemons.forEach { poke ->
                    if(poke.currentHp > 0){
                        nextPokemon = poke
                        return@forEach
                    }
                }
                if(nextPokemon != null){
                    val index = pokemons.indexOf(nextPokemon)
                    trainer.pokemonTeam.pokemons.removeAt(index)
                    nextPokemon?.let { trainer.pokemonTeam.pokemons.add(0, it) }

                    handleUI(nextPokemon as Pokemon, trainerType)
                    return true
                }
                return false
            }
        }
            return false

    }

    fun handleUI(pokemon: Pokemon, trainerType: String){
        when(trainerType){
            "TRAINER" -> {
                val pokeImg: ImageView? = activity?.findViewById(R.id.trainer_pokemon)
                val pokeText: TextView? = activity?.findViewById(R.id.trainer_text_box)

                pokeImg?.load(pokemon.backSprite)
                pokeText?.text = "${pokemon.name} Lv${pokemon.level}\nHP: ${pokemon.currentHp}/${pokemon.baseStatMaxHp}"
            }
            "OPPONENT" -> {
                val pokeImg: ImageView? = activity?.findViewById(R.id.enemy_pokemon)
                val pokeText: TextView? = activity?.findViewById(R.id.enemy_text_box)

                pokeImg?.load(pokemon.frontSprite)
                pokeText?.text = "${pokemon.name} Lv${pokemon.level}\nHP: ${pokemon.currentHp}/${pokemon.baseStatMaxHp}"
            }
        }


    }
}