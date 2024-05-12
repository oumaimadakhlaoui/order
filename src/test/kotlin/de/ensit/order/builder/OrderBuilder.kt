package de.ensit.order.builder

import de.ensit.order.order.repository.entity.OrderStatus
import de.ensit.order.order.to.Order
import java.time.LocalDate

data class OrderBuilder(
    val orderId: Long = 0L,
    val customerId: Long = 0L,
    val orderDate: LocalDate = LocalDate.now(),
    val status: OrderStatus = OrderStatus.PENDING
) {
    fun build(): Order = Order(
        orderId = orderId,
        customerId = customerId,
        orderDate = orderDate,
        status= status
    )
}