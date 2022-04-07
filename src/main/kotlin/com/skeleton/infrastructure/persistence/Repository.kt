package com.skeleton.infrastructure.persistence

import com.skeleton.domain.persistence.entities.UserEntity
import com.skeleton.application.persistence.repositories.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class Repository @Autowired constructor(
    private val usersRepository: UsersRepository
) {

    // -----------------------------------------------------------------------------------
    // PERSIST: The following methods are for persisting data
    // NOTE: This is something I do personally. Since we have method overloading
    //       (OOP: Polymorphism), I make use of that and leave it to the JVM to select the
    //       correct method when persisting entries to the Database.
    // -----------------------------------------------------------------------------------

    fun persist(userEntity: UserEntity): UserEntity {
        return usersRepository.save(userEntity)
    }

    // -----------------------------------------------------------------------------------
    // EXTRACT: The following methods are for extracting data
    // NOTE: This is something I also do personally when ever I extract (find) elements in
    //       the database I make use of a noun at the beginning of the method name.
    //       In this case I used find, so all the methods listed under extract should have
    //       the noun "find" as a prefect to the name.
    //
    //       Examples: findUser, findUserEmail, findUserPhone
    //
    // -----------------------------------------------------------------------------------

    fun findUserByEmail(email: String): Optional<UserEntity> {
        return usersRepository.findByEmail(email)
    }

    fun findUserByPhone(phone: String): Optional<UserEntity> {
        return usersRepository.findByPhone(phone)
    }

    fun findByPhoneAndEmail(phone: String, email: String): Optional<UserEntity> {
        return usersRepository.findByPhoneOrEmail(
            phone = phone,
            email = email
        )
    }

    fun findUserById(userId: Long): Optional<UserEntity> {
        return usersRepository.findById(userId)
    }
}
