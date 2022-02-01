package com.services.user.management.service;

import com.services.user.management.repository.SubscriptionRepository;
import com.services.user.management.model.Subscription;
import com.services.user.management.repository.SubscriptionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SocialMediaSubscriptionService {

    @Autowired
    SubscriptionRepository repository;

    public void saveAll(final Subscription subscription) {
        List<SubscriptionEntity> entities = toEntity(subscription);
        repository.saveAll(entities);
    }

    public Subscription getByUserId(int userId) {

        List<SubscriptionEntity> subscriptionEntities = repository.findByUserId(userId);

        return toModel(subscriptionEntities);
    }

    private List<SubscriptionEntity> toEntity(Subscription subscription){
        List<SubscriptionEntity> subscriptionEntities = new ArrayList<>();
        subscription.getSocialMedia().stream().forEach(socialMedia -> {
            subscriptionEntities.add(new SubscriptionEntity(subscription.getUserId(),
                    socialMedia));
        });

        return subscriptionEntities;
    }

    private Subscription toModel(List<SubscriptionEntity> subscriptionEntities){
        List<String> socialMediaList = new ArrayList<>();
        subscriptionEntities.stream().forEach(subscriptionEntity -> {
            socialMediaList.add(subscriptionEntity.getSocialMedia());
        });

        return new Subscription(subscriptionEntities.get(0).getUserId(), socialMediaList);
    }

}
