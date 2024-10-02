package com.trainingdev.td_bs_management_user.dto.input;

import com.trainingdev.td_bs_management_user.enums.GenderEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModified {

    @NotNull(message = "Id cannot be null.")
    private Integer id;

    @NotEmpty(message = "Name cannot be null.")
    private String name;

    @NotNull(message = "Birthday cannot be null.")
    private LocalDate birthday;

    @NotNull(message = "Email cannot be null.")
    @Email(message = "Email should be valid.")
    private String email;

    @NotEmpty(message = "Password cannot be null.")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "Password does not meet the requirements."
    )
    private String password;

    @NotNull(message = "Gender cannot be null.")
    private GenderEnum gender;

    @NotEmpty(message = "ProfilePhoto cannot be null.")
    private String profilePhoto;

    @NotEmpty(message = "CoverPhoto cannot be null.")
    private String coverPhoto;
}
