package edu.hanover.hc24_luuk_crawford_senior_project.data

/**
 * List of all active orders that were filled from database.
 */
object ActiveOrders {
    private var orders = mutableListOf<UserOrder>()

    fun clearActiveOrdersList(){
        orders = mutableListOf<UserOrder>()
    }

    fun addActiveOrder(newOrder: UserOrder){
        orders.add(newOrder)
    }

    /**
     * Returns previously downloaded list. Does not redownload.
     * @return list of saved active orders
     */
    fun getActiveOrdersList(): MutableList<UserOrder> {
        return orders
    }
}