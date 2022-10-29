package com.techelper.tropsmart_backend.user.domain.repositories;

import com.techelper.tropsmart_backend.user.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query("select s from User s where s.email =:uid")
    Optional<User> findPersonByEmail(@Param("uid") String userEmail);

    @Query("select s from User s where s.person.id = (:uid)")
    Optional<User> findUserByPersonId(@Param("uid")int personId);

    @Query("select s from User s where s.balance.id = (:uid)")
    Optional<User> findUserByBalanceId(@Param("uid")int balanceId);

    @Query("select s from User s where s.configuration.id = (:uid)")
    Optional<User> findUserByConfigurationId(@Param("uid")int configurationId);
}
