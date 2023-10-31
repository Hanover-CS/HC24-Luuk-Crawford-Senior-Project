package edu.hanover.hc24_luuk_crawford_senior_project.data

/**
 * sides and toppings list bundled into a single Customization data class.
 */
data class Customization(
    val sides: MutableList<String> = mutableListOf(),
    val toppings: MutableList<String> = mutableListOf()
                         )