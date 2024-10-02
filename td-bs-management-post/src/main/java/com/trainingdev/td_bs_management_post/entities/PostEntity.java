package com.trainingdev.td_bs_management_post.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "post")
public class PostEntity extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "type", nullable = false, length = 7)
    private String type;

    @Column(name = "feeling", length = 20)
    private String feeling;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

}
