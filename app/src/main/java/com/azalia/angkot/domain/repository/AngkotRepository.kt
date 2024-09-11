package com.azalia.angkot.domain.repository

import com.azalia.angkot.data.AngkotDatabase
import com.azalia.angkot.data.AngkotSpotsDao
import com.azalia.angkot.data.toAngkotSpot
import com.azalia.angkot.data.toAngkotSpotEntity
import com.azalia.angkot.domain.model.Angkot
import com.azalia.angkot.domain.model.AngkotData
import com.azalia.angkot.domain.model.AngkotSpot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class AngkotRepository () {

    private val angkot = mutableListOf<Angkot>()
//    private val dao: AngkotSpotsDao = database.dao

    init {
        if(angkot.isEmpty()) {
            angkot.addAll(AngkotData.angkots)
        }
    }

    fun getAllAngkots(): Flow<List<Angkot>> {
        return flowOf(angkot)
    }

    fun getAngkotById(id: String): Angkot {
        return angkot.first {
            it.id == id;
        }
    }

//    suspend fun insertSpots(spot: AngkotSpot) {
//        database.dao.insertAngkotSpot(spot.toAngkotSpotEntity())
//    }
//
//    suspend fun deleteSpots(spot: AngkotSpot) {
//        database.dao.deleteAngkotSpot(spot = spot.toAngkotSpotEntity())
//    }
//
//    fun getSpots(): Flow<List<AngkotSpot>> {
//        return database.dao.getAngkotSpot().map { spots ->
//            spots.map { it.toAngkotSpot() }
//        }
//    }

//    companion object {
//        @Volatile
//        private var instance: AngkotRepository? = null
//
//        fun getInstance(): AngkotRepository =
//            instance ?: synchronized(this) {
//                AngkotRepository().apply {
//                    instance = this
//                }
//            }
//    }
}