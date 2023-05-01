package com.loanapplication.usecase.mapper

import com.loanapplication.doaminservice.entity.Loan
import com.loanapplication.usecase.dto.CreateLoanCommand

fun CreateLoanCommand.toLoan(solicitationId: String): Loan =
    Loan(
        solicitationId = solicitationId,
        type = type,
        cpf = cpf,
        durationInMonths = durationInMonths,
        income = income,
        interestRate = interestRate
    )