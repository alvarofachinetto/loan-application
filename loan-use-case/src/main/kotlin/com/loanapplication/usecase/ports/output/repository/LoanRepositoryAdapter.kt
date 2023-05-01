package com.loanapplication.usecase.ports.output.repository

import com.loanapplication.doaminservice.entity.Loan

interface LoanRepositoryAdapter {

    fun createLoanSolicitation(loan: Loan)

    fun cancellLoanSolicitation(solicitationId: String)

}
