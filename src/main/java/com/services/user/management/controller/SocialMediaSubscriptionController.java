package com.services.user.management.controller;

import com.services.user.management.model.Subscription;
import com.services.user.management.service.SocialMediaSubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Slf4j
public class SocialMediaSubscriptionController {

    private static final String SUCCESS_CREATE_MESSAGE = "{\"message\" : " +
            "\"Successfully added subscriptions\"}";

    @Autowired
    private SocialMediaSubscriptionService socialMediaSubscriptionService;

    @RequestMapping(
            value = {"/subscriptions/{userId}" },
            method = GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Subscription getUserById(final @PathVariable int userId) {
        log.info("get subscription by user id");
        return socialMediaSubscriptionService.getByUserId(userId);
    }

    @RequestMapping(
            value = {"/subscriptions" },
            method = POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(final @Valid @RequestBody Subscription subscription) {
        log.info("add new subscriptions request {}", subscription);
        socialMediaSubscriptionService.saveAll(subscription);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(String.format(SUCCESS_CREATE_MESSAGE));
    }
}
