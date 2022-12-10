package com.example.pokemonapp

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.pokemonapp.database.PokemonRoomDatabase
import com.example.pokemonapp.databinding.TitleScreenBinding
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TitleScreenActivity : AppCompatActivity() {

    private lateinit var binding: TitleScreenBinding
    private lateinit var pokemonRoomDatabase: PokemonRoomDatabase
    private lateinit  var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TitleScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pokemonRoomDatabase = PokemonRoomDatabase.getDatabase(applicationContext)
        setListeners()
        setTitleMusic()
    }

    private fun setTitleMusic(){
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.title_screen_music)
        mediaPlayer.start()
    }

    private fun setListeners(){
        binding.newGame.setOnClickListener{
            changeFormActivity()
        }

        binding.loadGame.setOnClickListener {
            loadMenuIntent()
        }
    }

    private fun changeFormActivity(){
        var formIntent = Intent(applicationContext, FormActivity::class.java)
        mediaPlayer.stop()
        startActivity(formIntent)
    }

    private fun loadMenuIntent(){
        var builder = AlertDialog.Builder(this@TitleScreenActivity)
        var dialogInflater = layoutInflater
        var dialogView = dialogInflater.inflate(R.layout.trainer_dialog, null)
        lifecycleScope.launch(Dispatchers.IO) {
            val trainer = pokemonRoomDatabase.trainerDao().getTrainer();
            withContext(Dispatchers.Main) {
                if(trainer == null){
                    dialogView.findViewById<TextView>(R.id.trainer_dialog_text).text = "No Game Has Been Saved!"
                    builder.setView(dialogView)
                    var alert = builder.create()

                    alert.show()

                    dialogView.findViewById<MaterialButton>(R.id.trainer_dialog_button).setOnClickListener{
                        alert.dismiss()
                    }

                } else {
                    var menuIntent = Intent(applicationContext, MenuActivity::class.java)
                    menuIntent.putExtra("trainer", trainer)
                    dialogView.findViewById<TextView>(R.id.trainer_dialog_text).text = "Loading Saved Game..."
                    builder.setView(dialogView)
                    var alert = builder.create()

                    alert.show()

                    dialogView.findViewById<MaterialButton>(R.id.trainer_dialog_button).setOnClickListener{
                        alert.dismiss()
                        mediaPlayer.stop()
                        startActivity(menuIntent)
                    }
                }
            }
        }
    }

}