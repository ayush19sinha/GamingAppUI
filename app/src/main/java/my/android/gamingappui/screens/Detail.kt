package my.android.gamingappui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import my.android.gamingappui.R
import my.android.gamingappui.data.Game
import my.android.gamingappui.data.gameList
import my.android.gamingappui.ui.theme.hurmeGeometricFamily

@Composable
fun Detail(navController: NavController, game: Game) {
    var imageVisibility by remember { mutableStateOf(false) }
    var textVisibility by remember { mutableStateOf(false) }
    var boxVisibility by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        imageVisibility = true
        delay(600L)
        textVisibility = true
        delay(600L)
        boxVisibility = true
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 46.dp)) {

        Column {
            AnimatedVisibility(
                visible = imageVisibility,
                enter = fadeIn(initialAlpha = 0.0f, animationSpec = tween(1800))
            ) {
                ImageLayer(navController = navController, game = game, onClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                })
            }
        }
        DetailLayer(game = game,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxHeight(0.52f))
    }
}


@Composable
fun ImageLayer(navController: NavController,
               game: Game,
               onClick:()-> Unit)
{
    val boxModifier = Modifier
        .clip(CircleShape)
        .background(Color(0xFF3F5A9E))

    Box(modifier = Modifier) {
        game.gameDetailImg?.let { painterResource(id = it) }?.let {
            Image(painter = it,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(440.dp))
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = boxModifier
                .padding(6.dp)
                .clickable { navController.popBackStack() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp))
            }
            Box(modifier = boxModifier
                .padding(8.dp)
                .clickable(onClick = onClick)) {
                Icon(imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp))
            }
        }
    }
}


@Composable
fun DetailLayer(game: Game, modifier: Modifier) {
    var textVisibility by remember { mutableStateOf(false) }
    var boxVisibility by remember { mutableStateOf(false) }
    val brush = Brush.linearGradient(
        listOf(Color(0xFF58E1F3), Color(0xFF2552E4)))

    LaunchedEffect(key1 = Unit, block =  {
        textVisibility = true
        delay(600L)
        boxVisibility = true
    })

    Box(modifier = modifier
        .clip(RoundedCornerShape(30.dp))
        .background(Color.Transparent)) {
        Column(
            Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)

            ) {

                Spacer(modifier = Modifier.height(40.dp))

                game.smallTitle?.let {
                    Text(
                        text = it,
                        fontFamily = hurmeGeometricFamily,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                game.subTitle?.let {
                    Text(
                        text = it,
                        fontFamily = hurmeGeometricFamily
                    )
                }
                Spacer(modifier = Modifier.height(11.dp))

                game.desc?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        fontFamily = hurmeGeometricFamily,
                        modifier = Modifier.padding(26.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                AnimatedVisibility(
                    visible = textVisibility,
                    enter = scaleIn(animationSpec = tween(1000,
                        delayMillis = 600))

                ) {
                Button(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(Color(0xFF2690DE))
                ) {
                    Text(text = "Play Now", color = Color.White)
                }}
                Spacer(modifier = Modifier.height(50.dp))
            }
            Box(modifier = Modifier) {


                Box(modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(brush)
                    .fillMaxWidth()
                    .height(126.dp)
                ) {
                    Row(Modifier.fillMaxSize()) {
                        Image(painter = painterResource(id = R.drawable.robot),
                            contentDescription = null)
                        Column(Modifier.padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally) {

                            Text(
                                text = "Fortnite",
                                fontSize = 24.sp,
                                fontFamily = hurmeGeometricFamily,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Tournament",
                                fontSize = 24.sp,
                                fontFamily = hurmeGeometricFamily,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = { },
                                colors = ButtonDefaults.buttonColors(Color(0xFF2690DE))
                            ) {

                                Text(text = "Join Now", color = Color.White)
                            }
                        }
                    }
                }

            }
        }
    }
}

@Preview(device = Devices.PIXEL_6A)
@Composable
private fun DetailPreview() {
    val navController = rememberNavController()
    Detail(navController = navController, game = gameList[1])
}
