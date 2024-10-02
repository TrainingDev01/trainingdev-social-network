package com.trainingdev.td_bs_management_post.repository;

import com.trainingdev.td_bs_management_post.entities.RelationshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationshipRepository extends JpaRepository<RelationshipEntity, Long> {

    @Query("SELECT CASE " +
            "WHEN r.senderUser.id = :userId THEN r.receptorUser.id " +
            "WHEN r.receptorUser.id = :userId THEN r.senderUser.id " +
            "END " +
            "FROM RelationshipEntity r " +
            "WHERE (r.senderUser.id = :userId OR r.receptorUser.id = :userId) " +
            "AND r.state = 'ACCEPTED'")
    List<Integer> findFriendIdsByUserId(@Param("userId") Integer userId);
}
