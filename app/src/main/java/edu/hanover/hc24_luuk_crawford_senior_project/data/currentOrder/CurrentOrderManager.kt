package edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder

import edu.hanover.hc24_luuk_crawford_senior_project.data.Customization
import edu.hanover.hc24_luuk_crawford_senior_project.data.ItemStatus
import edu.hanover.hc24_luuk_crawford_senior_project.data.UserOrder
import java.util.Date

/**
 * singleton for the order the user is making. A UserOrder.
 * Set/getters to update user's order.
 */
object CurrentOrderManager {

    private val currentOrder =
        UserOrder("", 0, 0, 0L, Customization(), ItemStatus.inProgress, 404L, null)

    fun getCurrentUserOrder(): UserOrder {
        return currentOrder
    }

    fun setUsersName(name: String) {
        currentOrder.user = name
    }

    fun getUserName(): String {
        return currentOrder.user
    }
    fun setOrderItemID(id: Long) {
        currentOrder.itemID = id
    }

    /**
     * add a customization to current order
     * @param customizationItem customization item (as string) to add to current order.
     * @param customizationCategory which category to add to (topping/sides list)
     */
    fun addOrderCustomization(customizationItem: String, customizationCategory: String) {
        when (customizationCategory) {
            "toppings" -> currentOrder.customization.toppings.add(customizationItem)
            "sides" -> currentOrder.customization.sides.add(customizationItem)
        }
    }
    /**
     * remove a customization to current order
     * @param customizationItem customization item (as string) to remove from current order.
     * @param customizationCategory which category to remove from (topping/sides list)
     */
    fun removeOrderCustomization(customizationItem: String, customizationCategory: String) {
        when (customizationCategory) {
            "toppings" -> currentOrder.customization.toppings.remove(customizationItem)
            "sides" -> currentOrder.customization.sides.remove(customizationItem)
        }
    }

    /**
     * get hash of current order.
     * @return hash of current user order
     */
    fun getCurrentOrderHash(): Int {
        return getCurrentUserOrder().hashCode()
    }

    fun setCurrentOrderTime() {
        currentOrder.orderTime = System.currentTimeMillis()
    }

    fun getCurrentOrderTime(): Long {
        return currentOrder.orderTime
    }

}

