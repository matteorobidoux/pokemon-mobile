package com.example.pokemonapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokemonapp.objects.*

@Database(entities = [Pokemon::class, Move::class, PokemonMoveRef::class, Trainer::class, Items::class, PokemonCollection::class, PokemonTeam::class], version = 15, exportSchema = false)
@TypeConverters(StringArrayListTypeConverter::class, MoveArrayListTypeConverter::class, PokemonArrayListTypeConverter::class, PokemonTeamTypeConverter::class, PokemonCollectionTypeConverter::class, ItemsArrayListTypeConverter::class)
abstract class PokemonRoomDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonWithMoves(): PokemonWithMovesDao
    abstract fun moveDao(): MoveDao
    abstract fun trainerDao(): TrainerDao

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
                    "pokemon_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}