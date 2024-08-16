package my.android.gamingappui.navDrawer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import my.android.gamingappui.R
import my.android.gamingappui.navigation.Routes

@Composable
fun DrawerContent(navController: NavController,
                  onItemClick: () -> Unit
) {
    Column(modifier = Modifier
        .background(Color(0xFF3F5A9E))
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(40.dp))

        Image(painter = painterResource(id = R.drawable.avatar2),
            contentDescription =null,
            Modifier
                .size(110.dp)
                .clip(CircleShape)
                .border(
                    BorderStroke(
                        4.dp,
                        color = Color(0xFF2E4277)
                    ),
                    shape = CircleShape
                ) )

        Text(
            text = "Ayush Sinha",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )
        Text(
            text = "ayushsinha.kumar19@gmail.com",
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(26.dp))

        Column(modifier = Modifier.fillMaxHeight()
            , verticalArrangement = Arrangement.SpaceEvenly) {


        DrawerItem("Profile", Icons.Default.Person) {
            onItemClick()
        }
        HorizontalDivider()

        DrawerItem("Home", Icons.Default.Home) {
            navController.navigate(Routes.home)
            onItemClick()
        }
        HorizontalDivider()

        DrawerItem("Setting", Icons.Default.Settings) {
            onItemClick()
        }
        HorizontalDivider()

        DrawerItem("Favorites", Icons.Default.Favorite) {
            onItemClick()
        }
            Box(modifier = Modifier.background(Color.White)
                .padding(vertical = 18.dp)
                ) {

                DrawerItem(
                    "Games",
                    iconRes = R.drawable.ic_controller, tint = Color.Black,
                    textColor = Color.Black
                ) {
                    onItemClick()
                }


            }
        DrawerItem("Message", iconRes = R.drawable.ic_chat_colored) {
            onItemClick()
        }

        HorizontalDivider()

        DrawerItem("Sign out", iconRes = R.drawable.ic_logout_colored) {
            onItemClick()
        }

        }
    }
}

@Composable
fun DrawerItem(
    title: String,
    icon: ImageVector? = null,
    iconRes: Int? = null,
    tint: Color = Color(0xFFB9D0F6),
    textColor: Color = Color(0xFFB9D0F6),
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(24.dp)
            )
        }
        iconRes?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(32.dp))
        Text(title, color = textColor)
    }
}

