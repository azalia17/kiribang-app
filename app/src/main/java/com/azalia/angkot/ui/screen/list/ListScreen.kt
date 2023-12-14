package com.azalia.angkot.ui.screen.list

import android.app.Application
//import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azalia.angkot.R
import com.azalia.angkot.di.Injection
import com.azalia.angkot.domain.model.Angkot
import com.azalia.angkot.domain.model.AngkotData
import com.azalia.angkot.ui.ViewModelFactory
import com.azalia.angkot.ui.common.UiState
import com.azalia.angkot.ui.theme.AngkotTheme
import com.azalia.angkot.ui.theme.Size12
import com.azalia.angkot.ui.theme.Size16
import com.azalia.angkot.ui.theme.Size20
import com.azalia.angkot.ui.theme.Size24
import com.azalia.angkot.ui.theme.Size28
import com.azalia.angkot.ui.theme.Size52
import com.azalia.angkot.ui.theme.Size60
import com.azalia.angkot.ui.theme.Size8
import com.azalia.angkot.ui.theme.Size88
import android.content.Context
import androidx.compose.ui.platform.LocalContext


@Composable
fun ListScreen(
    modifier: Modifier,
    viewModel: ListViewModel = viewModel(
//        factory = ViewModelFactory(Injection.provideRepository(), Injection.provideAngkotDatabase(app = Application()))
//        factory = ViewModelFactory.getInstance(application)
//        factory = ViewModelFactory(Injection.provideRepository(application))
        factory = ViewModelFactory(Injection.provideRepository(context = LocalContext.current))
    ),
    navigateToDetail: (String) -> Unit,
) {
   viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
       when (uiState) {
           is UiState.Loading -> {
               viewModel.getAllAngkots()
           }
           is UiState.Success -> {
               ListContent(angkot = uiState.data, navigateToDetail = navigateToDetail, modifier = modifier)
           }
           is UiState.Error -> {}
       }
   }
//    ListContent()
}

@Composable
fun ListContent(
    angkot: List<Angkot>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit
    ) {
    Column {
        Box(
            modifier = modifier
                .height(Size60)
                .fillMaxWidth()
//                .background(Color.White)
        ) {
            Text(
                text = "List Angkot",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                ),
                modifier = modifier.align(Alignment.Center)
            )
            Divider(thickness = 1.dp, color = Color.Gray, modifier = modifier.align(Alignment.BottomCenter))
        }
        Text(
            text = "Angkot",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Center,
                fontSize = 28.sp
            ),
            modifier = modifier.padding(start = Size20, bottom = Size20, top = Size24)
        )
        LazyColumn {
            items(angkot) { angkot ->
                Row(
                    modifier = modifier
                        .padding(start = Size8, end = Size20)
                        .clickable {
                            navigateToDetail(angkot.id)
                        }
                ) {
                    Image(
                        painter = painterResource(id = angkot.image),
                        contentDescription = "This is angkot biru",
                        modifier = modifier
                            .height(Size52)
                            .width(92.dp)
                    )
                    Spacer(modifier = modifier.width(Size8))
                    Column {
                        Text(
                            text = angkot.id,
                            style = MaterialTheme.typography.bodySmall.copy(
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp
                            ),
                            modifier = modifier
                        )
                        Text(
                            text = "Kampung Melayu - Senen",
                            style = MaterialTheme.typography.bodySmall.copy(
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp
                            ),
                            modifier = modifier
                        )

                    }
                }
                Divider(
                    thickness = 1.dp,
                    color = colorResource(id = R.color.brown_2),
                    modifier = modifier
                        .padding(horizontal = 22.dp, vertical = Size8)
                        .alpha(.5f)
                )
            }
            }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun ListContentPreview() {
    AngkotTheme {
//        ListContent()
    }
}