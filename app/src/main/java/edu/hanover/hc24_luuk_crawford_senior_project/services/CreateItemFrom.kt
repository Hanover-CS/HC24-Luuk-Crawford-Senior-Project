package edu.hanover.hc24_luuk_crawford_senior_project.services

import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem

/**
 * creates a MenuItem using the map of info.
 * @param food map which contains name, id, customizationType, imageLink, and price
 * @return returns MenuItem it created
 */
fun createItemFrom(food: Map<String, Any>): MenuItem {

    return MenuItem(
        name = food["name"] as String,
        id = food["id"] as Long,
        customizationType = food["customizationType"] as String,
        imageLink = food["imageLink"] as String,
        price = food.getOrElse("price") { "Mealswipe" } as String
    )
}
