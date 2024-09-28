package com.trainingdev.td_bs_management_user.entities;

import com.trainingdev.td_bs_management_user.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "userr")
public class UserEntity extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "email", length = 60, nullable = false)
    private String email;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 6, nullable = false)
    private GenderEnum gender;

    @Column(name = "profile_photo")
    private String profilePhoto;

    @Column(name = "cover_photo")
    private String coverPhoto;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostEntity> posts;

}
