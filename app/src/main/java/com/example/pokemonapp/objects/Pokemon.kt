package com.example.pokemonapp.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.roundToInt

@Entity(tableName = "pokemon_table")
class Pokemon(@PrimaryKey @ColumnInfo(name = "pokemonNumber") val pokemonNumber: String,
              @ColumnInfo(name="species") val species : String,
              @ColumnInfo(name="baseStateAttack") val pokemonBaseStateAttack : Int,
              @ColumnInfo(name="pokemonBaseStatDefense") val pokemonBaseStatDefense : Int,
              @ColumnInfo(name="pokemonBaseStatSpecialAttack") val pokemonBaseStatSpecialAttack : Int,
              @ColumnInfo(name="pokemonBaseStatSpecialDefense") val pokemonBaseStatSpecialDefense: Int,
              @ColumnInfo(name="pokemonBaseStateMaxHp") val pokemonBaseStateMaxHp : Int,
              @ColumnInfo(name="pokemonBaseStatSpeed") val pokemonBaseStatSpeed : Int,
              @ColumnInfo(name="baseExperienceReward") val baseExperienceReward : Int,
              @ColumnInfo(name="types") val types : ArrayList<String>,
              @ColumnInfo(name="frontSprite") val frontSprite : String,
              @ColumnInfo(name="backSprite") val backSprite : String,
              @ColumnInfo(name="pokemonMoves") val pokemonMoves : ArrayList<Move>) : java.io.Serializable{

    var name = species
    var level = floor(baseExperienceReward.toDouble().pow(1/3)).toInt()
    var experience = level*level*level
    var baseStatAttack = pokemonBaseStateAttack * (50 + level)/50
    var baseStatDefense = pokemonBaseStatDefense * (50 + level)/50
    var baseStatSpecialAttack = pokemonBaseStatSpecialAttack * (50 + level)/50
    var baseStatSpecialDefense = pokemonBaseStatSpecialDefense * (50 + level)/50
    var baseStatSpeed = pokemonBaseStatSpeed * (50 + level)/50
    var baseStatMaxHp = pokemonBaseStateMaxHp * (50 + level)/50
    var currentHp = baseStatMaxHp
    var moves: ArrayList<Move> = ArrayList()

    init {
        for(move in pokemonMoves){
            if(moves.size < 4 && move.level_learned_at <= level){
                moves.add(move)
            }
        }
    }

    fun calculateExperienceGained(oppenentPokemon: Pokemon) : Boolean{
        var newMoveAvailable = false
        val prevLevel = level
        experience = (0.3 * oppenentPokemon.baseExperienceReward * oppenentPokemon.level).roundToInt()
        level = floor(baseExperienceReward.toDouble().pow(1/3)).toInt()
        if(prevLevel != level){
            increaseStats()
             newMoveAvailable = newMoveAvailable()
        }
        return newMoveAvailable
    }

    private fun increaseStats(){
        baseStatDefense = baseStatDefense * (50 + level)/50
        baseStatSpecialAttack = baseStatSpecialAttack * (50 + level)/50
        baseStatSpecialDefense = baseStatSpecialDefense * (50 + level)/50
        baseStatSpeed = baseStatSpeed * (50 + level)/50
        baseStatMaxHp = baseStatMaxHp * (50 + level)/50
    }

    private fun newMoveAvailable(): Boolean{
        for(move in pokemonMoves){
            if(moves.size < 4 && move.level_learned_at == level){
                if(!moves.contains(move)) {
                    moves.add(move)
                    return false
                }
            } else if(moves.size == 4 && move.level_learned_at == level){
                if(!moves.contains(move)) {
                    return true
                }
            }
        }
        return false
    }

    fun attackOpponent(opposingPokemon: Pokemon, move: Move) : Double{
        if(move.damageClass == "physical") {
            val calculaton = ((2 * level) / 5 + 2).toDouble()
            var baseDamage =
                (1 / 50 * calculaton * move.power * (baseStatAttack / opposingPokemon.baseStatDefense) + 2).toDouble()
            for (type in types) {
                if (move.type == type) {
                    baseDamage *= 1.5
                    break
                }
                //TODO Create the type chart to calculate effective not effective moves
            }
            return baseDamage
        } else {
            val calculaton = ((2 * level) / 5 + 2).toDouble()
            var baseSpecialDamage =
                (1 / 50 * calculaton * move.power * (baseStatSpecialAttack / opposingPokemon.baseStatSpecialDefense) + 2).toDouble()
            for (type in types) {
                if (move.type == type) {
                    baseSpecialDamage *= 1.5
                    break
                }
                //TODO Create the type chart to calculate effective not effective moves
            }
            return baseSpecialDamage
        }
    }

    fun updateLevel(levelToSet: Int){
        level = levelToSet;
        experience = level*level*level
        baseStatAttack = pokemonBaseStateAttack * (50 + level)/50
        baseStatDefense = pokemonBaseStatDefense * (50 + level)/50
        baseStatSpecialAttack = pokemonBaseStatSpecialAttack * (50 + level)/50
        baseStatSpecialDefense = pokemonBaseStatSpecialDefense * (50 + level)/50
        baseStatSpeed = pokemonBaseStatSpeed * (50 + level)/50
        baseStatMaxHp = pokemonBaseStateMaxHp * (50 + level)/50
        currentHp = baseStatMaxHp
        moves.clear()
        for(move in pokemonMoves){
            if(moves.size < 4 && move.level_learned_at <= level){
                moves.add(move)
            }
        }
    }
}