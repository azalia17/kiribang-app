package com.azalia.angkot.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azalia.angkot.R
import com.azalia.angkot.ui.theme.AngkotTheme
import com.azalia.angkot.ui.theme.Size56
import com.azalia.angkot.ui.theme.Size8
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import com.azalia.angkot.domain.model.Message
import com.azalia.angkot.ui.theme.Size52
import com.azalia.angkot.ui.theme.Size60
import com.azalia.angkot.ui.theme.Size88
import com.azalia.angkot.utils.BotResponse
import com.azalia.angkot.utils.Constants.RECEIVE_ID
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.util.Log
import com.azalia.angkot.utils.Route1
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun HomeScreen(

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
    state: LazyListState = rememberLazyListState(),
    ) {
    var msg by rememberSaveable { mutableStateOf("") }
    val random = (0..3).random()
    var chats by remember { mutableStateOf(emptyList<Message>()) }
    var bubbles by remember { mutableStateOf(emptyList<String>()) }
    val scope = rememberCoroutineScope()
//    val scope = rememberCoroutineScope()

//    val messages = bubbles.collectAsLazyPagingItems()

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
            Box(
                modifier = modifier
//                    .fillMaxHeight()
                    .height(620.dp)
                    .fillMaxWidth()
            ) {
                LazyColumn() {
                    scope.launch {
                        state.scrollToItem(0)
                    }
                    items(
//                        count = bubbles.size,
                        count = chats.size,
//                        itemContent =
                    ) { index ->
//                        bubbles[index]?.let {
//                            Bubble(isMine = true, text = it)
//                        }
                        chats[index]?.let {
                            Bubble(isMine = it.isMine, text = it.message)
                        }
                    }
                }
                OutlinedTextField(
                    value = msg,
                    onValueChange = { msg = it },
                    trailingIcon = { // Add the trailing icon here
                        val icon: @Composable () -> Unit = {
                            IconButton(
                                onClick = {
                                    val mess = Message(msg, isMine = true)
                                    chats = chats + listOf(mess)
//                                    bubbles = bubbles + listOf(msg)
                                    Log.d("YourTag", "Message: $msg")
                                    Log.d("YourTag", "mess: $mess")

                                    print(msg)
                                    print(mess)
                                    val response = BotResponse.basicResponses(msg)
                                    Log.d("YourTag", "Response: $response")
                                    print(response)

                                    //Adds it to our local list
//                                            bubbles = bubbles + listOf(response)
                                    val resp = Message(response, false)
                                    chats = chats + listOf(resp)
//                                    scope.launch {
//                                        withContext(Dispatchers.Main) {
//                                            //Gets the response
//                                            val response = basicResponses(msg)
//                                            Log.d("YourTag", "Response: $response")
//                                            print(response)
//
//                                            //Adds it to our local list
////                                            bubbles = bubbles + listOf(response)
//                                            val resp = Message(response, false)
//                                            chats = chats + listOf(resp)
//
//                                            //Inserts our message into the adapter
//
//                                            //Scrolls us to the position of the latest message
//                                        }
//                                    }
                                    msg = ""

                                },
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
        }
    }
}

@Composable
fun Bubble(
    isMine: Boolean,
    text: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = when { // 2
            isMine -> Alignment.End
            else -> Alignment.Start
        },
    ) {
        Card(
            modifier = Modifier.widthIn(max = 340.dp),
            shape = cardShapeFor(isMine), // 3

//            colors = when {
//                isMine -> {
//                    CardColors(containerColor = colorResource(id = R.color.brown_1))
//                }
//                else -> MaterialTheme.colors.secondary
//            },
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = text,
                color = when {
                    isMine -> colorResource(id = R.color.brown_1)
                    else -> colorResource(id = R.color.brown_1)
                },
            )
        }
//        Text( // 4
//            text = messageItem.message.user.name,
//            fontSize = 12.sp,
//        )
    }
}

@Composable
fun cardShapeFor(isMine: Boolean): Shape {
    val roundedCorners = RoundedCornerShape(16.dp)
    return when {
        isMine -> roundedCorners.copy(bottomEnd = CornerSize(0))
        else -> roundedCorners.copy(bottomStart = CornerSize(0))
    }
}

@Preview
@Composable
fun MyBubblePreview() {
    AngkotTheme {
        Bubble(isMine = false, text = "aa")
    }
}

fun basicResponses(_message: String): String {
    Log.d("YourTag", "Message botresponse.kt: $_message")



    val random = (0..2).random()
    val message =_message.toLowerCase(Locale.ROOT)

    return when {

        //Hello
        message.contains("hello") -> {
            when (random) {
                0 -> "Hello there!"
                1 -> "Halo"
                else -> "error" }
        }

        //How are you?
        message.contains("how are you") -> {
            when (random) {
                0 -> "I'm doing fine, thanks!"
                1 -> "I'm hungry..."
                2 -> "Pretty good! How about you?"
                else -> "error"
            }
        }

        //What time is it?
        message.contains("time") || message.contains("?")-> {
            val timeStamp = (System.currentTimeMillis())
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
            val date = sdf.format(Date(timeStamp))

            date.toString()

        }

        //What Routes (1)
        /*message.contains("from pasar senen") && message.contains("to") && message.contains("kampung melayu") ||
                message.contains("from RSPAD") && message.contains("to") && message.contains("kampung melayu") ||
                message.contains("from ") && message.contains("to") && message.contains("") ||
                message.contains("from ") && message.contains("to") && message.contains("") ||
                message.contains("from ") && message.contains("to") && message.contains("") ||
                message.contains("from ") && message.contains("to") && message.contains("") ||
                message.contains("from ") && message.contains("to") && message.contains("") ||
                message.contains("from ") && message.contains("to") && message.contains("") ||
                message.contains("from ") && message.contains("to") && message.contains("") ||
                message.contains("from ") && message.contains("to") && message.contains("") ||
                message.contains("from ") && message.contains("to") && message.contains("") ||
                message.contains("from ") && message.contains("to") && message.contains("") ||
                message.contains("from ") && message.contains("to") && message.contains("") ||
                message.contains("from ") && message.contains("to") && message.contains("") ||
                message.contains("from ") && message.contains("to") && message.contains("") ||
                message.contains("from ") && message.contains("to") && message.contains("") ||
                message.contains("from ") && message.contains("to") && message.contains("") ||
                message.contains("from ") && message.contains("to") && message.contains("")-> {
            val timeStamp = (System.currentTimeMillis())
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
            val date = sdf.format(Date(timeStamp))

            date.toString()

        }*/

        //What Routes (1)
        message.contains("from") && message.contains("to") -> {
            when {
                message.contains("kampung melayu") && message.contains("pasar senen") -> {
                    handleRouteRequest(message, "kampung melayu", "pasar senen")
                }
                message.contains("kampung melayu") && message.contains("rspad") -> {
                    handleRouteRequest(message, "kampung melayu", "rspad")
                }
                message.contains("gang suzuki") && message.contains("sekolah cahaya sakti otista i") -> {
                    handleRouteRequest(message, "gang suzuki", "sekolah cahaya sakti otista i")
                }
                else -> {
                    "Please provide valid route information."
                }
            }
        }

        //When the programme doesn't understand...
        else -> {
            when (random) {
                0 -> "Sorry, I don't understand..."
                1 -> "Try asking me something different"
                2 -> "Please let me know where are you at and where do you want to go, maybe you can use this template: I'm from ... and I want to go to ..."
                else -> "error"
            }
        }
    }
}
private fun handleRouteRequest(message: String, startLocation: String, endLocation: String): String {
    val endLocationSubstring: String = message.substringAfterLast("to", "").trim().toLowerCase()
    val startLocationSubstring: String = message.substringAfterLast("from", "").substringBefore("to").trim().toLowerCase()

    return try {
        val answer = Route1.routes(startLocationSubstring, endLocationSubstring)
        "$answer"
    } catch (e: Exception) {
        "Error processing the route request."
    }
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
//            }
//        }
//    }


//@Preview(showBackground = true, device = Devices.PIXEL_4)
//@Composable
//fun HomeContentPreview() {
//    AngkotTheme {
////        HomeContent()
//    }
//}