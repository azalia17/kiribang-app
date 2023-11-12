package com.azalia.angkot.di

import com.azalia.angkot.data.AngkotRepository

object Injection {
    fun provideRepository(): AngkotRepository {
        return AngkotRepository.getInstance()
    }
}