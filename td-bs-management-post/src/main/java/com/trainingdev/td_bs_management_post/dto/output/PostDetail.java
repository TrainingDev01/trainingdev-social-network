package com.trainingdev.td_bs_management_post.dto.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingdev.td_bs_management_post.dto.business.User;
import com.trainingdev.td_bs_management_post.enums.PostFeeling;
import com.trainingdev.td_bs_management_post.enums.PostType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDetail {

    @NotNull(message = "Post id cannot be null.")
    private Integer id;

    private User user;

    private PostType type;

    private PostFeeling feeling;

    private String description;

    private String image;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate modificationDate;
}
