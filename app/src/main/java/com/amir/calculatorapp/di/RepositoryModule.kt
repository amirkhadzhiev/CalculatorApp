package com.amir.calculatorapp.di
import com.amir.calculatorapp.data.repo.MainRepo
import org.koin.dsl.module


val repositoryModule = module {
    single { MainRepo(get()) }
}