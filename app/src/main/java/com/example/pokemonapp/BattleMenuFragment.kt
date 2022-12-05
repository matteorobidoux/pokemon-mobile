package com.example.pokemonapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.pokemonapp.databinding.BattleStartFragmentBinding
import com.example.pokemonapp.objects.Pokemon
import com.example.pokemonapp.objects.Trainer

class BattleMenuFragment : Fragment() {
    private val TAG = "BATTLE_FRAGMENT"
    lateinit var trainer: Trainer
    lateinit var opponent: Pokemon

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = BattleStartFragmentBinding.inflate(inflater,container,false)



        Log.d(TAG, "arguments: ${arguments?.size()}")

        if(arguments != null){
            trainer = arguments?.getSerializable("trainer") as Trainer
            opponent = arguments?.getSerializable("opponent") as Pokemon
            Log.d(TAG, "trainer: ${trainer.trainerName}")

            Log.d(TAG, "active pokemon: ${trainer.pokemonTeam.pokemons[0].currentHp}")
            //TODO implement full team
            val activePokemon: Pokemon = trainer.pokemonTeam.pokemons[0]

            if(activePokemon.currentHp <= 0){
                //fainted
                var menu = Intent(activity?.applicationContext, MenuActivity::class.java)
                menu.putExtra("trainer", trainer)
                startActivity(menu)
            }

            //handle fighting
            binding.fight.setOnClickListener{
                Log.d(TAG, "clicked fight in the fragment")

                val fragment = FightFragment()
                val dataToSend = Bundle()
                dataToSend.putSerializable("trainer", trainer)
                dataToSend.putSerializable("opponent", opponent)
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
                Log.d(TAG, "clicked on bag")
                val fragment = BagFragment()
                val dataToSend = Bundle()
                dataToSend.putSerializable("trainer", trainer)
                dataToSend.putSerializable("opponent", opponent)
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
                Log.d(TAG, "clicked on run")
                val menu = Intent(activity?.applicationContext, MenuActivity::class.java)
                menu.putExtra("trainer", trainer)
                startActivity(menu)
                activity?.finish()
            }

            //handle team
            binding.team.setOnClickListener {
                Log.d(TAG, "clicked on pokemon button")
                val fragment = TeamFragment()
                val dataToSend = Bundle()
                dataToSend.putSerializable("trainer", trainer)
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
}