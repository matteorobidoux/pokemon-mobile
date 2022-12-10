package com.example.pokemonapp.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "item_table")
class Items(@PrimaryKey @ColumnInfo(name="name") val name: String,
            @ColumnInfo(name="quantity") var quantity: Int,
            @ColumnInfo(name="value") val value: Int,
            @ColumnInfo(name="description") val description: String,
            @ColumnInfo(name="sprite") val sprite: Int) : java.io.Serializable {
    init {

    }

<<<<<<< HEAD
    class Items() : java.io.Serializable {
        @PrimaryKey(autoGenerate = true)
        var pokeBalls = 0
        var potions = 0;
    }
}
=======
}
>>>>>>> 5bca2d305b8bd869195873932bdb3d0db1591a18
