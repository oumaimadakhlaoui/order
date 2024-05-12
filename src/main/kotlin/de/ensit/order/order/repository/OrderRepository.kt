package de.ensit.order.order.repository

import de.ensit.order.order.repository.entity.OrderEntity
import de.ensit.order.order.repository.entity.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: JpaRepository<OrderEntity, Long> {
    fun findByStatus(orderStatus: OrderStatus): List<OrderEntity>
}