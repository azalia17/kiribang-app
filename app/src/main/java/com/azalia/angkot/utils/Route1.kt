package com.azalia.angkot.utils

object Route1 {

    fun routes(startLocation: String, endLocation: String): String {
        // Debug
        println("Debug: startLocation=$startLocation, endLocation=$endLocation")

        // Lowercase both startLocation and endLocation for case-insensitive comparison
        val start = startLocation.toLowerCase()
        val end = endLocation.toLowerCase()

        // Check the conditions and provide the appropriate response
        return when {
            start == "kampung melayu" && end == "pasar senen" -> {
                // Case 1
                "You can take angkot M01 for route 2.\n" +
                        "Routes: Terminal Kampung Melayu 2, Rusun Jatinegara Barat, RS Hermina, ...\n" +
                        "Fee: 5000\n" +
                        "Angkot Frequency: 15 min\n" +
                        "Travel Time: 37 min"
            }
            start == "kampung melayu" && end == "rspad" -> {
                // Case 2
                "You can take angkot M01A for route 2.\n" +
                        "Routes: Terminal Kampung Melayu 2, Rusun Jatinegara Barat, Santa Maria, ...\n" +
                        "Fee: 5000\n" +
                        "Angkot Frequency: 15 min\n" +
                        "Travel Time: 26 min"
            }
            start == "gang suzuki" && end == "sekolah cahaya sakti otista i" -> {
                // Case 3
                "You can take angkot XYZ for route ABC.\n" +
                        "Routes: ...\n" +
                        "Fee: ...\n" +
                        "Angkot Frequency: ...\n" +
                        "Travel Time: ..."
            }
            else -> {
                // Default Case
                "There are no angkot in your area."
            }
        }
    }
}

