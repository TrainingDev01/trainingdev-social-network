package com.trainingdev.td_bs_management_user.entities;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditModelListener.class)
public class AuditModel {

    @Column(name = "modification_date", nullable = false, updatable = false)
    private LocalDateTime modificationDate;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;
}
