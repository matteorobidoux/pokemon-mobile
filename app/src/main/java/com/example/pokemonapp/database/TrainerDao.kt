package com.example.pokemonapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pokemonapp.objects.Trainer

@Dao
interface TrainerDao {

    @Query("SELECT * FROM trainer_table WHERE id == 0")
    fun getTrainer(): Trainer

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(trainer: Trainer)

    @Update
    suspend fun update(trainer: Trainer)

    @Query("DELETE FROM trainer_table")
    suspend fun deleteAll()
}