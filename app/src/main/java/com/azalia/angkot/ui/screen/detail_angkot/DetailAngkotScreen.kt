package com.azalia.angkot.ui.screen.detail_angkot

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azalia.angkot.R
import com.azalia.angkot.di.Injection
import com.azalia.angkot.domain.model.Angkot
import com.azalia.angkot.ui.ViewModelFactory
import com.azalia.angkot.ui.common.UiState
import com.azalia.angkot.ui.theme.Size60

@Composable
fun DetailAngkotScreen(
    angkotId: String,
    navigateBack: () -> Unit,
    viewModel: DetailAngkotViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context = LocalContext.current))
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        when (it) {
            is UiState.Loading -> {
                viewModel.getAngkotById(angkotId = angkotId)
            }
            is UiState.Success -> {
                val data = it.data
                DetailContent(angkot = data, onBackClick = navigateBack)
            }
            is UiState.Error -> {
                Text(text = it.errorMessage)
            }
            else -> {}
        }
    }
}

@Composable
fun DetailContent(
    angkot: Angkot,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
//            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .height(Size60)
                .fillMaxWidth()
//                .background(Color.White)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = modifier
                    .padding(16.dp)
                    .size(40.dp)
            ){
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    modifier = modifier,
                )
            }
            Text(
                text = angkot.id,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                ),
                modifier = modifier.align(Alignment.Center)
            )
            Divider(thickness = 1.dp, color = Color.Gray, modifier = modifier.align(Alignment.BottomCenter))
        }
        Image(
            painter = painterResource(id = angkot.image),
            contentDescription = angkot.id,
//            contentScale = ContentScale.FillHeight,
            modifier = modifier
//                .height(400.dp)
                .fillMaxHeight()
                .padding(top = 20.dp)
//                .background(color = colorResource(id = R.color.brown_1))
        )
        Spacer(modifier = modifier.height(20.dp))
        Text(text = angkot.id,style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
        ))
        Spacer(modifier = modifier.height(8.dp),)
        Text(text = "Rp.${angkot.price}",style = MaterialTheme.typography.bodyLarge.copy(
            fontSize = 14.sp,))
        Spacer(modifier = modifier.height(4.dp))
        Text(text = "Estimasi datang setiap ${angkot.interval.toString()} menit",style = MaterialTheme.typography.bodyLarge.copy(
            fontSize = 14.sp,))
    }
}