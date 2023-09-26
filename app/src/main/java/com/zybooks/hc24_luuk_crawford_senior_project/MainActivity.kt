package com.zybooks.hc24_luuk_crawford_senior_project

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log


import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material.Button


// ...
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.FirebaseFirestore

//This setups loading to make my composable's work.
//- Starts AppNavHost

val myMenuList = mutableListOf<MenuItem>()
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost()
        }
        //downloadMenuFirebase()
        downloadMenuLocal()

    }

    private fun downloadMenuLocal() {
        val menuExample = MenuItem(
            name = "HamburgerTest",
            id = 100,
            customizationType = "Burger",
            imageLink = "https://i.imgur.com/N22z5gY.jpeg"
        )
        myMenuList.add(menuExample)
        val menuExample2 = MenuItem(
            name = "VeggieBurgerTest",
            id = 101,
            customizationType = "Burger",
            imageLink = "https://i.imgur.com/K6alfDv.jpeg"
        )

        myMenuList.add(menuExample2)
    }

    private fun downloadMenuFirebase() {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("menuContent")

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    for (foodOffering in document){
                        val food = foodOffering.data
                        Log.d(TAG, "TEST ${food} is food id${food["id"]}")
                        val nextItem = MenuItem(
                            name = "${food["name"]}",
                            id = "${food["id"]}".toInt(),
                            customizationType = "${food["customizationType"]}",
                            imageLink = "${food["imageLink"]}"
                        )
                        myMenuList.add(nextItem)
                    }

                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }


    }
}

/*
Nav host is the composable manager
- set start location
- handles which composable to load when triggered
 */

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.welcomeScreen.name
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        //TEST EXAMPLE TO FOLLOW
        composable(Routes.startLocation.name) {
            ExampleComposable(
                onNavigateToPlace = { navController.navigate(Routes.placeToGo.name) },
            )
        }
        composable(Routes.placeToGo.name) { locationToGo(/*...*/) }
        //test example done


        composable(Routes.welcomeScreen.name){
            WelcomeScreen(
                onNavigateToMenu = {navController.navigate(Routes.menuScreen.name)},
            )
        }
        composable(Routes.menuScreen.name) { MenuScreen()}




    }

}

/*
Test example to test onNavigate interaction with NavHost
*/
@Composable
fun ExampleComposable(
    onNavigateToPlace: () -> Unit,
    /*...*/
) {
    /*...*/
    Button(onClick = onNavigateToPlace) {
        Text(text = "visit the place to go")
    }
}
@Composable
fun locationToGo(){
    Text(text = "wow you went to the place to go")
}

/*
* This is the starting screen.
* - shows underground and school name and such
* - button to navigate to menu
* */
@Composable
fun WelcomeScreen(onNavigateToMenu: () -> Unit) {

    Column {
        locationInfoLogo()
        beginOrderButton(onNavigateToMenu)
    }
}

@Composable
private fun beginOrderButton(onNavigateToMenu: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()


    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { onNavigateToMenu() },
                shape = RoundedCornerShape(18.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.beginOrder),
                    fontSize = 40.sp,
                    color = Color.White, textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun locationInfoLogo() {
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = stringResource(id = R.string.collegeNameCaps),
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,

        textAlign = TextAlign.Center,
        color = colorResource(id = R.color.hanoverWebRed)
    )
    Text(
        text = stringResource(id = R.string.underground), fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun MenuScreen(){
    LazyColumn {//https://developer.android.com/jetpack/compose/lists#lazy
        items(myMenuList.size){index ->

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            ) {
                Image(
                    painter = rememberImagePainter(myMenuList[index].imageLink),
                    contentDescription = stringResource(R.string.lostImage),
                    modifier = Modifier.size(70.dp)
                )
                Text(text = myMenuList[index].name)

            }

        }



        // Add 5 items
        items(5) { index ->
            Text(text = "Item: $index")
        }

        // Add another single item
        item {
            Text(text = "Last item")
        }
    }
}
