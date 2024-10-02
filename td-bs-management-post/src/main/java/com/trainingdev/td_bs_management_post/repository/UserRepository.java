package com.trainingdev.td_bs_management_post.repository;

import com.trainingdev.td_bs_management_post.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(Integer id);

    boolean existsByEmail(String email);
}
