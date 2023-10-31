package edu.hanover.hc24_luuk_crawford_senior_project.data

/**
 * data class for each item on menu
 * @param name name of item
 * @param id id of item
 * @param customizationType category of customization
 * @param imageLink link to image (at least jpeg works)
 */
data class MenuItem(val name: String = "default item", val id: Int = 0,
                    val customizationType: String = "default type", val imageLink: String = "https://i.imgur.com/BDa36Zp.jpeg")


