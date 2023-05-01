package com.loanapplication.doaminservice.action

import com.loanapplication.doaminservice.entity.Loan
import java.time.ZonedDateTime

class CreateLoanSolicitationAction(
    loan: Loan,
    createdAt: ZonedDateTime
): LoanSolicitationAction(loan, createdAt)