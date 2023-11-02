package edu.hanover.hc24_luuk_crawford_senior_project.data

import java.util.Date

/**
 * data class for user order
 */
data class UserOrder(
    var user: String,
    var userID: Int,
    val orderID: Int,
    var itemID: Long,
    var customization: Customization,
    var itemStatus: ItemStatus,
    var orderTime: Date?,
    var orderEndTime: Date?
)