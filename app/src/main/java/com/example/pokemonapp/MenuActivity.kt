package com.example.pokemonapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pokemonapp.database.PokemonRoomDatabase
import com.example.pokemonapp.databinding.MenuActivityBinding
import com.example.pokemonapp.objects.Trainer
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuActivity: AppCompatActivity() {

    private lateinit var binding: MenuActivityBinding
    private lateinit var trainer: Trainer
    private val TAG = "MENU"
    private lateinit var pokemonRoomDatabase: PokemonRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MenuActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pokemonRoomDatabase = PokemonRoomDatabase.getDatabase(applicationContext)

        val extras = intent.extras
        if (extras != null) {
            trainer = extras.getSerializable("trainer") as Trainer
            Log.d(TAG, "trainer team size: ${trainer.pokemonTeam.pokemons.size}")
        }

        trainer.pokemonTeam.pokemons.forEach { poke ->
            Log.d(TAG, "${poke.name} : LV ${poke.experience}")
        }
        binding.pokecenterBtn.setOnClickListener {
            changeActivityPokecenter()
        }

        binding.pokemartBtn.setOnClickListener {
            changeActivityPokemart()

        }

        binding.wildPokeBattleBtn.setOnClickListener{
            changeActivityWildBattle()
        }

        binding.trainerBattleBtn.setOnClickListener{
            changeActivityTrainerBattle()
        }

        binding.saveGameBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.IO) {
                    val checkTrainer = pokemonRoomDatabase.trainerDao().getTrainer()
                    if (checkTrainer == null) {
                        pokemonRoomDatabase.trainerDao().insert(trainer)
                    } else {
                        pokemonRoomDatabase.trainerDao().update(trainer)
                    }
                }
                withContext(Dispatchers.Main) {
                    var builder = AlertDialog.Builder(this@MenuActivity)
                    var dialogInflater = layoutInflater
                    var dialogView = dialogInflater.inflate(R.layout.trainer_dialog, null)
                    dialogView.findViewById<TextView>(R.id.trainer_dialog_text).text =
                        "Your Game Has Been Saved!"
                    builder.setView(dialogView)
                    var alert = builder.create()

                    alert.show()

                    dialogView.findViewById<MaterialButton>(R.id.trainer_dialog_button)
                        .setOnClickListener {
                            alert.dismiss()
                        }
                }
            }
        }

        binding.changeTeamBtn.setOnClickListener {
            changeActivityChangeTeam()
        }

        binding.wildPokeBattleBtn.setOnClickListener{
            changeActivityWildBattle()
        }

    }


    fun changeActivityPokecenter() {
        var pokeCenter = Intent(applicationContext, PokecenterActivity::class.java)
        pokeCenter.putExtra("trainer", trainer)
        startActivity(pokeCenter)
    }

    fun changeActivityPokemart(){
        var pokeMart = Intent(applicationContext, Pokemart::class.java)
        pokeMart.putExtra("trainer", trainer)
        startActivity(pokeMart)
    }

    private fun changeActivityWildBattle(){
        var battle = Intent(applicationContext, BattleActivity::class.java)
        battle.putExtra("trainer", trainer)
        startActivity(battle)
    }

    private fun changeActivityTrainerBattle() {
        var battle = Intent(applicationContext, TrainerBattleActivity::class.java)
        battle.putExtra("trainer", trainer)
        startActivity(battle)
    }

    private fun changeActivityChangeTeam() {
        var formIntent = Intent(applicationContext, TeamActivity::class.java)
        formIntent.putExtra("trainer", trainer)
        startActivity(formIntent)
    }
}