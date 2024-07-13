package com.trainingdev.td_bs_management_user.repository;

import com.trainingdev.td_bs_management_user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
