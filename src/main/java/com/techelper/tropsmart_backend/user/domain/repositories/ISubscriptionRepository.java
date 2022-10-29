package com.techelper.tropsmart_backend.user.domain.repositories;

import com.techelper.tropsmart_backend.user.domain.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISubscriptionRepository extends JpaRepository<Subscription, Integer> {
    @Query("select s from Subscription s where s.user.id = (:uid)")
    List<Subscription> getSubscriptionsByUserId(@Param("uid") Integer userId);


}

