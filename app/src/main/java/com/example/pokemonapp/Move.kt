package com.example.pokemonapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "move_table")
class Move(@PrimaryKey @ColumnInfo(name="name") val name: String,
           @ColumnInfo(name="level_learned_at") val level_learned_at: String,
           @ColumnInfo(name="accuracy") val accuracy: Int,
           @ColumnInfo(name="ailment") val ailment: String,
           @ColumnInfo(name="ailmentChance") val ailmentChance: Int,
           @ColumnInfo(name="category") val category: String,
           @ColumnInfo(name="damageClass") val damageClass: String,
           @ColumnInfo(name="heal") val heal: Int,
           @ColumnInfo(name="maxPP") val maxPP: Int,
           @ColumnInfo(name="power") val power: Int,
           @ColumnInfo(name="target") val target: String,
           @ColumnInfo(name="type") val type: String) {

    init {

    }
}