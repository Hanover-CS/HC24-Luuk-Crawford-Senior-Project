package edu.hanover.hc24_luuk_crawford_senior_project.data

import java.util.Date

/**
 * data class for user order
 */
data class UserOrder(
    var user: String,
    var userID: Long,
    val orderID: Long,
    var itemID: Long,
    var customization: Customization,
    var itemStatus: ItemStatus,
    var orderTime: Long,
    var orderEndTime: Long?
)