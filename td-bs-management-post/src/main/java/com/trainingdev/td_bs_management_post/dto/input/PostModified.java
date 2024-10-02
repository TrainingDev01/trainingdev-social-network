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
public class PostModified {

    @NotNull(message = "Post id cannot be null.")
    private Integer id;

    @NotNull(message = "Post userId cannot be null.")
    private Integer userId;

    @NotNull(message = "Post type cannot be null.")
    private PostType type;

    @NotNull(message = "Post feeling cannot be null.")
    private PostFeeling feeling;

    @NotEmpty(message = "Post description cannot be null.")
    private String description;

    @NotEmpty(message = "Post image cannot be null.")
    private String image;
}
