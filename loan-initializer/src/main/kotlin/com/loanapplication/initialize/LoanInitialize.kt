package com.loanapplication.initialize

import com.loanapplication.datarepository.customer.entity.Contact
import com.loanapplication.datarepository.customer.entity.CustomerEntity
import com.loanapplication.datarepository.customer.repository.CustomerJpaRepository
import io.grpc.BindableService
import io.grpc.ServerBuilder
import jakarta.annotation.PostConstruct
import mu.KotlinLogging
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.time.LocalDate

@SpringBootApplication
@ComponentScan(basePackages = ["com.loanapplication.*"])
@EnableJpaRepositories(basePackages = ["com.loanapplication.datarepository.*"])
@EntityScan(basePackages = ["com.loanapplication.datarepository.*"])
class LoanInitialize(
    val services: List<BindableService>,
    val customerJpaRepository: CustomerJpaRepository
    ): ApplicationRunner{

    override fun run(args: ApplicationArguments?) {
        val serverBuilder = ServerBuilder.forPort(9090)

        services.forEach{
            serverBuilder.addService(it)
        }


        serverBuilder.build().start()
        logger.info { "Server GRPC Starts!" }
        serverBuilder.build().awaitTermination();

    }

    @PostConstruct
    fun loadCustomer() {
        val customerEntity = CustomerEntity(
            name = "Lucas Anderson Ryan Almada",
            cpf = "55978060924",
            bornDate = LocalDate.of(2000,1,24),
            contact = Contact(
                "lucas-almada78@senhorasdaarte.com.br",
                "(67)99363-3534",
                "(67)3654-7005"
            )
        )

        logger.info {  "Save Customer" }
        customerJpaRepository.save(customerEntity)
    }

    companion object{
        val logger = KotlinLogging.logger {  }
    }

}

fun main(args: Array<String>) {
    runApplication<LoanInitialize>(*args)
}






