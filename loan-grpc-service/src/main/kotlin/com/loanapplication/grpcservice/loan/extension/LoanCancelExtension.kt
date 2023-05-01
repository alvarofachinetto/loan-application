package com.loanapplication.grpcservice.loan.extension

import com.loan.application.grpc.service.LoanCancelRequest
import com.loanapplication.usecase.dto.CancellLoanCommand


fun LoanCancelRequest.toCancelLoanCommand() =
    CancellLoanCommand(
        solicitationId = solicitationId
    )