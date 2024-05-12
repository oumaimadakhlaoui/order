package de.ensit.order.order.service

import de.ensit.order.order.repository.OrderRepository
import de.ensit.order.order.repository.entity.OrderStatus
import de.ensit.order.order.to.Order
import de.ensit.order.order.to.toOrder
import de.ensit.order.order.to.toOrderEntity
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository
) {
    fun getOrders(): List<Order> =
        orderRepository.findAll().map { it.toOrder() }

    fun getPendingOrders(): List<Order> =
        orderRepository.findByStatus(OrderStatus.PENDING).map { it.toOrder() }

    fun getShippedOrders(): List<Order> =
        orderRepository.findByStatus(OrderStatus.SHIPPED).map { it.toOrder() }

    fun getDeliveredOrders(): List<Order> =
        orderRepository.findByStatus(OrderStatus.DELIVERED).map { it.toOrder() }

    fun getOrderById(orderId: Long): Order =
        orderRepository.findById(orderId).get().toOrder()

    fun createOrUpdateOrder(order: Order): Order =
        orderRepository.save(order.toOrderEntity()).toOrder()

    fun deleteOrder(orderId: Long) =
        orderRepository.deleteById(orderId)

}
