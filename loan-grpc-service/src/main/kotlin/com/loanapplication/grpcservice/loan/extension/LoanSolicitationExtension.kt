package com.loanapplication.grpcservice.loan.extension

import com.loanapplication.doaminservice.entity.LoanType
import com.loanapplication.usecase.dto.CreateLoanCommand
import java.math.BigDecimal
import com.loan.application.grpc.service.LoanSolicitationRequest

fun LoanSolicitationRequest.toSolicitationLoanCommand() =
    CreateLoanCommand(
        type = LoanType.valueOf(type.toString()),
        income = BigDecimal(income),
        durationInMonths = durationInMonths,
        cpf = cpf
    )