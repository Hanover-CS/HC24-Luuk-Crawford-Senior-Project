package edu.hanover.hc24_luuk_crawford_senior_project

import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.CurrentOrderManager
import org.junit.Assert.assertEquals
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

}