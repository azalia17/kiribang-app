package com.azalia.angkot.utils

object Route1 {
    fun routes(startLocation: String, endLocation: String): String {
        println("Debug: startLocation=$startLocation, endLocation=$endLocation")

        val start = startLocation.toLowerCase()
        val end = endLocation.toLowerCase()

        return when {
            start == "kampung melayu" && end == "pasar senen" -> {
                "You can take angkot M01 for route 2.\n" +
                        "Routes: Terminal Kampung Melayu 2, Rusun Jatinegara Barat, RS Hermina, ...\n" +
                        "Fee: 5000\n" +
                        "Angkot Frequency: 15 min\n" +
                        "Travel Time: 37 min"
            }
            start == "kampung melayu" && end == "rspad" -> {
                "You can take angkot M01A for route 2.\n" +
                        "Routes: Terminal Kampung Melayu 2, Rusun Jatinegara Barat, Santa Maria, ...\n" +
                        "Fee: 5000\n" +
                        "Angkot Frequency: 15 min\n" +
                        "Travel Time: 26 min"
            }
            start == "gang suzuki" && end == "sekolah cahaya sakti otista i" -> {
                "You can take angkot XYZ for route ABC.\n" +
                        "Routes: ...\n" +
                        "Fee: ...\n" +
                        "Angkot Frequency: ...\n" +
                        "Travel Time: ..."
            }
            else -> {
                "There are no angkot in your area."
            }
        }
    }
}

