package edu.hanover.hc24_luuk_crawford_senior_project.services

import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem

fun createItemFrom(food: Map<String, Any>): MenuItem {
    return MenuItem(
        name = "${food["name"]}",
        id = "${food["id"]}".toInt(),
        customizationType = "${food["customizationType"]}",
        imageLink = "${food["imageLink"]}"
    )
}