package com.example.pokemonapp.objects

import androidx.room.Entity


@Entity(tableName = "item_table")
class Items() : java.io.Serializable{
    var pokeBalls = 0
    var potions = 0;
}
