package com.zybooks.hc24_luuk_crawford_senior_project


// ...

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
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
var mySelections = mutableListOf<String>()

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost()
        }

        val firebaseIsEnabled = false
        if (firebaseIsEnabled) {
            downloadMenuFirebase()
        } else {
            downloadMenuLocal()
        }
    }

    private fun downloadMenuLocal() {
        myMenuList.clear()
        customizationOptions.clear()
        val menuExample = MenuItem(
            name = "Hamburger__Test",
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
        val burgerToppings =
            listOf("Lettuce Test", "Tomato", "Onion Test", "Pickle", "Cheese", "Bacon")
        customizationOptions["Burger"] = Customization(burgerSides, burgerToppings)

        val quesadillaToppings = listOf(
            "Rice Test",
            "Black Beans",
            "Queso",
            "Chicken",
            "Tomatoes",
            "Lettuce Test",
            "Onions",
            "Jalapenos",
            "Sour Cream",
            "Salsa",
            "Guacamole",
            "Sub Gluten Free"
        )
        val quesadillaSides = emptyList<String>()
        customizationOptions["Quesadilla"] = Customization(quesadillaSides, quesadillaToppings)
    }


}

fun downloadMenuFirebase() {
    downloadItems()
    downloadToppings()

}

private fun downloadToppings() {
    customizationOptions.clear()
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("customization")
    docRef.get()
        .addOnSuccessListener { document ->
            if (document != null) {
                for (itemType in document) {
                    val customizationData = itemType.data

                    val sides = customizationData["sides"] as? List<String> ?: emptyList()
                    val toppings = customizationData["toppings"] as? List<String> ?: emptyList()

                    val customization = Customization(sides, toppings)

                    customizationOptions["${itemType.id}"] = customization
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
    myMenuList.clear()
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
        composable(Destination.startLocation.name) { ExampleComposable(onNavigateToPlaceFunction) }
        composable(Destination.placeToGo.name) { locationToGo(/*no potential destination*/) }
        //test example end

        //activated when button clicked.
        val onNavigateToMenuFunction = { navController.navigate(Destination.menuScreen.name) }
        val onNavigateToToppingsFunction =
            { navController.navigate(Destination.toppingsScreen.name) }

        //composable(destination.place.name){ Destination(navigateButtonInstruction)}
        composable(Destination.welcomeScreen.name) { WelcomeScreen(onNavigateToMenuFunction) }

        composable(Destination.menuScreen.name) { MenuScreen(onNavigateToToppingsFunction) }

        composable(Destination.toppingsScreen.name) { ToppingsScreen(itemToLoad) }

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
fun locationToGo() {
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
fun MenuScreen(onNavigateToToppings: () -> Unit) {
    hcLogoText()

    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
        for (menuItem in myMenuList){

            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .testTag(menuItem.name)
                        .clickable {
                            itemToLoad = menuItem
                            onNavigateToToppings()
                        })
                {
                    imageAndTexFor(menuItem)

                }
            }
        }
    }
}

@Composable
private fun imageAndTexFor(menuItem: MenuItem) {
    Row {
        Image(
            painter = rememberAsyncImagePainter(menuItem.imageLink),
            contentDescription = stringResource(R.string.lostImage),
            modifier = Modifier.size(70.dp)
    )
        Column(Modifier.padding(10.dp)) {
            Text(text = menuItem.name)
            Text(text = "test")
        }
    }
}

//start order, ID,
@Composable
fun ToppingsScreen(item: MenuItem) {
    val itemCustomization = customizationOptions[item.customizationType]
    Column {
            Text(text = "This is the toppings screen!")
            imageAndTexFor(menuItem = item)

        Text(text = stringResource(R.string.toppings))

        if (itemCustomization != null){
            createCheckboxForEach(itemCustomization.toppings)}
        else throw NullPointerException("Expression 'itemCustomization' must not be null")

        Text(text = stringResource(R.string.sides))
        createCheckboxForEach(itemCustomization.sides)

    }
}





@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun createCheckboxForEach(itemList: List<String>) {
    var checkedItems by remember { mutableStateOf(emptyList<String>()) }
    FlowRow {
        for (item in itemList) {
            CheckButtonFor(selectionName = item)
        }
    }
}


@Composable
private fun CheckButtonFor(selectionName: String) {
    var checked by remember { mutableStateOf(false) }
    Row(
        Modifier
            .toggleable(
                value = checked,
                role = Role.Checkbox,
                onValueChange = {
                    checked = !checked
                }

            )
            .padding(10.dp)
            .wrapContentWidth()
            .width(100.dp)
            .background(color = Color(32))
    
    ) {
        Checkbox(checked = checked,  { newValue ->
            checked = newValue
        })//onCheckedChange = null)
        //Text(text = if (checked) "Checked" else "Not Checked")
        if (checked){
            mySelections.add(selectionName)
            Log.d(TAG, "added ${selectionName}")
            Log.d(TAG, mySelections.toString())
        }else{
            mySelections.remove(selectionName)
            Log.d(TAG, "removed ${selectionName}")
            Log.d(TAG, mySelections.toString())
        }
        
        Text(selectionName, Modifier.weight(1f))
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
