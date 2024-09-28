package com.trainingdev.td_bs_management_user.dto.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetail {
    private Integer id;
    private Integer userId;
    private String type;
    private String feeling;
    private String description;
    private String image;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private List<Comment> commentList;
}
