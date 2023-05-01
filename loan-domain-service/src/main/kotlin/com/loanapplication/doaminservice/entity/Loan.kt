package com.loanapplication.doaminservice.entity

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.time.ZonedDateTime

data class Loan(
    val id: Int? = null,
    val solicitationId: String?= null,
    val type: LoanType,
    val income: BigDecimal,
    val interestRate: Double? = null,
    val durationInMonths: Int,
    var monthlyPayment: BigDecimal? = null,
    var totalPayment: BigDecimal? = null,
    var loanStatus: LoanStatus? = null,
    val cpf: String,
    val solicitationAt: ZonedDateTime? = null,
    val cancelledAt: ZonedDateTime? = null,
){

    fun monthlyPaymentWithTaxCalculation(): BigDecimal = income.divide(BigDecimal(durationInMonths), MathContext(2, RoundingMode.HALF_UP))
            .multiply(BigDecimal(interestRate!!))


    fun totalPaymentWithTaxCalculation() = monthlyPayment
            ?.times(BigDecimal(durationInMonths))


}

enum class LoanStatus {
    APPROVED, CANCELLED, PENDING
}

enum class LoanType {
    PERSONAL_LOAN,
    AUTO_LOAN,
    STUDENT_LOAN,
    MORTGAGE_LOAN,
    HOME_EQUITY_LOAN,
    CREDIT_BUILDER_LOAN,
    DEBIT_CONSOLIDATION_LOAN,
    PAYDAY_LOAN
}
