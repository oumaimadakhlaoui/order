package de.ensit.order.order.to

import de.ensit.order.order.repository.entity.OrderEntity
import de.ensit.order.order.repository.entity.OrderStatus
import java.time.LocalDate

data class Order (
    val orderId: Long = 0L,
    val customerId: Long = 0L,
    val orderDate: LocalDate = LocalDate.now(),
    val status: OrderStatus = OrderStatus.PENDING
)

fun OrderEntity.toOrder() = Order(
    orderId = orderId,
    customerId = customerId,
    orderDate = orderDate,
    status= status
)

fun Order.toOrderEntity() = OrderEntity(
    orderId = orderId,
    customerId = customerId,
    orderDate = orderDate,
    status= status
)