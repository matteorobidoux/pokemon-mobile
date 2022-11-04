package com.example.pokemonapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.pokemonapp.databinding.NameFragmentBinding
import com.google.android.material.button.MaterialButton

class NameFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = NameFragmentBinding.inflate(inflater,container,false)
        binding.confirmName.setOnClickListener{
            var builder = AlertDialog.Builder(context)
            var dialogInflater = layoutInflater
            var dialogView = dialogInflater.inflate(R.layout.custom_dialog, null)
            dialogView.findViewById<TextView>(R.id.confirm_text).text = "Your name is ${binding.trainerName.text}?"

            builder.setView(dialogView)
            var alert = builder.create()

            alert.show()
            dialogView.findViewById<MaterialButton>(R.id.yes_name).setOnClickListener{
                view?.findNavController()?.navigate(R.id.action_nameFragment_to_starterFragment)
                alert.dismiss()
            }

            dialogView.findViewById<MaterialButton>(R.id.no_name).setOnClickListener{
               alert.dismiss()
            }
        }

        return binding.root
    }
}