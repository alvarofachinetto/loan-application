package com.loanapplication.usecase

import com.loanapplication.doaminservice.LoanDomainService
import com.loanapplication.doaminservice.entity.LoanType
import com.loanapplication.usecase.dto.CancellLoanCommand
import com.loanapplication.usecase.dto.CreateLoanCommand
import com.loanapplication.usecase.mapper.toLoan
import com.loanapplication.usecase.ports.input.service.LoanApplicationService
import com.loanapplication.usecase.ports.output.repository.LoanRepositoryAdapter
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.util.*

@Service
class LoanApplicationServiceImpl(
    val loanRepositoryAdapter: LoanRepositoryAdapter,
    val loanDomainService: LoanDomainService
): LoanApplicationService {


    override fun createLoanSolicitation(createLoanCommand: CreateLoanCommand) {
        val solicitationId = UUID.randomUUID()

        logger.info { "Initializing loan solicitation $solicitationId" }

        createLoanCommand.interestRate = defineInterestRateBy(createLoanCommand.type)

        val loanDomain = loanDomainService.initializeLoanProcess(createLoanCommand.toLoan(solicitationId.toString()))

        loanRepositoryAdapter.createLoanSolicitation(loanDomain)
    }




    override fun cancellLoanSolicitation(cancellLoanCommand: CancellLoanCommand) {
        logger.info { "Cancelling loan solicitation ${cancellLoanCommand.solicitationId}" }
        loanRepositoryAdapter.cancellLoanSolicitation(cancellLoanCommand.solicitationId)
    }

    private fun defineInterestRateBy(type: LoanType): Double =
        when(type){
            LoanType.PERSONAL_LOAN -> 1.1061
            LoanType.CREDIT_BUILDER_LOAN -> 1.0780
            LoanType.HOME_EQUITY_LOAN -> 1.1204
            LoanType.MORTGAGE_LOAN -> 1.0586
            LoanType.AUTO_LOAN -> 1.1749
            LoanType.PAYDAY_LOAN -> 1.0461
            LoanType.STUDENT_LOAN -> 1.10
            LoanType.DEBIT_CONSOLIDATION_LOAN -> 1.1142
            else ->  1.1375
        }


    companion object{
        val logger = KotlinLogging.logger {  }
    }

}