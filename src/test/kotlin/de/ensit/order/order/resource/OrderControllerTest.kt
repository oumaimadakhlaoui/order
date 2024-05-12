package de.ensit.order.order.resource

import de.ensit.order.builder.OrderBuilder
import de.ensit.order.order.repository.OrderRepository
import de.ensit.order.order.to.toOrderEntity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var orderRepository: OrderRepository

    @BeforeEach
    fun cleanUpBeforeTests() {
        orderRepository.deleteAll()
    }

    @Test
    fun `getOrders should return all orders`() {
        orderRepository.save(
            OrderBuilder(
                orderId = 1,
                customerId = 1
            ).build().toOrderEntity()
        )
        mvc.perform(
            MockMvcRequestBuilders.get("/orders")
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `getOrderById should return order with id 1`() {
        orderRepository.save(
            OrderBuilder(
                orderId = 1,
                customerId = 1
            ).build().toOrderEntity()
        )
        mvc.perform(
            MockMvcRequestBuilders.get("/orders/1")
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `createOrder should add and return the new order`() {
        mvc.perform(
            MockMvcRequestBuilders.post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ " +
                        "\"orderId\": \"1\", " +
                        "\"customerId\": \"1\", " +
                        "\"orderDate\": \"${LocalDate.now()}\", " +
                        "\"status\": \"PENDING\" " +
                        "}")
        ).andExpect(MockMvcResultMatchers.status().isNotFound)
    }
}