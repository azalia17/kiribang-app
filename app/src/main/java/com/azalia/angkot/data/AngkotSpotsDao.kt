package com.azalia.angkot.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AngkotSpotsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAngkotSpot(spot: AngkotSpotsEntity)

    @Delete
    suspend fun deleteAngkotSpot(spot: AngkotSpotsEntity)

    @Query("SELECT * FROM angkot_spot")
    fun getAngkotSpot(): Flow<List<AngkotSpotsEntity>>

}