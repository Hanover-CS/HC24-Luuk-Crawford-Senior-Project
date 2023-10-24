package edu.hanover.hc24_luuk_crawford_senior_project.services

import edu.hanover.hc24_luuk_crawford_senior_project.data.Customization
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem

fun downloadMenuLocal(): MenuData {
    //myMenuList.clear()
    val menuList = mutableListOf<MenuItem>()
    val customizationType = mutableMapOf<String, Customization>()
    customizationType.clear()
    val menuExample = MenuItem(
        name = "Hamburger__Test",
        id = 100,
        customizationType = "Burger",
        imageLink = "https://i.imgur.com/N22z5gY.jpeg"
    )
    menuList.add(menuExample)
    val menuExample2 = MenuItem(
        name = "VeggieBurgerTest",
        id = 101,
        customizationType = "Burger",
        imageLink = "https://i.imgur.com/K6alfDv.jpeg"
    )
    menuList.add(menuExample2)

    val burgerSides = listOf("Hand Cut Fries Test", "Mac-N-Cheese", "Tater Tots")
    val burgerToppings =
        listOf("Lettuce Test", "Tomato", "Onion Test", "Pickle", "Cheese", "Bacon")
    customizationType["Burger"] = Customization(burgerSides, burgerToppings)

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
    customizationType["Quesadilla"] = Customization(quesadillaSides, quesadillaToppings)

    return MenuData(menuList,customizationType)
}