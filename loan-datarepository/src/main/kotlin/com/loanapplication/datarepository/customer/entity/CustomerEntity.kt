package com.loanapplication.datarepository.customer.entity

import com.loanapplication.datarepository.loan.entity.LoanEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "CUSTOMER")
class CustomerEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,
    val name: String,
    val cpf: String,
    val bornDate: LocalDate,
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    val loans: MutableList<LoanEntity>? = mutableListOf(),
    @Embedded
    val contact: Contact
){

}
@Embeddable
class Contact(
    val email: String?,
    val cellphone: String,
    val tel: String?
)


