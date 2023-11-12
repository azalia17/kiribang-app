package com.azalia.angkot.ui.screen.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun ListScreen() {
    ListContent()
}

@Composable
fun ListContent(modifier: Modifier = Modifier) {
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
//                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                ),
                modifier = modifier.align(Alignment.Center)

            )
            Divider(thickness = 1.dp, color = Color.Gray, modifier = modifier.align(Alignment.BottomCenter))

        }
        Spacer(modifier = modifier.height(Size16))
        Text(
            text = "Mikrolet",
            style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Center,
                fontSize = 28.sp
            ),
            modifier = modifier.padding(start = Size20)
        )
        Spacer(modifier = modifier.height(Size12))
        Row(
            modifier = modifier
                .padding(start = Size8, end = Size20)
        ) {
            Image(
                painter = painterResource(id = R.drawable.angkotbiru),
                contentDescription = "This is angkot biru",
                modifier = modifier
                    .height(Size52)
                    .width(92.dp)
            )
            Spacer(modifier = modifier.width(Size8))
            Column {
                Text(
                    text = "M01",
                    style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
//                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    ),
                    modifier = modifier
                )
                Text(
                    text = "Kampung Melayu - Senen",
                    style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
//                        fontWeight = FontWeight.SemiBold,
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
        Row(
            modifier = modifier
                .padding(start = Size8, end = Size20)
        ) {
            Image(
                painter = painterResource(id = R.drawable.angkotbiru),
                contentDescription = "This is angkot biru",
                modifier = modifier
                    .height(Size52)
                    .width(92.dp)
            )
            Spacer(modifier = modifier.width(Size8))
            Column {
                Text(
                    text = "M01",
                    style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
//                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    ),
                    modifier = modifier
                )
                Text(
                    text = "Kampung Melayu - Senen",
                    style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
//                        fontWeight = FontWeight.SemiBold,
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
        Row(
            modifier = modifier
                .padding(start = Size8, end = Size20)
        ) {
            Image(
                painter = painterResource(id = R.drawable.angkotbiru),
                contentDescription = "This is angkot biru",
                modifier = modifier
                    .height(Size52)
                    .width(92.dp)
            )
            Spacer(modifier = modifier.width(Size8))
            Column {
                Text(
                    text = "M01",
                    style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
//                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    ),
                    modifier = modifier
                )
                Text(
                    text = "Kampung Melayu - Senen",
                    style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
//                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp
                    ),
                    modifier = modifier
                )

            }
        }
//        Divider(
//            thickness = 1.dp,
//            color = colorResource(id = R.color.brown_2),
//            modifier = modifier
//                .padding(horizontal = 22.dp, vertical = Size8)
//                .alpha(.5f)
//        )

    Spacer(modifier = modifier.height(Size28))
    Text(
        text = "Transjakarta",
        style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
            fontWeight = FontWeight.W700,
            textAlign = TextAlign.Center,
            fontSize = 28.sp
        ),
        modifier = modifier.padding(start = Size20)
    )
    Spacer(modifier = modifier.height(Size12))
    Row(
        modifier = modifier
            .padding(start = Size8, end = Size20)
    ) {
        Image(
            painter = painterResource(id = R.drawable.angkotbiru),
            contentDescription = "This is angkot biru",
            modifier = modifier
                .height(Size52)
                .width(92.dp)
        )
        Spacer(modifier = modifier.width(Size8))
        Column {
            Text(
                text = "M01",
                style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
//                        fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                ),
                modifier = modifier
            )
            Text(
                text = "Kampung Melayu - Senen",
                style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
//                        fontWeight = FontWeight.SemiBold,
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
    Row(
        modifier = modifier
            .padding(start = Size8, end = Size20)
    ) {
        Image(
            painter = painterResource(id = R.drawable.angkotbiru),
            contentDescription = "This is angkot biru",
            modifier = modifier
                .height(Size52)
                .width(92.dp)
        )
        Spacer(modifier = modifier.width(Size8))
        Column {
            Text(
                text = "M01",
                style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
//                        fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                ),
                modifier = modifier
            )
            Text(
                text = "Kampung Melayu - Senen",
                style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
//                        fontWeight = FontWeight.SemiBold,
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
    Row(
        modifier = modifier
            .padding(start = Size8, end = Size20)
    ) {
        Image(
            painter = painterResource(id = R.drawable.angkotbiru),
            contentDescription = "This is angkot biru",
            modifier = modifier
                .height(Size52)
                .width(92.dp)
        )
        Spacer(modifier = modifier.width(Size8))
        Column {
            Text(
                text = "M01",
                style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
//                        fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                ),
                modifier = modifier
            )
            Text(
                text = "Kampung Melayu - Senen",
                style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Gray,
//                        fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                ),
                modifier = modifier
            )

        }
    }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun ListContentPreview() {
    AngkotTheme {
        ListContent()
    }
}