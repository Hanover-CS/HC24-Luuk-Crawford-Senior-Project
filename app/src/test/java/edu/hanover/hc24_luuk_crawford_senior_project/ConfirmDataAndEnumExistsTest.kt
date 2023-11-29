package edu.hanover.hc24_luuk_crawford_senior_project

import edu.hanover.hc24_luuk_crawford_senior_project.data.Customization
import edu.hanover.hc24_luuk_crawford_senior_project.data.Destination
import edu.hanover.hc24_luuk_crawford_senior_project.data.ItemStatus
import edu.hanover.hc24_luuk_crawford_senior_project.data.UserOrder
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Making sure classes that don't have functions exist correctly.
 */
class ConfirmDataAndEnumExistsTest {

    @Test
    fun destinationEnumExists(){
        val welcomeName = Destination.welcomeScreen.name
        val menuName = Destination.menuScreen.name
        val toppingName = Destination.toppingsScreen.name

        assertEquals("welcomeScreen",welcomeName)
        assertEquals("menuScreen",menuName)
        assertEquals("toppingsScreen",toppingName)
    }
    @Test
    fun itemStatusEnumExists(){
        val cancelledName = ItemStatus.cancelled.name
        val inProgressName = ItemStatus.inProgress.name
        val completeName = ItemStatus.complete.name

        assertEquals("cancelled", cancelledName)
        assertEquals("inProgress", inProgressName)
        assertEquals("complete", completeName)
    }

    @Test
    fun userOrderDataClassExists(){
        val sides: MutableList<String> = mutableListOf("side1","side2")
        val toppings: MutableList<String> = mutableListOf("topping1", "topping2")
        val userOrder = UserOrder("userName",1000L,2000L,3000L,
            Customization(sides,toppings),ItemStatus.inProgress,4000L,5000L)

        assertEquals("userName", userOrder.user)
        assertEquals(1000L, userOrder.userID)
        assertEquals(2000L, userOrder.orderID)
        assertEquals(3000L, userOrder.itemID)
        assertEquals(sides, userOrder.customization.sides)
        assertEquals(toppings, userOrder.customization.toppings)
        assertEquals(ItemStatus.inProgress, userOrder.itemStatus)
        assertEquals(4000L, userOrder.orderTime)
        assertEquals(5000L, userOrder.orderEndTime)
    }

    @Test
    fun testCustomizationDataClassExists(){
        val sides: MutableList<String> = mutableListOf("a","3")
        val toppings: MutableList<String> = mutableListOf("b")
        val sauces: MutableList<String> = mutableListOf("c")
        val glutenFree: MutableList<String> = mutableListOf("d","_")
        val custom = Customization(sides,toppings, sauces, glutenFree)
        assertEquals(sides, custom.sides)
        assertEquals(toppings, custom.toppings)
        assertEquals(sauces, custom.sauces)
        assertEquals(glutenFree, custom.glutenFree)
        assertNotEquals(custom.toppings, custom.glutenFree)
    }

}