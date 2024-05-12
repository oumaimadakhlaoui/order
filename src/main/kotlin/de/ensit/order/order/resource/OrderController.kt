package de.ensit.order.order.resource

import de.ensit.order.order.service.OrderService
import de.ensit.order.order.to.Order
import org.springframework.web.bind.annotation.*

@RestController
class OrderController(private val orderService: OrderService) {

    @GetMapping("/orders")
    fun getOrders(): List<Order> =
        orderService.getOrders()

    @GetMapping("/orders/{orderId}")
    fun getOrderById(@PathVariable orderId: Long): Order =
        orderService.getOrderById(orderId)

    @PostMapping("/order")
    fun createOrder(order: Order): Order =
        orderService.createOrUpdateOrder(order)

    @PutMapping("/order")
    fun updateOrder(order: Order): Order =
        orderService.createOrUpdateOrder(order)

    @DeleteMapping("/order/{orderId}")
    fun deleteOrder(@PathVariable orderId: Long) =
        orderService.deleteOrder(orderId)

    @GetMapping("/orders/pending")
    fun getPendingOrders() = orderService.getPendingOrders()

    @GetMapping("/orders/shipped")
    fun getShippedOrders(): List<Order> =
        orderService.getShippedOrders()

    @GetMapping("/orders/delivered")
    fun getDeliveredOrders(): List<Order> =
        orderService.getDeliveredOrders()
}