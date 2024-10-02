package com.trainingdev.td_bs_management_post.entities;

import com.trainingdev.td_bs_management_post.enums.RelationshipStateEnum;
import com.trainingdev.td_bs_management_post.enums.RelationshipTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "relationship")
public class RelationshipEntity extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_user", nullable = false)
    private UserEntity senderUser;

    @ManyToOne
    @JoinColumn(name = "receptor_user", nullable = false)
    private UserEntity receptorUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 10)
    private RelationshipTypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false, length = 10)
    private RelationshipStateEnum state;

}
