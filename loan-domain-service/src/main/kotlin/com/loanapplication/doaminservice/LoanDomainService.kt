package com.loanapplication.doaminservice

import com.loanapplication.doaminservice.entity.Loan

interface LoanDomainService {

    fun initializeLoanProcess(loan: Loan) : Loan

}