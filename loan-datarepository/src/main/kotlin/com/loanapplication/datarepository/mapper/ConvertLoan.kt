package com.loanapplication.datarepository.mapper

import com.loanapplication.datarepository.customer.entity.CustomerEntity
import com.loanapplication.datarepository.loan.entity.LoanEntity
import com.loanapplication.doaminservice.entity.Loan
import java.time.ZoneId
import java.time.ZonedDateTime

fun Loan.toEntity(customer: CustomerEntity) =
    LoanEntity(
        solicitationId = solicitationId!!,
        type = type,
        income = income,
        interestRate = interestRate!!,
        durationInMonths = durationInMonths,
        monthlyPayment = monthlyPayment!!,
        totalPayment = totalPayment!!,
        loanStatus = loanStatus!!,
        customer = customer,
        solicitationAt = ZonedDateTime.now(ZoneId.of("UTC"))
    )