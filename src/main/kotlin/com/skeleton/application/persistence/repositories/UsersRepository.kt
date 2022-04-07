package com.skeleton.application.persistence.repositories

import com.skeleton.domain.persistence.entities.UserEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsersRepository : CrudRepository<UserEntity, Long> {

    @Query("FROM UserEntity user WHERE email = :email")
    fun findByEmail(
        @Param("email") email: String
    ): Optional<UserEntity>

    @Query("FROM UserEntity user WHERE phone = :phone")
    fun findByPhone(
        @Param("phone") phone: String
    ): Optional<UserEntity>

    @Query("FROM UserEntity user WHERE phone = :phone OR email =:email")
    fun findByPhoneOrEmail(
        @Param("phone") phone: String,
        @Param("email") email: String
    ): Optional<UserEntity>
}
