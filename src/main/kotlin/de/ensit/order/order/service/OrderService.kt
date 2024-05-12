package de.ensit.order.order.service

import de.ensit.order.order.repository.OrderRepository
import de.ensit.order.order.repository.entity.OrderEntity
import de.ensit.order.order.repository.entity.OrderStatus
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepository: OrderRepository) {
    fun getOrders() = orderRepository.findAll()
    fun getPendingOrders() = orderRepository.findByStatus(OrderStatus.PENDING)
    fun getShippedOrders() = orderRepository.findByStatus(OrderStatus.SHIPPED)
    fun getDeliveredOrders() = orderRepository.findByStatus(OrderStatus.DELIVERED)
    fun getOrderById(orderId: Long) = orderRepository.findById(orderId)
    fun createOrUpdateOrder(orderEntity: OrderEntity) = orderRepository.save(orderEntity)
    fun deleteOrder(orderId: Long) = orderRepository.deleteById(orderId)
}