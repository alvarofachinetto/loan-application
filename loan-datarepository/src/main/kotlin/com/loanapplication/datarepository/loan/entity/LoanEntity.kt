package com.loanapplication.datarepository.loan.entity

import com.loanapplication.datarepository.customer.entity.CustomerEntity
import com.loanapplication.doaminservice.entity.LoanStatus
import com.loanapplication.doaminservice.entity.LoanType
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.ZonedDateTime

@Entity
@Table(name = "LOAN")
class LoanEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,
    val solicitationId: String,
    @Enumerated(EnumType.STRING)
    val type: LoanType,
    val income: BigDecimal,
    val interestRate: Double,
    val durationInMonths: Int,
    var monthlyPayment: BigDecimal,
    var totalPayment: BigDecimal,
    @Enumerated(EnumType.STRING)
    var loanStatus: LoanStatus,
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "customerId")
    val customer: CustomerEntity,
    val solicitationAt: ZonedDateTime? = null,
    var cancelledAt: ZonedDateTime? = null
){

}