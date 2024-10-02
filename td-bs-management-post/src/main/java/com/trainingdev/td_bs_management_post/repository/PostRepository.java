package com.trainingdev.td_bs_management_post.repository;

import com.trainingdev.td_bs_management_post.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    List<PostEntity> findAllByUserId(Integer userId);

    @Query("SELECT p FROM PostEntity p WHERE p.user.id IN :userIds")
    List<PostEntity> findAllByUserIds(@Param("userIds") List<Integer> userIds);

}
