package edu.hanover.hc24_luuk_crawford_senior_project

fun downloadMenuLocal() {
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