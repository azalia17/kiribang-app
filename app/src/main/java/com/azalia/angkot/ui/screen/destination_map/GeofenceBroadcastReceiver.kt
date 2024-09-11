package com.azalia.angkot.ui.screen.destination_map

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.widget.Toast
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {
//    override fun onReceive(context: Context?, intent: Intent?) {
//        val geofenceTransition = LocationServices.getGeofenceTransition(intent)
//        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
//            triggerAlarm(context)
//        }
//    }
override fun onReceive(context: Context?, intent: Intent?) {
    val geofencingEvent = intent?.let { GeofencingEvent.fromIntent(it) }
    if (geofencingEvent != null) {
        if (geofencingEvent.hasError()) {
            // Handle error
            return
        }
    }

    val geofenceTransition = geofencingEvent?.geofenceTransition

    if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
        triggerAlarm(context)
    }
}
    private fun triggerAlarm(context: Context?) {
        val alarmUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone = RingtoneManager.getRingtone(context, alarmUri)
        ringtone.play()

        // You can also show a notification or perform any other actions when the user enters the geofenced area
        Toast.makeText(context, "You are inside the geofenced area!", Toast.LENGTH_SHORT).show()
    }
}
