package de.ensit.order.order.resource

import de.ensit.order.order.service.OrderService
import de.ensit.order.order.repository.entity.OrderEntity
import org.springframework.web.bind.annotation.*

@RestController
class OrderController(private val orderService: OrderService) {

    @GetMapping("/orders")
    fun getOrders() = orderService.getOrders()

    @GetMapping("/orders/{orderId}")
    fun getOrderById(@PathVariable orderId: Long) = orderService.getOrderById(orderId)

    @PostMapping("/order")
    fun createOrder(orderEntity: OrderEntity) = orderService.createOrUpdateOrder(orderEntity)

    @PutMapping("/order")
    fun updateOrder(orderEntity: OrderEntity) = orderService.createOrUpdateOrder(orderEntity)

    @DeleteMapping("/order/{orderId}")
    fun deleteOrder(@PathVariable orderId: Long) = orderService.deleteOrder(orderId)

    @GetMapping("/orders/pending")
    fun getPendingOrders() = orderService.getPendingOrders()

    @GetMapping("/orders/shipped")
    fun getShippedOrders() = orderService.getShippedOrders()

    @GetMapping("/orders/delivered")
    fun getDeliveredOrders() = orderService.getDeliveredOrders()
}