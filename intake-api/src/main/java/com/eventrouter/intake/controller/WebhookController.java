package com.eventrouter.intake.controller;

import com.eventrouter.intake.dto.GithubIssuePayload;
import com.eventrouter.intake.model.Event;
import com.eventrouter.intake.repository.EventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/webhooks")
public class WebhookController {

    private final EventRepository eventRepository;
    private final ObjectMapper objectMapper;

    public WebhookController(EventRepository eventRepository, ObjectMapper objectMapper) {
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/github")
    public ResponseEntity<Map<String, String>> handleGithubWebhook(@RequestBody GithubIssuePayload payload)
            throws Exception {

        UUID eventId = UUID.randomUUID();
        String rawPayloadJson = objectMapper.writeValueAsString(payload);

        Event event = new Event(
                eventId,
                "github",
                rawPayloadJson,
                "received",
                OffsetDateTime.now()
        );

        eventRepository.save(event);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(Map.of("event_id", eventId.toString(), "status", "received"));
    }
}