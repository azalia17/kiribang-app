package com.azalia.angkot.data

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [AngkotSpotsEntity::class],
    version = 1
)
abstract class AngkotDatabase: RoomDatabase() {
    abstract val dao: AngkotSpotsDao
}