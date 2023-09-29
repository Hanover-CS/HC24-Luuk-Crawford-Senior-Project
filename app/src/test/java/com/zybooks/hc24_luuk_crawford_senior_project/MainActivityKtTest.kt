package com.zybooks.hc24_luuk_crawford_senior_project

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame
import org.junit.Test

internal class MainActivityKtTest {
    @Test
    fun newMenuListTest() {
        assertEquals(3, 3)
        val testMenuList = mutableListOf<MenuItem>()

        assertEquals(testMenuList, myMenuList)

    }

    @Test
    fun fillMenuListTest() {
        val testMenuList = mutableListOf<MenuItem>()
        val itemExample = MenuItem(
            name = "VeggieBurgerTest",
            id = 101,
            customizationType = "Burger",
            imageLink = "https://i.imgur.com/K6alfDv.jpeg"
        )
        myMenuList.add(itemExample)
        assertNotSame(testMenuList, myMenuList)

        testMenuList.add(itemExample)
        assertEquals(testMenuList, myMenuList)
    }

    @Test
    fun createItemFromFoodTest() {
        val food = mapOf(
            "name" to "candy Apple",
            "id" to 300,
            "customizationType" to "burger",
            "imageLink" to "www.something../jpeg"
        )
        val createdItem = createItemFrom(food)

        //ensure created correctly
        val testItemSame = MenuItem("candy Apple", 300, "burger", "www.something../jpeg")
        assertEquals(testItemSame, createdItem)

        //making sure it's not always returning same
        val testItemDifferent = MenuItem()
        assertNotSame(testItemDifferent, createdItem)
    }

    @Test
    fun createItemFromFoodTestSmallDifferences(){
        val food = mapOf(
            "name" to "veggie Burger",
            "id" to 10,
            "customizationType" to "burger",
            "imageLink" to "www.something../jpeg"
        )
        val createdItem = createItemFrom(food)

        //different ID
        val testItemDifferentID = MenuItem("veggie Burger", 22, "burger", "www.something../jpeg")
        assertNotSame(testItemDifferentID, createdItem)

        //different name string
        val testItemDifferentName = MenuItem("not apple", 10, "burger", "www.something../jpeg")
        assertNotSame(testItemDifferentName, createdItem)
    }






}

