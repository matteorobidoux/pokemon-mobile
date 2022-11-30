package com.example.pokemonapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [Pokemon::class, Move::class, PokemonAndMoves::class], version = 1, exportSchema = false)
@TypeConverters(StringArrayListTypeConverter::class, MoveArrayListTypeConverter::class)
abstract class PokemonRoomDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonWithMoves(): PokemonWithMovesDao
    abstract fun moveDao(): MoveDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PokemonRoomDatabase? = null

        fun getDatabase(context: Context): PokemonRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonRoomDatabase::class.java,
                    "poke_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}