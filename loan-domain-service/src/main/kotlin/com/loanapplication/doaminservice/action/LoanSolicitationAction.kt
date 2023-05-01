package com.loanapplication.doaminservice.action

import com.loanapplication.doaminservice.entity.Loan
import java.time.ZonedDateTime

open class LoanSolicitationAction(
    val loan: Loan,
    val createdAt: ZonedDateTime
)