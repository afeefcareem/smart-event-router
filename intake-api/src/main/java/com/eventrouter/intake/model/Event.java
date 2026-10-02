package com.eventrouter.intake.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "events")
public class Event {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String source;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "raw_payload", nullable = false, columnDefinition = "jsonb")
    private String rawPayload;

    @Column(nullable = false)
    private String status;

    @Column(name = "received_at", nullable = false)
    private OffsetDateTime receivedAt;

    protected Event() {
    }

    public Event(UUID id, String source, String rawPayload, String status, OffsetDateTime receivedAt) {
        this.id = id;
        this.source = source;
        this.rawPayload = rawPayload;
        this.status = status;
        this.receivedAt = receivedAt;
    }

    public UUID getId() { return id; }
    public String getSource() { return source; }
    public String getRawPayload() { return rawPayload; }
    public String getStatus() { return status; }
    public OffsetDateTime getReceivedAt() { return receivedAt; }
}