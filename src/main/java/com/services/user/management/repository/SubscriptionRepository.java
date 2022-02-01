package com.services.user.management.repository;

import com.services.user.management.model.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, Integer> {
    List<SubscriptionEntity> findByUserId(int userId);
}

