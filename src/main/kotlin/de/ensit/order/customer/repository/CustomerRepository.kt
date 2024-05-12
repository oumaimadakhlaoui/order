package de.ensit.order.customer.repository

import de.ensit.order.customer.repository.entity.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<CustomerEntity, Long> {
    // test
}