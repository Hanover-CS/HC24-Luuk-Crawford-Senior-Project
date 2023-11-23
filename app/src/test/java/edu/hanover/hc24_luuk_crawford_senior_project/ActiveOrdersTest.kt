package edu.hanover.hc24_luuk_crawford_senior_project

import edu.hanover.hc24_luuk_crawford_senior_project.data.ActiveOrders
import edu.hanover.hc24_luuk_crawford_senior_project.data.Customization
import edu.hanover.hc24_luuk_crawford_senior_project.data.ItemStatus
import edu.hanover.hc24_luuk_crawford_senior_project.data.UserOrder
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ActiveOrdersTest {

    @Before
    fun clearActiveOrdersBefore(){
        ActiveOrders.clearActiveOrdersList()
    }
    @Test
    fun addActiveOrderTest(){
        val exampleCustomization = Customization(mutableListOf("a"), mutableListOf("b"))
        val unixOrderStartTime = 1700746279L


        val exampleOrder = UserOrder("user1", 5000, 7000,200, exampleCustomization,ItemStatus.inProgress,unixOrderStartTime,null)
        assertEquals(mutableListOf<UserOrder>(),ActiveOrders.getActiveOrdersList())
        ActiveOrders.addActiveOrder(exampleOrder)
        assertEquals(mutableListOf<UserOrder>(exampleOrder), ActiveOrders.getActiveOrdersList())
    }

    @Test
    fun clearActiveOrdersTest(){
        val exampleCustomization = Customization(mutableListOf("a"), mutableListOf("b"))
        val unixOrderStartTime = 1700746279L

        val exampleOrder = UserOrder("user1", 5000, 7000,200, exampleCustomization,ItemStatus.inProgress,unixOrderStartTime,null)
        ActiveOrders.addActiveOrder(exampleOrder)

        assertEquals(mutableListOf<UserOrder>(exampleOrder), ActiveOrders.getActiveOrdersList())
        ActiveOrders.clearActiveOrdersList()
        assertEquals(mutableListOf<UserOrder>(),ActiveOrders.getActiveOrdersList())

    }
}
