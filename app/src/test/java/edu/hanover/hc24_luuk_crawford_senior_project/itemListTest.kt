package edu.hanover.hc24_luuk_crawford_senior_project

import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem
import edu.hanover.hc24_luuk_crawford_senior_project.services.createItemFrom
import junit.framework.TestCase
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class itemListTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

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
            "id" to 300,
            "customizationType" to "burger",
            "imageLink" to "www.something../jpeg"
        )
        val createdItem = createItemFrom(food)

        //ensure created correctly
        val testItemSame = MenuItem("candy Apple", 300, "burger", "www.something../jpeg")
        TestCase.assertEquals(testItemSame, createdItem)

        //making sure it's not always returning same
        val testItemDifferent = MenuItem()
        TestCase.assertNotSame(testItemDifferent, createdItem)
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
        TestCase.assertNotSame(testItemDifferentID, createdItem)

        //different name string
        val testItemDifferentName = MenuItem("not apple", 10, "burger", "www.something../jpeg")
        TestCase.assertNotSame(testItemDifferentName, createdItem)
    }

}