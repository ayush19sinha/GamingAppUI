package my.android.gamingappui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import my.android.gamingappui.R
import my.android.gamingappui.data.Game
import my.android.gamingappui.data.gameList
import my.android.gamingappui.navDrawer.DrawerContent
import my.android.gamingappui.navigation.Routes
import my.android.gamingappui.ui.theme.hurmeGeometricFamily

@Composable
fun Home(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    navController = navController,
                    onItemClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                BottomBar(onClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                })
            }
        ) { padding ->

        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

                Column(
                    Modifier
                        .padding(top = 40.dp)
                        .padding(8.dp)
                ) {
                    TopCard()
                    Box(Modifier.padding(horizontal = 4.dp)) {
                        GameGrid(gameList = gameList, navController)
                    }
                    Spacer(modifier = Modifier.height(1200.dp))

            }
        }
    }
}
}

@Composable
fun TopCard() {
    val brush = Brush.linearGradient(
        listOf(
            Color(0xFF9FD1F6),
            Color(0xFF006DCB)
        )
    )

    var backgroundVisible by remember { mutableStateOf(false) }
    var textVisible by remember { mutableStateOf(false) }
    var imageVisible by remember { mutableStateOf(false) }
    var popularVisible by remember { mutableStateOf(false) }
    var gamesVisible by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        delay(300)
        backgroundVisible = true
        delay(750)
        textVisible = true
        delay(250)
        imageVisible = true
        delay(250)
        popularVisible = true
        delay(440)
        gamesVisible = true
    }


    AnimatedVisibility(
        visible = backgroundVisible,
        enter = fadeIn(tween(550)) + expandVertically( expandFrom = Alignment.Top, animationSpec = tween(550)),
    ) {

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .height(370.dp)
            .fillMaxWidth()
            .background(brush)
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        AnimatedVisibility(
            visible = textVisible,
            enter = fadeIn() + slideInVertically(),
        ) {
            Text(
                text = "Great games to play and stay connected",
                fontFamily = hurmeGeometricFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(27.dp)
            )
        }

        AnimatedVisibility(
            visible = imageVisible,
            enter = fadeIn() + slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight }
            ) + scaleIn(),
        ) {
        Box(
            Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.globe),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .offset(x = 90.dp, y = 35.dp)
                    .height(300.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            }
        }
        }
    }

    Spacer(modifier = Modifier.height(30.dp))

