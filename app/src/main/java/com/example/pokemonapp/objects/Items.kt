package com.example.pokemonapp.objects

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "item_table")
class Items() : java.io.Serializable{
    @PrimaryKey(autoGenerate = true)
    var pokeBalls = 0
    var potions = 0;
}
