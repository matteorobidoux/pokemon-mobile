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

class BattleMenuFragment : Fragment() {
    private val TAG = "BATTLE_FRAGMENT"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = BattleStartFragmentBinding.inflate(inflater,container,false)
        binding.fight.setOnClickListener{
            Log.d(TAG, "clicked fight in the fragment")

            val fragmentManager = parentFragmentManager
            fragmentManager.commit {
                replace<FightFragment>(R.id.battle_menu)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }

        binding.bag.setOnClickListener{
            Log.d(TAG, "clicked on bag")
        }

        binding.run.setOnClickListener {
            Log.d(TAG, "clicked on run")
        }

        binding.team.setOnClickListener {
            Log.d(TAG, "clicked on pokemon button")
        }

        return binding.root
    }
}