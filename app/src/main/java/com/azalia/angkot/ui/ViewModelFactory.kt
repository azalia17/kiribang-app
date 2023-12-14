package com.azalia.angkot.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.azalia.angkot.data.AngkotDatabase
import com.azalia.angkot.data.AngkotSpotsDao
import com.azalia.angkot.di.Injection
import com.azalia.angkot.domain.repository.AngkotRepository
import com.azalia.angkot.ui.screen.destination_map.DestinationMapViewModel
import com.azalia.angkot.ui.screen.detail_angkot.DetailAngkotViewModel
import com.azalia.angkot.ui.screen.home.HomeViewModel
import com.azalia.angkot.ui.screen.list.ListViewModel
import com.azalia.angkot.ui.screen.map.MapViewModel

class ViewModelFactory(private val repository: AngkotRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            return MapViewModel() as T
        } else if (modelClass.isAssignableFrom(DestinationMapViewModel::class.java)) {
            return DestinationMapViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailAngkotViewModel::class.java)) {
            return DetailAngkotViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
//    companion object {
//        @Volatile
//        private var instance: ViewModelFactory? = null
//
//        fun getInstance(context: Context): ViewModelFactory =
//            instance ?: synchronized(this) {
//                instance ?: ViewModelFactory(Injection.provideRepository(context))
//            }.also { instance = it }
//    }
}