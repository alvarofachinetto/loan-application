package com.loanapplication.datarepository.loan.repository

import com.loanapplication.datarepository.loan.entity.LoanEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanJpaRepository: JpaRepository<LoanEntity, String> {
    fun findBySolicitationId(solicitationId: String): LoanEntity?
}