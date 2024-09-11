package com.azalia.angkot.utils

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.IBinder
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import com.azalia.angkot.R
import com.azalia.angkot.ui.screen.destination_map.GeofenceBroadcastReceiver
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LocationService: Service() {
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var locationClient: LocationClient

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        locationClient = DefaultLocationClient(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, "location")
            .setContentTitle("Tracking Location...")
            .setContentText("Location: null")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        locationClient
            .getLocationUpdates(10000L)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                val lat = location.latitude.toString().takeLast(3)
                val lng = location.longitude.toString().takeLast(3)
                val updateNotification = notification.setContentText(
                    "Location: $lat, $lng"
                )
                notificationManager.notify(1, updateNotification.build())
            }.launchIn(serviceScope)
        startForeground(1, notification.build())
    }

    private fun stop() {
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }
}

@SuppressLint("MissingPermission")
fun getLocation(context: Context, callback: (LatLng) -> Unit) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    fusedLocationClient.lastLocation
        .addOnSuccessListener { location: Location? ->
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude
                val loc = LatLng(latitude, longitude)
                Log.d("getloc", loc.toString())
                callback(loc) // Call the callback with the location
            } else {
                // Handle the case where the location is null
            }
        }
        .addOnFailureListener { e ->
            // Handle the failure to obtain the last location
            Log.e("getloc", "Error getting location", e)
        }
}

@SuppressLint("MissingPermission")
fun addGeofence(location: LatLng, context: Context) {
    val geofencingClient = LocationServices.getGeofencingClient(context)

    // Create a Geofence with a specific radius
    val geofence = Geofence.Builder()
        .setRequestId("GeofenceId") // Unique ID for the Geofence
        .setCircularRegion(location.latitude, location.longitude, 100.0F) // 100 meters radius
        .setExpirationDuration(Geofence.NEVER_EXPIRE)
        .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
        .build()

    // Create a GeofencingRequest
    val geofencingRequest = GeofencingRequest.Builder()
        .addGeofence(geofence)
        .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
        .build()

    // Create a PendingIntent for the Geofence
    val geofencePendingIntent = getGeofencePendingIntent(context)

    // Add the Geofence to the GeofencingClient
    geofencingClient.addGeofences(geofencingRequest, geofencePendingIntent)?.run {
        addOnSuccessListener {
            // Geofence added successfully
            Log.d("Geofence", "Geofence added successfully")
        }
        addOnFailureListener {
            // Geofence addition failed
            Log.e("Geofence", "Geofence addition failed", it)
        }
    }
}

fun getGeofencePendingIntent(context: Context): PendingIntent {
    val intent = Intent(context, GeofenceBroadcastReceiver::class.java)
    intent.action = "com.azalia.angkot.ui.screen.destination_map.ACTION_GEOFENCE_TRANSITION"
    // Use PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_MUTABLE based on your needs
    val flags = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
        PendingIntent.FLAG_IMMUTABLE
    } else {
        // For versions lower than S, use FLAG_UPDATE_CURRENT
        PendingIntent.FLAG_UPDATE_CURRENT
    }

    return PendingIntent.getBroadcast(context, 0, intent, flags)
}

class GeofenceBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.azalia.angkot.ui.screen.destination_map.ACTION_GEOFENCE_TRANSITION") {
            // Handle geofence transition here
            Log.d("Geofence", "Geofence transition received")
        }
    }
}