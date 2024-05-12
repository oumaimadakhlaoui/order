package de.ensit.order.order.repository.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class OrderEntity(
    @Id
    @GeneratedValue
    val orderId: Long = 0,
    val customerId: Long = 0,
    val orderDate: LocalDate = LocalDate.now(),
    @Enumerated(EnumType.STRING)
    @Column
    val status: OrderStatus = OrderStatus.PENDING,
)

enum class OrderStatus {
    PENDING, SHIPPED, DELIVERED
}