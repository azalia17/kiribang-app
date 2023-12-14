package com.azalia.angkot.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azalia.angkot.R
import com.azalia.angkot.di.Injection
import com.azalia.angkot.ui.ViewModelFactory
import com.azalia.angkot.ui.theme.AngkotTheme
import com.azalia.angkot.ui.theme.Size16
import com.azalia.angkot.ui.theme.Size20
import com.azalia.angkot.ui.theme.Size24
import com.azalia.angkot.ui.theme.Size36
import com.azalia.angkot.ui.theme.Size44
import com.azalia.angkot.ui.theme.Size56
import com.azalia.angkot.ui.theme.Size8
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.res.stringResource
import com.azalia.angkot.ui.theme.Size52
import com.azalia.angkot.ui.theme.Size60
import com.azalia.angkot.ui.theme.Size88


@Composable
fun HomeScreen(
    modifier: Modifier,
//    viewModel: HomeViewModel = viewModel(
////        factory = ViewModelFactory(Injection.provideRepository())
//    ),
    navigateToDestinationMap: () -> Unit,
    ) {
    HomeContent(navigateToDestinationMap = navigateToDestinationMap)
//    val query by viewModel.query
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    navigateToDestinationMap: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = modifier
                    .height(Size60)
                    .fillMaxWidth()
//                    .clickable { navigateBack() }
            ) {
//            Row {
//                Icon(
//                    imageVector = Icons.Rounded.ArrowBack,
//                    contentDescription = "Back button",
//                    modifier = modifier.clickable { navigateBack() })
//                Text(
//                    text = "Buat Alarm",
//                    style = MaterialTheme.typography.bodySmall.copy(
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center,
//                        fontSize = 16.sp
//                    ),
////                    modifier = modifier.align(Alignment.Center)
//                )
//            }
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
                )}
            Box (modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()) {
                Column(modifier = modifier.fillMaxHeight()) {

                }
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    trailingIcon = { // Add the trailing icon here
                        val icon: @Composable () -> Unit = {
                            IconButton(
                                onClick = {},
                                modifier = modifier
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Send,
                                    tint = colorResource(id = R.color.brown_1),
                                    contentDescription = "This is button send"
                                )
                            }
                        }
                        icon()
                    },
                    placeholder = {
                        Text(
                            text = "Ketik pertanyaanmu",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.Gray
                            )
                        )
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .height(Size52)
                        .align(alignment = Alignment.BottomCenter)
//                            .padding(Size20)
                )
            }



//            Text(
//                text = "Selamat Siang User!",
//                modifier = modifier
//                    .padding(top = Size20, bottom = Size20, start = Size20, end = Size20),
//                style = MaterialTheme.typography.bodyLarge.copy(
//                    fontSize = 20.sp,
//                    letterSpacing = 4.sp
//                )
//            )
//            Text(
//                text = "Mau diingatkan dimana?",
//                style = MaterialTheme.typography.bodyLarge.copy(
//                    fontWeight = FontWeight.Medium,
//                    fontSize = 24.sp,
//                    letterSpacing = 2.sp
//                ),
//                modifier = modifier
//                    .padding(start = Size20, end = Size20)
//            )
//            Spacer(modifier = modifier.height(Size8))
//            Row(
//                modifier = modifier
//                    .padding(start = Size20, end = Size20)
//                    .fillMaxWidth()
//                    .height(Size56)
//                    .clickable {
//                        navigateToDestinationMap()
//                    }
//            ) {
//                Box(
//                    modifier = modifier
//                        .clip(RoundedCornerShape(Size16))
//                        .border(
//                            width = 2.dp,
//                            color = colorResource(id = R.color.brown_1),
//                            shape = RoundedCornerShape(Size16)
//                        )
//                        .background(colorResource(id = R.color.light_brown))
//                        //                    .fillMaxWidth()
//                        .weight(weight = .7f)
//                        .fillMaxHeight()
//
//                ) {
//                    Text(
//                        text = "Pilih Lokasi",
//                        style = MaterialTheme.typography.labelMedium.copy(
//                            textAlign = TextAlign.Center,
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Light
//                        ),
//                        modifier = modifier
//                            .padding(Size8)
//                            //                        .fillMaxWidth()
//                            //                        .fillMaxHeight()
//                            .align(Alignment.Center)
//                    )
//                }
//                Spacer(modifier = modifier.width(Size8))
//                Box(
//                    modifier = modifier
//                        .clip(RoundedCornerShape(Size56))
//                        .background(colorResource(id = R.color.brown_1))
//                        .height(Size56)
//                        .width(Size56)
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.alarm),
//                        contentDescription = "This is button add alarm",
//                        tint = Color.White,
//                        modifier = modifier
//                            .height(Size24)
//                            .width(Size24)
//                            .align(Alignment.Center)
//                    )
//                }
//            }
//            Spacer(modifier = modifier.height(Size36))
//            Text(
//                text = "Tanya",
//                style = MaterialTheme.typography.bodyLarge.copy(
//                    fontWeight = FontWeight.Medium,
//                    fontSize = 24.sp,
//                    letterSpacing = 2.sp
//                ),
//                modifier = modifier
//                    .padding(start = Size20, end = Size20)
//            )
//        }
//        Box(
//            modifier = modifier
//                .clip(RoundedCornerShape(topStart = Size16, topEnd = Size16))
//                .border(
//                    width = 2.dp,
//                    color = colorResource(id = R.color.brown_1),
//                    shape = RoundedCornerShape(topStart = Size16, topEnd = Size16)
//                )
//                .background(colorResource(id = R.color.light_brown))
//                .padding(top = Size8)
////                .align(Alignment.BottomCenter)
//                .fillMaxSize()
////                .height(450.dp)
////                .weight(.8f)
//        ) {
////            Spacer(modifier = )
//            Column(
//                modifier = modifier
//                    .align(Alignment.BottomCenter)
//                    .padding(Size20)
//            ) {
////                Spacer(modifier = modifier.weight(.6f))
//                OutlinedTextField(
//                    value = "",
//                    onValueChange = {},
//                    trailingIcon = { // Add the trailing icon here
//                        val icon: @Composable () -> Unit = {
//                            IconButton(
//                                onClick = {},
//                                modifier = modifier
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.Send,
//                                    tint = colorResource(id = R.color.brown_1),
//                                    contentDescription = "This is button send"
//                                )
//                            }
//                        }
//                        icon()
//                    },
//                    placeholder = {
//                        Text(
//                            text = "Ketik pertanyaanmu",
//                            style = MaterialTheme.typography.bodySmall.copy(
//                                color = Color.Gray
//                            )
//                        )
//                    },
//                    modifier = modifier
//                        .fillMaxWidth()
//                        .height(Size52)
////                            .padding(Size20)
//                )
            }
        }
    }


//@Preview(showBackground = true, device = Devices.PIXEL_4)
//@Composable
//fun HomeContentPreview() {
//    AngkotTheme {
////        HomeContent()
//    }
//}