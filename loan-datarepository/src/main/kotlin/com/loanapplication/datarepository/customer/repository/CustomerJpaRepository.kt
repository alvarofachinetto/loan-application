package com.loanapplication.datarepository.customer.repository

import com.loanapplication.datarepository.customer.entity.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerJpaRepository: JpaRepository<CustomerEntity, String> {
    fun findByCpf(cpf: String): CustomerEntity?
}