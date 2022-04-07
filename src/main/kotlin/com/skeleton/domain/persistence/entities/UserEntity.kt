package com.skeleton.domain.persistence.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
    @Column(name = "first_name", updatable = true, nullable = false)
    private var firstName: String,

    @Column(name = "last_name", updatable = true, nullable = false)
    private var lastName: String,

    @Column(name = "email", updatable = true, nullable = false, unique = true)
    private var email: String,

    @Column(name = "phone", updatable = true, nullable = false)
    private var phone: String
) {

    private constructor() : this("","", "", "")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    private val id: Long = 0

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME")
    private val updatedAt: ZonedDateTime = getCurrentDateTime()

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    private val createdAt: ZonedDateTime = getCurrentDateTime()

    private fun getCurrentDateTime(): ZonedDateTime {
        return ZonedDateTime.now()
    }

    /**
     * ---------------------------------
     * GET & SET FirstName properties
     * ---------------------------------
     */

    fun setFirstName(firstName: String) {
        this.firstName = firstName
    }

    fun getFirstName(): String {
        return firstName
    }

    /**
     * ---------------------------------
     * GET & SET FirstName properties
     * ---------------------------------
     */

    fun setLastName(lastName: String) {
        this.lastName = lastName
    }

    fun getLastName(): String {
        return lastName
    }

    /**
     * ---------------------------------
     * GET & SET Email properties
     * ---------------------------------
     */

    fun setEmail(email: String) {
        this.email = email
    }

    fun getEmail(): String {
        return email
    }

    /**
     * ---------------------------------
     * GET & SET Phone properties
     * ---------------------------------
     */

    fun setPhone(phone: String) {
        this.phone = phone
    }

    fun getPhone(): String {
        return phone
    }

    fun getId(): Long {
        return this.id
    }
}
