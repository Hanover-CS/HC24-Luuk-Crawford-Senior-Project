package edu.hanover.hc24_luuk_crawford_senior_project.data

/**
 * Customization lists bundled into a single data class.
 */
data class Customization(
    val sides: MutableList<String> = mutableListOf(),
    val toppings: MutableList<String> = mutableListOf(),
    val sauces: MutableList<String> = mutableListOf(),
    )