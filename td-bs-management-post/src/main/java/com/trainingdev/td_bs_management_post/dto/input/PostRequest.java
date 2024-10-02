package com.trainingdev.td_bs_management_post.dto.input;

import com.trainingdev.td_bs_management_post.enums.PostFeeling;
import com.trainingdev.td_bs_management_post.enums.PostType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    private Integer id;

    @NotNull(message = "User ID cannot be null.")
    private Integer userId;

    @NotNull(message = "Post type cannot be null.")
    private PostType type;

    private PostFeeling feeling;

    @NotEmpty(message = "Post description cannot be empty.")
    private String description;

    private String image;
}
