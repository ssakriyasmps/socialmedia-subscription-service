package com.services.user.management.repository;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Subscription")
@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
public class SubscriptionEntity {

    // @Id annotation specifies the primary key of an entity.
    // @GeneratedValue provides the generation strategy
    // specification for the primary key values.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String socialMedia;


    public SubscriptionEntity(int userId, String socialMedia){
        this.userId = userId;
        this.socialMedia = socialMedia;
    }
}
