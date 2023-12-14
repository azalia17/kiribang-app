package com.azalia.angkot.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.azalia.angkot.data.AngkotDatabase
import com.azalia.angkot.data.AngkotSpotsDao
import com.azalia.angkot.domain.repository.AngkotRepository

object Injection {
    fun provideRepository(context: Context): AngkotRepository {
        return AngkotRepository()
    }

    fun provideAngkotDatabase(context: Context): AngkotDatabase {
        return Room.databaseBuilder(
//            app,
            context,
            AngkotDatabase::class.java,
            "angkot.db"
        ).build()
    }
}