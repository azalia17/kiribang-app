package com.azalia.angkot.ui.screen.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.google.maps.android.compose.Marker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azalia.angkot.ui.theme.AngkotTheme
import com.azalia.angkot.ui.theme.Size60
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun MapScreen(viewModel: MapViewModel = androidx.lifecycle.viewmodel.compose.viewModel(), modifier: Modifier) {
//    MapContent(viewModel = viewModel)
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = true)
    }

    Column {
        Box(
            modifier = modifier
                .height(Size60)
                .fillMaxWidth()
//                .background(Color.White)
        ) {
            Text(
                text = "Peta Rute",
                style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                ),
                modifier = modifier.align(Alignment.Center)
            )
            Divider(thickness = 1.dp, color = Color.Gray, modifier = modifier.align(Alignment.BottomCenter))
        }
        GoogleMap(
            modifier = modifier.fillMaxSize(),
            properties = viewModel.state.properties,
            uiSettings = uiSettings,
//            cameraPositionState = CameraPositionState(
//                CameraPosition(
//                    target = LatLng(-6.175110, 106.865036),
//                    zoom = 15f
//                )
//            )
            cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(-6.175110, 106.865036), 11f)
            }
        ) {
            Marker(
                state = MarkerState(
                    position = LatLng(-6.175110, 106.865036)
                ),
                title = "You are here",
                icon = BitmapDescriptorFactory.defaultMarker(30f)


            )
            Marker(
                state = MarkerState(
                    position = LatLng(-6.185110, 106.865036)
                ),
                title = "Terminal Kampung Melayu"
            )
            Marker(
                state = MarkerState(
                    position = LatLng(-6.19, 106.87)
                ),
                title = "Terminal Kampung Melayu"
            )
            Marker(
                state = MarkerState(
                    position = LatLng(-6.19, 106.9)
                ),
                title = "Terminal Kampung Melayu"
            )
            Marker(
                state = MarkerState(
                    position = LatLng(-6.19, 106.67)
                ),
                title = "Terminal Kampung Melayu"
            )
            Marker(
                state = MarkerState(
                    position = LatLng(-6.19, 106.97)
                ),
                title = "Terminal Kampung Melayu"
            )
//            Marker(
//                state = MarkerState(
//                    position = LatLng()
//                )
////                position =
//            )

//            viewModel.state.angkotspots.forEach { stops ->
//                Marker(
//                    position = LatLng()
//                )
//            }
        }
    }
}

@Composable
fun MapContent(modifier: Modifier = Modifier, viewModel: MapViewModel) {
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = true)
    }
    Column {
        Box(
            modifier = modifier
                .height(Size60)
                .fillMaxWidth()
//                .background(Color.White)
        ) {
            Text(
                text = "Peta Rute",
                style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                ),
                modifier = modifier.align(Alignment.Center)
            )
            Divider(thickness = 1.dp, color = Color.Gray, modifier = modifier.align(Alignment.BottomCenter))
        }
        GoogleMap (
            modifier = modifier.fillMaxSize(),
            properties = viewModel.state.properties,
            uiSettings = uiSettings
        )
    }
}

@Preview(device = Devices.PIXEL_3)
@Composable
fun MapContentPreview() {
    AngkotTheme {
        MapScreen(modifier = Modifier)
    }
}