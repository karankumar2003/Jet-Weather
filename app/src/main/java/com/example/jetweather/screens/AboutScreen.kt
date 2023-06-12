package com.example.jetweather.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetweather.R
import com.example.jetweather.components.WeatherAppBar


@Composable
fun AboutScreen(navController: NavHostController) {

    Scaffold(topBar = {
        WeatherAppBar(navController = navController, title = "About", isMainScreen = false)
    }
    ) {

        Column(Modifier.padding(it)) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                contentAlignment = Alignment.Center
            ) {


                Icon(
                    painter = painterResource(id = R.drawable.sun_cloud),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center),
                    tint = MaterialTheme.colorScheme.primary
                )


            }
            val uriHandler = LocalUriHandler.current
            val context = LocalContext.current

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {
                    uriHandler.openUri("https://github.com/karankumar2003")
                }, modifier = Modifier.size(50.dp)) {
                    Icon(
                        painterResource(id = R.drawable.github),
                        "Github",
                        modifier = Modifier.size(30.dp)
                    )
                }
                IconButton(onClick = {
                    uriHandler.openUri("https://linkedin.com/in/karan-kumar-81469b278")
                }, modifier = Modifier.size(50.dp)) {
                    Icon(
                        painterResource(id = R.drawable.linkedin),
                        "LinkedIn",
                        modifier = Modifier.size(30.dp)
                    )
                }
                IconButton(
                    onClick = {

                        val intent = Intent()
                        intent.setAction(Intent.ACTION_SENDTO)
                        intent.data = Uri.parse("mailto:")
                        intent.putExtra(Intent.EXTRA_EMAIL, "karankumark19k@gmail.com")
                       context.startActivity(intent)

                    }, modifier = Modifier
                        .size(50.dp)
                        .padding(4.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.gmail),
                        "Gmail",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun AboutScreenPreview(navController: NavHostController = rememberNavController()) {
    AboutScreen(navController = navController)

}