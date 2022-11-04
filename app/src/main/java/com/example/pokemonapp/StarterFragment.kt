package com.example.pokemonapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.pokemonapp.databinding.StarterFragmentBinding
import com.google.android.material.button.MaterialButton

class StarterFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = StarterFragmentBinding.inflate(inflater,container,false)
        var formActivtiy = activity as FormActivity
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

            dialogView.findViewById<MaterialButton>(R.id.no_pokemon).setOnClickListener{
                alert.dismiss()
            }
        }
        return binding.root
    }
}