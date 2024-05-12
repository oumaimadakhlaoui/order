package de.ensit.order.order.service

import com.nhaarman.mockitokotlin2.whenever
import de.ensit.order.builder.OrderBuilder
import de.ensit.order.order.repository.OrderRepository
import de.ensit.order.order.repository.entity.OrderStatus
import de.ensit.order.order.to.toOrderEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.mock
import java.util.*

class OrderServiceTest {
    private lateinit var mockOrderRepository: OrderRepository
    private lateinit var orderService: OrderService

    @BeforeEach
    fun setup() {
        mockOrderRepository = mock()
        orderService = OrderService(
            orderRepository = mockOrderRepository
        )
    }

    @Test
    fun `getOrders should return all orders`() {
        // act
        val firstOrder = OrderBuilder(orderId = 1, customerId = 1).build()
        val secondOrder = OrderBuilder(orderId = 2, customerId = 1).build()
        val orders = listOf(
            firstOrder.toOrderEntity(),
            secondOrder.toOrderEntity()
        )
        whenever(mockOrderRepository.findAll()).thenReturn(orders)

        // arrange
        val result = orderService.getOrders()

        // assert
        assertThat(result.size).isEqualTo(2)
        assertThat(result.first()).isEqualTo(firstOrder)
        assertThat(result[1]).isEqualTo(secondOrder)
    }

    @Test
    fun `getPendingOrders should return list of pending orders`() {
        // act
        val order = OrderBuilder(orderId = 2, customerId = 1).build()
        val pendingOrders = listOf(
            order.toOrderEntity(),
        )
        whenever(mockOrderRepository.findByStatus(OrderStatus.PENDING))
            .thenReturn(pendingOrders)

        // arrange
        val result = orderService.getPendingOrders()

        // act
        assertThat(result.size).isEqualTo(1)
        assertThat(result.first()).isEqualTo(order)
        assertThat(result.first().status).isEqualTo(OrderStatus.PENDING)
    }

    @Test
    fun `getShippedOrders should return shipped orders`() {
        // act
        val order = OrderBuilder(
            orderId = 2,
            customerId = 1,
            status = OrderStatus.SHIPPED
        ).build()
        val shippedOrders = listOf(
            order.toOrderEntity(),
        )
        whenever(mockOrderRepository.findByStatus(OrderStatus.SHIPPED))
            .thenReturn(shippedOrders)

        // arrange
        val result = orderService.getShippedOrders()

        // act
        assertThat(result.size).isEqualTo(1)
        assertThat(result.first()).isEqualTo(order)
        assertThat(result.first().status).isEqualTo(OrderStatus.SHIPPED)
    }

    @Test
    fun `getDeliveredOrders should return delivered orders`() {
        // act
        val order = OrderBuilder(
            orderId = 2,
            customerId = 1,
            status = OrderStatus.DELIVERED
        ).build()
        val deliveredOrders = listOf(
            order.toOrderEntity(),
        )
        whenever(mockOrderRepository.findByStatus(OrderStatus.DELIVERED))
            .thenReturn(deliveredOrders)

        // arrange
        val result = orderService.getDeliveredOrders()

        // act
        assertThat(result.size).isEqualTo(1)
        assertThat(result.first()).isEqualTo(order)
        assertThat(result.first().status).isEqualTo(OrderStatus.DELIVERED)
    }

    @Test
    fun `getOrderById should return order with id equals 1`() {
        // act
        val orderId: Long = 1
        val order = OrderBuilder(orderId = orderId, customerId = 1).build()
        whenever(mockOrderRepository.findById(orderId))
            .thenReturn(Optional.of(order.toOrderEntity()))

        // arrange
        val result = orderService.getOrderById(orderId = orderId)

        // act
        assertThat(result).isEqualTo(order)
    }

    @Test
    fun createOrUpdateOrder() {
        // act
        val order = OrderBuilder(orderId = 1, customerId = 1).build()
        whenever(mockOrderRepository.save(order.toOrderEntity()))
            .thenReturn(order.toOrderEntity())

        // arrange
        val result = orderService.createOrUpdateOrder(order = order)

        // act
        assertThat(result).isEqualTo(order)
    }

}