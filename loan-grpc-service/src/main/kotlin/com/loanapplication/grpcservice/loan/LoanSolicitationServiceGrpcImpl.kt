package com.loanapplication.grpcservice.loan

import com.loan.application.grpc.service.LoanSolicitationRequest
import com.loan.application.grpc.service.LoanSolicitationResponse
import com.loan.application.grpc.service.LoanSolicitationServiceGrpc
import com.loanapplication.grpcservice.loan.extension.toSolicitationLoanCommand
import com.loanapplication.usecase.ports.input.service.LoanApplicationService
import io.grpc.stub.StreamObserver
import org.springframework.stereotype.Service

@Service
class LoanSolicitationServiceGrpcImpl(
    private val loanApplicationService: LoanApplicationService
): LoanSolicitationServiceGrpc.LoanSolicitationServiceImplBase() {
    override fun process(request: LoanSolicitationRequest?, responseObserver: StreamObserver<LoanSolicitationResponse>?) {
        val builder = LoanSolicitationResponse.newBuilder()

        loanApplicationService
            .createLoanSolicitation(request!!.toSolicitationLoanCommand())

        builder.message = "Loan Solicitation Created!"

        responseObserver?.onNext(builder.build())
        responseObserver?.onCompleted()
    }
}