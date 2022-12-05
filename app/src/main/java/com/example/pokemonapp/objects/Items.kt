package com.example.pokemonapp.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "item_table")
class Items(@PrimaryKey @ColumnInfo(name="name") val name: String,
            @ColumnInfo(name="quantity") var quantity: Int,
            @ColumnInfo(name="value") val value: Int,
            @ColumnInfo(name="description") val description: String,
            @ColumnInfo(name="sprite") val sprite: Int) : java.io.Serializable{
                init {

                }
}
