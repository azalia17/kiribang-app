package com.azalia.angkot.ui.screen.destination_map

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationManagerCompat.getCurrentLocation
import com.azalia.angkot.R
import com.azalia.angkot.di.Injection
import com.azalia.angkot.ui.ViewModelFactory
import com.azalia.angkot.ui.screen.map.MapScreen
import com.azalia.angkot.ui.screen.map.MapViewModel
import com.azalia.angkot.ui.theme.AngkotTheme
import com.azalia.angkot.ui.theme.Size24
import com.azalia.angkot.ui.theme.Size60
import com.azalia.angkot.utils.LocationClient
import com.azalia.angkot.utils.LocationService
import com.azalia.angkot.utils.addGeofence
import com.azalia.angkot.utils.getLocation
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("MissingPermission")
@Composable
fun DestinationMap(
    viewModel: DestinationMapViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context = LocalContext.current))
    ),
    modifier: Modifier,
    navigateBack: () -> Unit,
) {
    var markers by remember { mutableStateOf(emptyList<LatLng>()) }
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = true)
    }
    val applicationContext: Context = LocalContext.current.applicationContext
    var location by remember { mutableStateOf(LatLng(0.0, 0.0)) }
    var isLocationFetched by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        getLocation(applicationContext) { loc ->
            // Update the location when it's obtained
            location = loc
            isLocationFetched = true
            Log.d("getloc fin", location.toString())
        }
    }

//    val customColor = BitmapDescriptorFactory.defaultMarker(200f) // Change 200f to your desired hue value


    if (isLocationFetched){
        Column {
            Box(
                modifier = modifier
                    .height(Size60)
                    .fillMaxWidth()
                    .clickable { navigateBack() }
            ) {
                IconButton(
                    onClick = {
//                    if ()
                        Intent(applicationContext, LocationService::class.java).apply {
                            action = LocationService.ACTION_START
//                              startService(this)
                            applicationContext.startService(this)
                        }
                    },
                    modifier = modifier
                        .padding(16.dp)
                        .size(40.dp)
                        .align(alignment = Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "back",
                        modifier = modifier,
                    )
                }
                Text(
                    text = "Buat Alarm",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    ),
                    modifier = modifier.align(Alignment.Center)
                )
                Divider(
                    thickness = 1.dp,
                    color = Color.Gray,
                    modifier = modifier.align(Alignment.BottomCenter)
                )
            }
            GoogleMap(
                modifier = modifier.fillMaxSize(),
                properties = viewModel.state.properties,
                uiSettings = uiSettings,
//            cameraPositionState = CameraPositionState(position = ),
                onMapLongClick = {
//                viewModel.onEvent(DestinationMapEvent.onMapLongClick(it))
                    markers = markers + listOf(it)
                    addGeofence(it, applicationContext)
                },
                cameraPositionState = rememberCameraPositionState {
//                getLocation(applicationContext) {
//                    location = it
//                }
//                position = CameraPosition.fromLatLngZoom(LatLng(-6.175110, 106.865036), 15f)
                    position = CameraPosition.fromLatLngZoom(location, 15f)
                    Log.d("location", location.toString())
                }

            ) {
                Marker(
                    state = MarkerState(
                        position = LatLng(-6.175110, 106.865036)
//                    position = location
                    ),
                    title = "You are here",
//                icon = BitmapDescriptorFactory.defaultMarker(200f)
                )

                Marker(
                    state = MarkerState(
//                    position = LatLng(-6.175110, 106.865036)
                        position = location
                    ),
                    title = "You are here",
//                icon = BitmapDescriptorFactory.defaultMarker(200f)
                )
                markers.forEachIndexed { index, markerPosition ->
                    Circle(
                        center = markerPosition,
                        fillColor = colorResource(id = R.color.brown_1).copy(alpha = .5F),
                        radius = 100.0,
                        strokeColor = colorResource(id = R.color.brown_1),
                        strokeWidth = 5.0f

                    )

                }
            }
        }
    } else {
        Text("Loading location...")

    }
}


