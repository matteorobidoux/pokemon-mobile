package com.example.pokemonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokemonapp.databinding.StarterFragmentBinding

class StarterFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = StarterFragmentBinding.inflate(inflater,container,false)
        var formActivtiy = activity as FormActivity
        formActivtiy.setText("To start your adventure, you must first chose your starter pokemon!")
        return binding.root
    }
}