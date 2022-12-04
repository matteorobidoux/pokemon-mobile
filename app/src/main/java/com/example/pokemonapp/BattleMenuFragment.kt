package com.example.pokemonapp

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
            }

            //handle running
            binding.run.setOnClickListener {
                Log.d(TAG, "clicked on run")
            }

            //handle team
            binding.team.setOnClickListener {
                Log.d(TAG, "clicked on pokemon button")
            }
        }




        return binding.root
    }
}