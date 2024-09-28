package com.trainingdev.td_bs_management_user.entities;

import com.trainingdev.td_bs_management_user.enums.FeelingsEnum;
import com.trainingdev.td_bs_management_user.enums.PostTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "post")
public class PostEntity extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 7, nullable = false)
    private PostTypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(name = "feeling")
    private FeelingsEnum feeling;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private  List<CommentEntity> comments;

}
