package com.loanapplication.usecase.ports.input.service

import com.loanapplication.doaminservice.entity.LoanStatus
import com.loanapplication.usecase.dto.CancellLoanCommand
import com.loanapplication.usecase.dto.CreateLoanCommand

interface LoanApplicationService {

    fun createLoanSolicitation(createLoanCommand: CreateLoanCommand)

    fun cancellLoanSolicitation(cancellLoanCommand: CancellLoanCommand)

}