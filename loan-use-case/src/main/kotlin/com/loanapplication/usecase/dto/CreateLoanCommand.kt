package com.loanapplication.usecase.dto

import com.loanapplication.doaminservice.entity.LoanType
import java.math.BigDecimal

class CreateLoanCommand(
    val type: LoanType,
    var interestRate: Double? = null,
    val income: BigDecimal,
    val durationInMonths: Int,
    val cpf: String
)


