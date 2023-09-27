package com.codeonmars.issuesms.service;

import com.codeonmars.commonsms.security.UserContextHolder;
import com.codeonmars.issuesms.model.issues.CreatorData;
import com.codeonmars.issuesms.model.issues.IssuesEntity;
import com.codeonmars.issuesms.model.issues.PropertyData;
import com.codeonmars.issuesms.model.issues.dto.IssueRequest;
import com.codeonmars.issuesms.model.issues.dto.NotificationRecord;
import com.codeonmars.issuesms.model.issues.enums.IssueStatus;
import com.codeonmars.issuesms.remote.PropertiesApi;
import com.codeonmars.issuesms.remote.PropertiesApi.SimplePropertyData;
import com.codeonmars.issuesms.repository.IssuesRepository;
import com.github.dozermapper.core.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@Transactional
public class IssueUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IssueUserService.class);
    private static final String TOPIC = "notifications";

    private final IssuesRepository issuesRepository;
    private final PropertiesApi propertiesApi;
    private final Mapper dozer;
    private final  KafkaTemplate<String, NotificationRecord> kafkaTemplate;

    public IssueUserService(IssuesRepository issuesRepository, PropertiesApi propertiesApi, Mapper dozer, KafkaTemplate<String, NotificationRecord> kafkaTemplate) {
        this.issuesRepository = issuesRepository;
        this.propertiesApi = propertiesApi;
        this.dozer = dozer;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createNewIssue(IssueRequest request) {
            var property = propertiesApi.getPropertyOwnerTenant(request.getPropertyID(), getLoggedUserUsername());
            if (property.isPresent()) {
                var propertyEntity = property.get();
                var issue = issuesRepository.save(generateIssue(request, propertyEntity));
                this.sendUserMessage(generateIssueNotification(issue, propertyEntity.getOwnerUsername()));
            }
    }

    private void sendUserMessage(NotificationRecord notification) {
        Message<NotificationRecord> message = MessageBuilder
                .withPayload(notification)
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();
        this.kafkaTemplate.send(message);
    }

    /* SUPPORTING METHODS */

    private IssuesEntity generateIssue(IssueRequest request, SimplePropertyData simplePropertyData) {
        var issue = dozer.map(request, IssuesEntity.class);
        issue.setStatus(IssueStatus.OPEN);
        issue.setCreator(new CreatorData(getLoggedUserUsername(), getLoggedUserEmail()));
        issue.setProperty(new PropertyData(request.getPropertyID(), simplePropertyData.getDescription()));
        issue.setCreationDate(Instant.now());
        return issue;
    }

    private NotificationRecord generateIssueNotification(IssuesEntity issue, String owner) {
        return new NotificationRecord("Issue created", owner, composeMessage(issue), issue.getProperty().getOriginId(), issue.getCreationDate());
    }

    private String composeMessage(IssuesEntity issue) {
        return String.format("Issue with description:'%s' created from tenant '%s' for property '%s'",
                issue.getDescription(),
                issue.getCreator().getUsername(),
                Optional.ofNullable(issue.getProperty().getDescription()).orElse(""));
    }

    private String getLoggedUserUsername() {
        /* TODO: fix the throw to a caught exception*/
        var user = UserContextHolder.getContext().orElseThrow();
        return user.getUsername();
    }

    private String getLoggedUserEmail() {
        /* TODO: fix the throw to a caught exception*/
        var user = UserContextHolder.getContext().orElseThrow();
        return user.getEmail();
    }
}
