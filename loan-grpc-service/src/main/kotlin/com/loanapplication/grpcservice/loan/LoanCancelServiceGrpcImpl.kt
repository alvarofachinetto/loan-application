package com.loanapplication.grpcservice.loan

import com.loan.application.grpc.service.LoanCancelRequest
import com.loan.application.grpc.service.LoanCancelResponse
import com.loan.application.grpc.service.LoanCancelServiceGrpc
import com.loanapplication.grpcservice.loan.extension.toCancelLoanCommand
import com.loanapplication.usecase.ports.input.service.LoanApplicationService
import io.grpc.stub.StreamObserver
import org.springframework.stereotype.Service

@Service
class LoanCancelServiceGrpcImpl(
    private val loanApplicationService: LoanApplicationService
): LoanCancelServiceGrpc.LoanCancelServiceImplBase() {

    override fun cancel(request: LoanCancelRequest?, responseObserver: StreamObserver<LoanCancelResponse>?) {

        val builder = LoanCancelResponse.newBuilder()

        loanApplicationService.cancellLoanSolicitation(request!!.toCancelLoanCommand())

        builder.message = "Solicitation ${request.solicitationId} was cancelled"

        responseObserver?.onNext(builder.build())
        responseObserver?.onCompleted()
    }

}