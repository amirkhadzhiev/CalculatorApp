package com.amir.calculatorapp.di

import com.amir.calculatorapp.data.HistoryDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dataBaseModule = module(override = true) {
    single { HistoryDatabase.getInstance(androidContext()).historyDao() }
//    single { get<HistoryDatabase>().historyDao() }
}