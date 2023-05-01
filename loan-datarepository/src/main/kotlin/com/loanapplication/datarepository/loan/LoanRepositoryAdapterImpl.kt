package com.loanapplication.datarepository.loan

import com.loanapplication.datarepository.customer.repository.CustomerJpaRepository
import com.loanapplication.datarepository.loan.repository.LoanJpaRepository
import com.loanapplication.datarepository.mapper.toEntity
import com.loanapplication.doaminservice.entity.Loan
import com.loanapplication.doaminservice.entity.LoanStatus
import com.loanapplication.usecase.ports.output.repository.LoanRepositoryAdapter
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException
import java.time.ZoneId
import java.time.ZonedDateTime

@Component
class LoanRepositoryAdapterImpl(
    private val customerJpaRepository: CustomerJpaRepository,
    private val loanJpaRepository: LoanJpaRepository
) : LoanRepositoryAdapter {

    @Transactional
    override fun createLoanSolicitation(loan: Loan) {
        logger.info { "Initializing Insert Loan" }

        try {
            val customerSaved = customerJpaRepository
                .findByCpf(loan.cpf)

            if(customerSaved != null){
                val loanPending = customerSaved.loans?.
                    any { it.loanStatus == LoanStatus.PENDING }


                if(loanPending == true){
                    logger.info { "There is a solicitation pending" }
                }

                loanJpaRepository.save(loan.toEntity(customerSaved))
            }

            logger.info { "Success to insert solicitation ${loan.solicitationId} " }
        }catch (e: SQLException){
            logger.error { "Error with SQL to save solicitation ${loan.solicitationId}" }
            throw e
        }catch (e: Exception){
            logger.error { "Unknown error to save solicitation ${loan.solicitationId}" }
            throw e
        }

    }

    override fun cancellLoanSolicitation(solicitationId: String) {
        try {
            val loan = loanJpaRepository.findBySolicitationId(solicitationId)

            if(loan != null){
                when(loan.loanStatus){
                    LoanStatus.APPROVED -> throw RuntimeException("Solicitation $solicitationId Approved you cannot cancel")
                    LoanStatus.CANCELLED -> throw RuntimeException("Solicitation $solicitationId already Cancelled")
                    else -> {
                        loan.loanStatus = LoanStatus.CANCELLED
                        loan.cancelledAt = ZonedDateTime.now(ZoneId.of("UTC"))
                        loanJpaRepository.save(loan)
                    }
                }
                logger.info { "Solicitation $solicitationId was Cancelled" }
            }
        }catch (e: SQLException){
            logger.error { "Error with SQL to cancel solicitation $solicitationId" }
            throw e
        }catch (e: Exception){
            logger.error { "Unknown error to cancel solicitation $solicitationId" }
            throw e
        }
    }

    companion object{
        val logger = KotlinLogging.logger {  }
    }


}