Row {
    AnimatedVisibility(
        visible = popularVisible,
        enter = fadeIn(tween(400)) ,
    ) {

    Text(
        text = "Popular Games",
        fontSize = 18.sp,
        fontFamily = hurmeGeometricFamily,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 4.dp)
    )
    }

}
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun GameGrid(gameList: List<Game>, navController: NavHostController) {
    val visibleItems = remember { mutableStateListOf<Boolean>().apply {
        repeat(gameList.size) { add(false) }
    }}

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(24.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(40.dp))
            .background(Color(0xFFF4FBFD))
    ) {
        items(gameList.size) { index ->
            val game = gameList[index]

            LaunchedEffect(key1 = index) {

                delay(if (index == 0) 2100L else (2100L + (index * 300L)))
                visibleItems[index] = true

            }

            AnimatedVisibility(
                visible = visibleItems[index],
                enter = fadeIn() ,
            ) {
                GameCard(
                    game = game,
                    onClick = {
                        navController.navigate(
                            Routes.detail.replace("{gameId}", game.gameId.toString())
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun GameCard(game: Game, onClick: () -> Unit) {
    Box(Modifier) {
        Column(
            Modifier
                .width(136.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(2.dp)
                .clickable(onClick = onClick),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = game.img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .clip(RoundedCornerShape(20.dp))

            )
            Text(
                text = game.title,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}

@Composable
fun BottomBar(onClick: () -> Unit) {

    var rowVisibility by remember { mutableStateOf(false) }
    var menuIconVisibility by remember { mutableStateOf(false) }
    var gameIconVisibility by remember { mutableStateOf(false) }
    var favouriteIconVisibility by remember { mutableStateOf(false) }
    var chatIconVisibility by remember { mutableStateOf(false) }
    var plusIconVisibility by remember { mutableStateOf(false) }
    var fabVisibility by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(3000L)
        rowVisibility = true
        delay(250L)
        menuIconVisibility = true
        delay(250L)
        gameIconVisibility = true
        delay(250L)
        favouriteIconVisibility = true
        delay(250L)
        chatIconVisibility = true
        delay(450L)
        plusIconVisibility = true
        delay(300L)
        fabVisibility = true
    }
    AnimatedVisibility(visible = rowVisibility,
        enter = fadeIn()) {

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(76.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 30.dp, topEnd = 30.dp,
                        bottomStart = 30.dp, bottomEnd = 30.dp
                    )
                )
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .drawWithContent {
                    drawContent()
                    drawCircle(
                        color = Color.White,
                        center = Offset(size.width / 2, size.height / 150),
                        radius = 36.dp.toPx(),
                        blendMode = BlendMode.Clear
                    )

                }
                .background(Color(0xFF3F5A9E))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { }
            ) {
                AnimatedVisibility(visible = menuIconVisibility,
                    enter = fadeIn()) {


                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .offset(x = 40.dp)
                        .clickable(onClick = onClick)
                )
                }

                AnimatedVisibility(visible = gameIconVisibility,
                    enter = fadeIn()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_controller),
                    contentDescription = "Controller",
                    tint = Color.White,
                    modifier = Modifier
                        .offset(x = 100.dp)
                        .size(24.dp)
                )
                }
                Spacer(modifier = Modifier.height(56.dp))
                AnimatedVisibility(visible =favouriteIconVisibility ,
                    enter = fadeIn()) {

                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = Color.White,
                    modifier = Modifier
                        .offset(x = 210.dp)
                        .size(24.dp)
                )
                }
                AnimatedVisibility(visible =chatIconVisibility ,
                    enter = fadeIn()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chat),
                    contentDescription = "Chat",
                    tint = Color.White,
                    modifier = Modifier
                        .offset(x = 270.dp)
                        .size(24.dp)
                )
                }
            }
        }

        FloatingActionButton(
            onClick = { /* TODO */ },
            containerColor = Color(0xFF3F5A9E),
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .padding(end = 6.dp)
                .size(56.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-46).dp, x = (2.5).dp)
        )
        {
            AnimatedVisibility(visible = plusIconVisibility, enter = fadeIn()) {


            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                modifier = Modifier.size(30.dp)
            )
            }
        }

    }
}
}

@Preview
@Composable
private fun HomePreview() {
    val navController = rememberNavController()
    Home(navController = navController)
}




/// used to make cuts

@Preview
@Composable
fun TopLeftCircle() {
    Box(modifier = Modifier
        .size(100.dp)
        .graphicsLayer {
            compositingStrategy = CompositingStrategy.Offscreen
        }
        .drawWithContent {
            drawContent()
            drawCircle(
                color = Color(0xFFFFFFFF),
                center = Offset(x = 0f, y = 0f),
                radius = 100f,
                blendMode = BlendMode.DstOut
            )
        }
        .background(color = Color(0xFFFFFFFF))
    ) {
        Text("There is text and other content here!")
    }
}

////

//Box(modifier = Modifier
//.graphicsLayer {
//    compositingStrategy = CompositingStrategy.Offscreen
//}
//.drawWithContent {
//    drawContent()
//    drawCircle(
//        color = Color(0xFFFFFFFF),
//        center = Offset(x = 0f, y = 0f),
//        radius = 100f,
//        blendMode = BlendMode.DstOut
//    )
//}
//)



//There are 3 key parts to this:
//
//drawWithContent lets you customise how your Box (or other @Composable) content is drawn to screen, so that a circle (or any other shape) can be drawn on top of the content.
//The colour doesn’t matter, only the shape / position does.
//As this new shape is being drawn on top, a blendMode of BlendMode.DstOut (a Porter/Duff blend mode1) states that the new shape should be subtracted from the original content.
//Setting the compositingStrategy to Offscreen forces the content & any shapes to be drawn to a buffer then placed on screen at once. This is required to allow the blendMode to function.
//Available since 1.4.0, previous versions can use alpha = 0.99f instead to force the behaviour.
