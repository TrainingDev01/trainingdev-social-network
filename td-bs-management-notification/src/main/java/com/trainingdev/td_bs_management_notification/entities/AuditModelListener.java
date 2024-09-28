package com.trainingdev.td_bs_management_notification.entities;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class AuditModelListener {
    @PrePersist
    public void prePersist(AuditModel audit) {
        LocalDateTime now = LocalDateTime.now();
        audit.setCreationDate(now);
        audit.setModificationDate(now);
    }

    @PreUpdate
    public void preUpdate(AuditModel audit) {
        audit.setModificationDate(LocalDateTime.now());
    }
}
