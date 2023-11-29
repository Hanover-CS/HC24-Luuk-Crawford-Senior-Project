package edu.hanover.hc24_luuk_crawford_senior_project

import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem
import edu.hanover.hc24_luuk_crawford_senior_project.services.createItemFrom
import junit.framework.TestCase
import org.junit.Test

class ItemListTest {

    @Test
    fun newMenuListTest() {
        val testMenuList = mutableListOf<MenuItem>()
        TestCase.assertEquals(testMenuList, MenuData.get().menuItemList)
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
        MenuData.addMenuItem(itemExample)
        TestCase.assertNotSame(testMenuList, MenuData.get().menuItemList)

        testMenuList.add(itemExample)
        TestCase.assertEquals(testMenuList, MenuData.get().menuItemList)
    }

    @Test
    fun createItemFromFoodTest() {
        val food = mapOf(
            "name" to "candy Apple",
            "id" to 300L,
            "customizationType" to "burger",
            "imageLink" to "www.something../jpeg"
        )
        val createdItem = createItemFrom(food)

        val testItemSame = MenuItem("candy Apple", 300, "burger", "www.something../jpeg")
        TestCase.assertEquals(testItemSame, createdItem)

        val testItemDifferent = MenuItem()
        TestCase.assertNotSame(testItemDifferent, createdItem)
    }

    /**
     * Tests different ID then different name tp ensure they are not considered the same.
     */
    @Test
    fun createItemFromFoodTestSmallDifferences(){
        val food = mapOf(
            "name" to "veggie Burger",
            "id" to 10L,
            "customizationType" to "burger",
            "imageLink" to "www.something../jpeg"
        )
        val createdItem = createItemFrom(food)

        val testItemDifferentID = MenuItem("veggie Burger", 22, "burger", "www.something../jpeg")
        TestCase.assertNotSame(testItemDifferentID, createdItem)

        val testItemDifferentName = MenuItem("not apple", 10, "burger", "www.something../jpeg")
        TestCase.assertNotSame(testItemDifferentName, createdItem)
    }

}