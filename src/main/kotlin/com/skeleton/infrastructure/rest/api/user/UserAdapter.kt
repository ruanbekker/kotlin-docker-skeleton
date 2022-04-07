package com.skeleton.infrastructure.rest.api.user

import com.skeleton.domain.persistence.entities.UserEntity
import com.skeleton.infrastructure.persistence.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserAdapter @Autowired constructor(
    private val repository: Repository
) {

    fun processCreateUserRequest(userDetails: UserDetails): Long {
        val optionalUserEntity = repository.findByPhoneAndEmail(
            phone = userDetails.phone,
            email = userDetails.email
        )

        return if (optionalUserEntity.isEmpty) {
            processCreateUserRequestImpl(userDetails).getId()
        } else {
            throw Exception("Duplicate Phone || Email")
        }
    }

    fun processGetUserRequest(userId: String): UserEntity {
        return processGetUserRequestImpl(
            userId.toLong()
        )
    }

    // ----------------
    // PRIVATE METHODS
    // ----------------

    private fun processCreateUserRequestImpl(userDetails: UserDetails): UserEntity {
        return repository.persist(
            UserEntity(
                firstName = userDetails.firstName,
                lastName = userDetails.lastName,
                email = userDetails.email,
                phone = userDetails.phone
            )
        )
    }

    private fun processGetUserRequestImpl(userId: Long): UserEntity {
        val optionalUserEntity = repository.findUserById(userId)
        if (optionalUserEntity.isPresent) {
            return optionalUserEntity.get()
        }

        throw Exception("User D.N.E")
    }

    data class UserDetails(
        val firstName: String,
        val lastName: String,
        val email: String,
        val phone: String,
    )
}