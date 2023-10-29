package edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder

import edu.hanover.hc24_luuk_crawford_senior_project.data.Customization
import edu.hanover.hc24_luuk_crawford_senior_project.data.ItemStatus
import edu.hanover.hc24_luuk_crawford_senior_project.data.UserOrder
import java.util.Date

object CurrentOrderManager {
    //basically a singleton
    private val currentOrder =
        UserOrder("", 0, 0, 0, Customization(), ItemStatus.inProgress, Date(1), null)

    fun getCurrentUserOrder(): UserOrder {
        return currentOrder
    }

    fun setUsersName(name: String) {
        currentOrder.user = name
    }

    fun getUserName(): String {
        return currentOrder.user
    }


    fun setOrderItemID(id: Int) {
        currentOrder.itemID = id
    }

    fun addOrderCustomization(customizationItem: String, customizationCategory: String) {
        when (customizationCategory) {
            "toppings" -> currentOrder.customization.toppings.add(customizationItem)
            "sides" -> currentOrder.customization.sides.add(customizationItem)
        }
    }

    fun removeOrderCustomization(customizationItem: String, customizationCategory: String) {
        when (customizationCategory) {
            "toppings" -> currentOrder.customization.toppings.remove(customizationItem)
            "sides" -> currentOrder.customization.sides.remove(customizationItem)
        }
    }

    fun getCurrentOrderHash(): Int {
        return getCurrentUserOrder().hashCode()
    }

    fun setCurrentOrderTime() {
        currentOrder.orderTime = Date(System.currentTimeMillis())
    }

    fun getCurrentOrderTime(): Date? {
        return currentOrder.orderTime
    }

}

