package com.loanapplication.doaminservice

import com.loanapplication.doaminservice.entity.Loan
import com.loanapplication.doaminservice.entity.LoanStatus
import com.loanapplication.doaminservice.exception.LoanDomainException
import org.springframework.stereotype.Component
import java.math.BigDecimal
@Component
class LoanDomainServiceImpl: LoanDomainService {

    override fun initializeLoanProcess(loan: Loan): Loan {

        incomeValidate(loan.income)

        loan.monthlyPayment = loan.monthlyPaymentWithTaxCalculation()

        loan.totalPayment = loan.totalPaymentWithTaxCalculation()
        loan.loanStatus = LoanStatus.PENDING
        return loan;
    }

    private fun incomeValidate(income: BigDecimal){
        if(!income.isGreaterThanZero())
            throw LoanDomainException("Income must be greater than zero!")
    }

    private fun BigDecimal.isGreaterThanZero(): Boolean = this > BigDecimal.ZERO

}