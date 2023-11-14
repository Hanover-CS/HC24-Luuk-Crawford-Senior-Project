package edu.hanover.hc24_luuk_crawford_senior_project.data

object ActiveOrders {
    private var orders = mutableListOf<UserOrder>()

    fun clearActiveOrdersList(){
        orders = mutableListOf<UserOrder>()
    }

    fun addActiveOrder(newOrder: UserOrder){
        orders.add(newOrder)
    }

    fun getActiveOrdersList(): MutableList<UserOrder> {
        return orders
    }
}