package com.azalia.angkot.data

class AngkotRepository {

    companion object {
        @Volatile
        private var instance: AngkotRepository? = null

        fun getInstance(): AngkotRepository =
            instance ?: synchronized(this) {
                AngkotRepository().apply {
                    instance = this
                }
            }
    }
}