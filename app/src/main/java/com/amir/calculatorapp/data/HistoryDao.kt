package com.amir.calculatorapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history_table")
    fun getAll(): LiveData<List<History>>

    @Insert
    suspend fun insertAll(history: History)

    @Delete
    fun delete(history: History)

    @Query("DELETE FROM history_table")
    fun deleteAll()
}