package com.example.pokemonapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.BattleStartFragmentBinding
import com.example.pokemonapp.databinding.FragmentFightBinding

class FightFragment : Fragment() {
    private val TAG = "FIGHT_FRAGMENT"
    private lateinit var adapter: MoveRecyclerViewAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentFightBinding.inflate(inflater,container,false)

        //recycler view Setup
        val recyclerView: RecyclerView = binding.moves
        val layoutManager: GridLayoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
        //set up fragment on click
        adapter.onItemClick = { move ->
            Log.d(TAG, "clicked $move")
        }

        return binding.root
    }
}