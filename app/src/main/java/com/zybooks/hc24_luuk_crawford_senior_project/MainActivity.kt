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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
var itemToLoad = MenuItem()
val customizationOptions = mutableMapOf<String, Customization>()


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

        val burgerSides = listOf("Hand Cut Fries Test", "Mac-N-Cheese", "Tater Tots")
        val burgerToppings = listOf("Lettuce Test", "Tomato", "Onion Test", "Pickle", "Cheese", "Bacon")
        customizationOptions["Burger"] = Customization(burgerSides,burgerToppings)

        val quesadillaToppings = listOf("Rice Test", "Black Beans", "Queso", "Chicken", "Tomatoes", "Lettuce Test", "Onions", "Jalapenos", "Sour Cream", "Salsa", "Guacamole", "Sub Gluten Free")
        val quesadillaSides = emptyList<String>()
        customizationOptions["Quesadilla"] = Customization(quesadillaSides,quesadillaToppings)
    }


}
fun downloadMenuFirebase() {
    downloadItems()
    downloadToppings()

}

private fun downloadToppings() {
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("customization")
    docRef.get()
        .addOnSuccessListener { document ->
            if (document != null) {
                //listDocumentItemsIn(document, myMenuList)
                Log.d(
                    TAG,
                    "TEST ${document} is CUSTOMIZATION document ${document?.javaClass?.simpleName}"
                )
                for (itemType in document) {
                    val customizationData = itemType.data
                    Log.d(TAG, "TEST ${customizationData} is ${customizationData?.javaClass?.simpleName} cutomizationData")

                    Log.d(TAG, "test sides ${customizationData["sides"]}")
                    //val customization = Customization(customizationData["sides"] as List<String>,customizationData["toppings"] as List<String>)

                    val sides = customizationData["sides"] as? List<String> ?: emptyList()
                    val toppings = customizationData["toppings"] as? List<String> ?: emptyList()

                    val customization = Customization(sides, toppings)

                    customizationOptions["${itemType.id}"] = customization

                    for( i in customizationOptions){
                        Log.d(TAG, "item i i i i ${i} in customizationOptions")
                        Log.d(TAG, "the id is ${itemType.id} keyyy ${i.key} valueee ${i.value}")
                    }
                }
            } else {
                Log.d(TAG, "No such document")
            }
        }
        .addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
}

private fun downloadItems() {
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("menuContent")
    docRef.get()
        .addOnSuccessListener { document ->
            if (document != null) {
                listDocumentItemsIn(document, myMenuList)
            } else {
                Log.d(TAG, "No such document")
            }
        }
        .addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
}

fun listDocumentItemsIn(document: QuerySnapshot, list: MutableList<MenuItem>) {

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
    startDestination: String = Destination.welcomeScreen.name,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        //TEST EXAMPLE TO FOLLOW
        val onNavigateToPlaceFunction = { navController.navigate(Destination.placeToGo.name) }
        composable(Destination.startLocation.name) {ExampleComposable(onNavigateToPlaceFunction)}
        composable(Destination.placeToGo.name) { locationToGo(/*no potential destination*/) }
        //test example end

        //activated when button clicked.
        val onNavigateToMenuFunction = {navController.navigate(Destination.menuScreen.name)}
        val onNavigateToToppingsFunction = { navController.navigate(Destination.toppingsScreen.name)}

        //composable(destination.place.name){ Destination(navigateButtonInstruction)}
        composable(Destination.welcomeScreen.name){ WelcomeScreen(onNavigateToMenuFunction) }

        composable(Destination.menuScreen.name) { MenuScreen(onNavigateToToppingsFunction)}

        composable(Destination.toppingsScreen.name){ToppingsScreen(itemToLoad)}

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
fun MenuScreen(onNavigateToToppings: () -> Unit){
    hcLogoText()

    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
        items(myMenuList.size){index ->

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .testTag("item${index}Exists")
                    .clickable {
                        itemToLoad = myMenuList[index]
                        onNavigateToToppings()
                    })
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
fun ToppingsScreen(item: MenuItem){
    LazyColumn(){
        item(){
            Text(text = "This is the toppings screen!")
            Text(text = item.name)
            
        }
        val itemCustomizations = customizationOptions[item.customizationType]
        itemCustomizations?.toppings?.let {
            items(it.size){ index ->
                Text(text = itemCustomizations.toppings[index])
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
