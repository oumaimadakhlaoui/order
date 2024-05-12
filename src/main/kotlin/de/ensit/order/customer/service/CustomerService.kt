package de.ensit.order.customer.service

import de.ensit.order.customer.repository.CustomerRepository
import de.ensit.order.customer.repository.entity.CustomerEntity

class CustomerService(
    private val customerRepository: CustomerRepository
) {
    fun getCustomers(): List<CustomerEntity> = customerRepository.findAll()
}