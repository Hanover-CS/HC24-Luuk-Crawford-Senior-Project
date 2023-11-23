package edu.hanover.hc24_luuk_crawford_senior_project.services

import edu.hanover.hc24_luuk_crawford_senior_project.data.Customization
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData.Companion.setItemTypeToCustomization
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData.Companion.addMenuItem
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData.Companion.clearMenuContents
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem

/**
 * local menu for testing
 */
fun downloadMenuLocal(){
    clearMenuContents()
    val menuExample = MenuItem(
        name = "Hamburger__Test",
        id = 100L,
        customizationType = "Burger",
        imageLink = "https://i.imgur.com/N22z5gY.jpeg"
    )
    addMenuItem(menuExample)
    val menuExample2 = MenuItem(
        name = "VeggieBurgerTest",
        id = 101L,
        customizationType = "Burger",
        imageLink = "https://i.imgur.com/K6alfDv.jpeg"
    )
    addMenuItem(menuExample2)

    val burgerSides = mutableListOf("Hand Cut Fries Test", "Mac-N-Cheese", "Tater Tots")
    val burgerToppings =
        mutableListOf("Lettuce Test", "Tomato", "Onion Test", "Pickle", "Cheese", "Bacon")
    setItemTypeToCustomization("Burger",Customization(burgerSides, burgerToppings))
    val quesadillaToppings = mutableListOf(
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
    val quesadillaSides = mutableListOf<String>()
    setItemTypeToCustomization("Quesadilla", Customization(quesadillaSides, quesadillaToppings))
}