//    val uiSettings = remember {
//        MapUiSettings(zoomControlsEnabled = true)
//    }
//
//    var selectedLocation by remember { mutableStateOf<LatLng?>(null) }
//    val mapView = rememberMapViewWithLifecycle()
//
//    // Use a DisposableEffect to request the location when the composable is first composed
//    DisposableEffect(Unit) {
//        val locationResult = requestLocation()
//
//        // Update the selectedLocation with the user's current location
//        locationResult?.let {
//            selectedLocation = LatLng(it.latitude, it.longitude)
//        }
//
//        onDispose {
//            // Clean up if needed
//        }
//    }
//    Column {
//        Box(
//            modifier = modifier
//                .height(Size60)
//                .fillMaxWidth()
////                .background(Color.White)
//                .clickable { navigateBack() }
//        ) {
//            Text(
//                text = "Buat Alarm",
//                style = MaterialTheme.typography.bodySmall.copy(
////                    color = Color.Gray,
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Center,
//                    fontSize = 16.sp
//                ),
//                modifier = modifier.align(Alignment.Center)
//            )
//            Divider(thickness = 1.dp, color = Color.Gray, modifier = modifier.align(Alignment.BottomCenter))
//        }
//        GoogleMap (
//            modifier = modifier.fillMaxSize(),
//            properties = viewModel.state.properties,
//            uiSettings = uiSettings,
//            onMapClick = { latLng ->
//                // Assuming your ViewModel has a function to set the selected location
//                viewModel.setSelectedLocation(latLng)
//            }
//        )
//
//        // Button to create Geofence and go back
//        Button(
//            onClick = {
//                viewModel.selectedLocation.value?.let { location ->
////
//
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Text(text = "Set Geofence Alarm")
//        }
//    }
//}

//@Composable
//fun requestLocation(): Location? {
//    var location by remember { mutableStateOf<Location?>(null) }
//
//    LaunchedEffect(true) {
//        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(LocalContext.current)
//
//        try {
//            val locationResult = if (ActivityCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return@LaunchedEffect
//            } else {
//            }
//            fusedLocationClient.lastLocation.await()
//            location = locationResult
//        } catch (e: Exception) {
//            // Handle location request failure
//            e.printStackTrace()
//        }
//    }
//
//    return location
//}
//
//fun getGeofencePendingIntent(context: Context): PendingIntent {
//    val intent = Intent(context, GeofenceBroadcastReceiver::class.java)
//    intent.action = "com.azalia.angkot.ui.screen.destination_map.ACTION_GEOFENCE_TRANSITION"
//    return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//}
//
//@Composable
//fun createAlarm(location: LatLng?, geofencePendingIntent: PendingIntent): PendingIntent {
//    // Create an alarm intent that will be triggered when the Geofence is entered
//    val alarmIntent = Intent("com.azalia.angkot.ui.screen.destination_map.ACTION_GEOFENCE_ALARM")
//    alarmIntent.putExtra("location", location)
//    alarmIntent.putExtra("geofencePendingIntent", geofencePendingIntent)
//
//    return PendingIntent.getBroadcast(
//        LocalContext.current,
//        0,
//        alarmIntent,
//        PendingIntent.FLAG_UPDATE_CURRENT
//    )
//}
//
//@Composable
//fun createGeofence(location: LatLng?): PendingIntent {
//    lateinit var geofencingClient: GeofencingClient
//
//    geofencingClient = LocationServices.getGeofencingClient(this)
//
//    // Create a Geofence with a specific radius
//    val geofence = Geofence.Builder()
//        .setRequestId("GeofenceId") // Unique ID for the Geofence
//        .setCircularRegion(location!!.latitude, location.longitude, 100.0F) // 100 meters radius
//        .setExpirationDuration(Geofence.NEVER_EXPIRE)
//        .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
//        .build()
//
//    // Create a GeofencingRequest
//    val geofencingRequest = GeofencingRequest.Builder()
//        .addGeofence(geofence)
//        .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
//        .build()
//
//    // Create a PendingIntent for the Geofence
//    val geofencePendingIntent = getGeofencePendingIntent(LocalContext.current)
//
//    // Add the Geofence to the GeofencingClient
//    if (ActivityCompat.checkSelfPermission(
//            this,
//            Manifest.permission.ACCESS_FINE_LOCATION
//        ) != PackageManager.PERMISSION_GRANTED
//    ) {
//        // TODO: Consider calling
//        //    ActivityCompat#requestPermissions
//        // here to request the missing permissions, and then overriding
//        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//        //                                          int[] grantResults)
//        // to handle the case where the user grants the permission. See the documentation
//        // for ActivityCompat#requestPermissions for more details.
//        return
//    }
//    geofencingClient.addGeofences(geofencingRequest, geofencePendingIntent)?.run {
//        addOnSuccessListener {
//            // Geofence added successfully
//        }
//        addOnFailureListener {
//            // Geofence addition failed
//            it.printStackTrace()
//        }
//    }
//
//    return geofencePendingIntent
//}


//@Preview(device = Devices.PIXEL_3)
//@Composable
//fun MapContentPreview() {
//    AngkotTheme {
//        MapScreen(modifier = Modifier)
//    }
//}