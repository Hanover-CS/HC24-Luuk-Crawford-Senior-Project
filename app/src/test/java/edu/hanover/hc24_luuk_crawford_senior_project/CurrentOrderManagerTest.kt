package edu.hanover.hc24_luuk_crawford_senior_project

import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.CurrentOrderManager
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class CurrentOrderManagerTest {
    @Test
    fun SetNameTest(){
        CurrentOrderManager.setUsersName("Bob")
        assertEquals("Bob", CurrentOrderManager.getUserName())

        CurrentOrderManager.setUsersName("Apple 4_%#")
        assertEquals("Apple 4_%#", CurrentOrderManager.getUserName())

        val userOrder = CurrentOrderManager.getCurrentUserOrder()
        assertEquals("Apple 4_%#", userOrder.user)
    }

    @Test
    fun GetCurrentUserTest(){
        CurrentOrderManager.setUsersName("Sam")
        CurrentOrderManager.setOrderItemID(202L)
        val currentOrder = CurrentOrderManager.getCurrentUserOrder()

        assertEquals("Sam", currentOrder.user)
        assertEquals(202L, currentOrder.itemID)

        CurrentOrderManager.setUsersName("Sally")
        CurrentOrderManager.setOrderItemID(1000L)

        assertEquals("Sally", currentOrder.user)
        assertEquals(1000L, currentOrder.itemID)
    }

    @Test
    fun editingCustomizationsTest(){
        val order = CurrentOrderManager.getCurrentUserOrder()

        assertEquals(mutableListOf<String>(),order.customization.toppings)
        assertEquals(mutableListOf<String>(),order.customization.sides)

        testAddAndRemoveFromCustomizationCategory("toppings",  order.customization.toppings)
        testAddAndRemoveFromCustomizationCategory("sides", order.customization.sides)
    }

    private fun testAddAndRemoveFromCustomizationCategory(
        categoryString: String,
        OrderCustomizationList: MutableList<String>
    ) {
        val lettuceExampleEntry = "lettuce"
        val tomatoExampleEntry = "tomato"

        CurrentOrderManager.addOrderCustomization(lettuceExampleEntry, categoryString)
        CurrentOrderManager.addOrderCustomization(tomatoExampleEntry, categoryString)
        assertEquals(mutableListOf(lettuceExampleEntry, tomatoExampleEntry), OrderCustomizationList)

        CurrentOrderManager.removeOrderCustomization(lettuceExampleEntry, categoryString)
        assertEquals(mutableListOf(tomatoExampleEntry), OrderCustomizationList)

        CurrentOrderManager.removeOrderCustomization(tomatoExampleEntry, categoryString)
        assertEquals(mutableListOf<String>(), OrderCustomizationList)
    }

    @Test
    fun getCurrentOrderHashTest(){
        val hash1 = CurrentOrderManager.getCurrentOrderHash()
        CurrentOrderManager.setUsersName("tempName")
        val hash2 = CurrentOrderManager.getCurrentOrderHash()
        CurrentOrderManager.setOrderItemID(3323L)
        val hash3 = CurrentOrderManager.getCurrentOrderHash()


        assertNotEquals(hash1,hash2)
        assertNotEquals(hash2,hash3)
    }

    @Test
    fun currentOrderTimeTest(){
        val expectedStartingDefaultValue = 404L
        val actualStartingTime = CurrentOrderManager.getCurrentOrderTime()
        assertEquals(expectedStartingDefaultValue, actualStartingTime)

        CurrentOrderManager.setCurrentOrderTime()
        val currentTime = CurrentOrderManager.getCurrentOrderTime()

        assert(currentTime > 1700769100L)
    }
}