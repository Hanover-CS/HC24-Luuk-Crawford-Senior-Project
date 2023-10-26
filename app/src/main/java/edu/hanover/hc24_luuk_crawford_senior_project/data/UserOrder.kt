package edu.hanover.hc24_luuk_crawford_senior_project.data

data class UserOrder(var user: String,
                     var userId: Int,
                     val orderId: Int,
                     var itemId: Int,
                     var customization: Customization,
                     var itemStatus: ItemStatus,
                     var orderTime: String,
                     var orderEndTime: String)