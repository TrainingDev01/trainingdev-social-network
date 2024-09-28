package com.trainingdev.td_bs_management_user.dto.input;

import com.trainingdev.td_bs_management_user.enums.GenderEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {

    @NotNull(message = "Id cannot be null.")
    private Integer id;

    private String name;

    private LocalDate birthday;

    private String email;

    private String password;

    private GenderEnum gender;

    private String profilePhoto;

    private String coverPhoto;

    private LocalDateTime creationDate;

    private LocalDateTime modificationDate;
}
