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

    @NotEmpty(message = "firstName cannot be empty")
    @Size(max = 20, message = "firstName cannot be longer than 20 characters")
    private String firstName;

    @NotEmpty(message = "lastName cannot be empty")
    @Size(max = 20, message = "lastName cannot be longer than 20 characters")
    private String lastName;

    @NotNull(message = "birthday cannot be null")
    private LocalDate birthday;

    @NotEmpty(message = "email cannot be empty")
    @Email(message = "The email is wrong")
    private String email;

    @NotEmpty(message = "password cannot be empty")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "The password must have at least one uppercase letter, at least one lowercase letter, at least one number, at least one special character, minimum 8 characters."
    )
    private String password;

    @NotNull(message = "gender cannot be null")
    private GenderEnum gender;

}
