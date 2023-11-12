package com.azalia.angkot.ui.screen.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings

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
        GoogleMap (
            modifier = modifier.fillMaxSize(),
            properties = viewModel.state.properties,
            uiSettings = uiSettings
        )
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