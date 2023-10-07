package com.zybooks.hc24_luuk_crawford_senior_project


// ...

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.testTag
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
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot



//This setups loading to make my composable's work.
//- Starts AppNavHost

val myMenuList = mutableListOf<MenuItem>()
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost()
        }

        val firebaseIsEnabled = false
        if (firebaseIsEnabled){
            downloadMenuFirebase()
        }else{
            downloadMenuLocal()
        }


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


}
fun downloadMenuFirebase() {
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("menuContent")
    docRef.get()
        .addOnSuccessListener { document ->
            if (document != null) {
                listDocumentContentsIn(document, myMenuList)
            } else {
                Log.d(TAG, "No such document")
            }
        }
        .addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }


}
fun listDocumentContentsIn(document: QuerySnapshot, list: MutableList<MenuItem>) {

    Log.d(TAG, "TEST ${document} is food document")
    for (foodOffering in document) {
        val food = foodOffering.data
        Log.d(TAG, "TEST ${food} is food id${food["id"]}")
        val nextItem = createItemFrom(food)
        list.add(nextItem)
    }
}

fun createItemFrom(food: Map<String, Any>): MenuItem {
    return MenuItem(
        name = "${food["name"]}",
        id = "${food["id"]}".toInt(),
        customizationType = "${food["customizationType"]}",
        imageLink = "${food["imageLink"]}"
    )
}

/*
Nav host is the composable manager
- set start location
- handles which composable to load when triggered
 */
@Composable
fun AppNavHost(
    startDestination: String = Routes.welcomeScreen.name,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
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
        //test example end


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
fun beginOrderButton(onNavigateToMenu: () -> Unit) {
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
                modifier = Modifier.testTag("beginOrderButton")
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
fun locationInfoLogo() {
    Spacer(modifier = Modifier.height(8.dp))
    hcLogoText()
    Text(
        text = stringResource(id = R.string.underground), fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.testTag("undergroundText")
    )
}

@Composable
fun MenuScreen(){
    hcLogoText()

    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
        //https://developer.android.com/jetpack/compose/lists#lazy
        items(myMenuList.size){index ->

            //Button(onClick = { /*TODO*/ }, Modifier.fillMaxWidth().layout()) {
            Row(Modifier.fillMaxWidth()
                .padding(10.dp).
                clickable { /*TODO*/ })
            {
                Image(
                    painter = rememberAsyncImagePainter(myMenuList[index].imageLink),
                    contentDescription = stringResource(R.string.lostImage),
                    modifier = Modifier.size(70.dp)
                )

                Column(Modifier.padding(10.dp)) {
                    Text(text = myMenuList[index].name)
                    Text(text = "test")
                }

            }

        }
    }
}

@Composable
private fun hcLogoText() {
    Text(
        text = stringResource(id = R.string.collegeNameCaps),
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,

        textAlign = TextAlign.Center,
        color = colorResource(id = R.color.hanoverWebRed),
        modifier = Modifier.testTag("collegeNameText")
    )
}
