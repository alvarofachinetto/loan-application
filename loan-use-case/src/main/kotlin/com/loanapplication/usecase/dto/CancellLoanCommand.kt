package com.loanapplication.usecase.dto

import com.loanapplication.doaminservice.entity.LoanStatus

data class CancellLoanCommand (
    val solicitationId: String
)