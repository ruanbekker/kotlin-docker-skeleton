package com.skeleton.infrastructure.rest.api.user.v1

import com.skeleton.infrastructure.rest.api.user.UserAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/")
class UserController @Autowired constructor(
    private val userAdapter: UserAdapter
) {

    @PostMapping(path = ["create-user-entry"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createUserRequest(
        @RequestParam first_name: String,
        @RequestParam last_name: String,
        @RequestParam email: String,
        @RequestParam phone: String
    ): ResponseEntity<Any> {
        return processCreateUserRequest(
            firstName = first_name,
            lastName = last_name,
            email = email,
            phone = phone
        )
    }

    @GetMapping(path = ["find-user-entry"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserRequest(
        @RequestParam user_id: String
    ): ResponseEntity<Any> {
        return processGetUserRequest(user_id)
    }

    // ----------------
    // PRIVATE METHODS
    // ----------------

    private fun processCreateUserRequest(
        firstName: String,
        lastName: String,
        email: String,
        phone: String
    ): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(
                userAdapter.processCreateUserRequest(
                    UserAdapter.UserDetails(
                        firstName = firstName,
                        lastName = lastName,
                        email = email,
                        phone = phone
                    )
                )
            )
        } catch (exception: Exception) {
            ResponseEntity.badRequest().body(
                exception.message
            )
        }
    }

    private fun processGetUserRequest(userId: String): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(
                userAdapter.processGetUserRequest(userId)
            )
        } catch (exception: Exception) {
            ResponseEntity.badRequest().body(
                exception.message
            )
        }
    }
}
