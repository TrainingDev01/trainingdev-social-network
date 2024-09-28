package com.trainingdev.td_bs_management_user.dto.input;

import com.trainingdev.td_bs_management_user.enums.GenderEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Integer id;

    @NotEmpty(message = "First name cannot be empty")
    @Size(max = 20, message = "First name cannot be longer than 20 characters")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    @Size(max = 20, message = "Last name cannot be longer than 20 characters")
    private String lastName;

    @NotNull(message = "Birthday cannot be null")
    private LocalDate birthday;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "The email format is incorrect")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "Password does not meet the requirements."
    )
    private String password;

    @NotNull(message = "Gender cannot be null")
    private GenderEnum gender;

}
