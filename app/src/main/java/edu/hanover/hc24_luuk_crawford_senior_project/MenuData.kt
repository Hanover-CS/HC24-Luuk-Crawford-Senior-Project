package edu.hanover.hc24_luuk_crawford_senior_project

data class MenuData(
    val menuItemList: MutableList<MenuItem>,
    val customizationOptions: MutableMap<String, Customization>
)