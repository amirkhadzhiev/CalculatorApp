package com.amir.calculatorapp.data.repo

import com.amir.calculatorapp.data.History
import com.amir.calculatorapp.data.HistoryDao
import com.amir.calculatorapp.data.HistoryDatabase

class MainRepo(var db: HistoryDao) {

    suspend fun insertModel(history: History) {
        db.insertAll(history)
    }

    fun readAllData() = db.getAll()
}