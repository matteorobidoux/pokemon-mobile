package com.example.pokemonapp

import androidx.room.Entity


@Entity(tableName = "item_table")
class Items() {
    var pokeBalls = 0
    var potions = 0;
}
