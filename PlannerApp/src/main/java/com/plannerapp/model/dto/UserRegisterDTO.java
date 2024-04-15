package com.plannerapp.model.dto;


import com.plannerapp.vallidation.availableEmailCheck.AvailableEmailCheck;
import com.plannerapp.vallidation.availableUsernameCheck.AvailableUsernameCheck;
import com.plannerapp.vallidation.confirmPasswordMatcher.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@PasswordMatch
public class UserRegisterDTO {

    @Size (min = 3, max = 10, message = "Username length must be between 3 and 10 characters!")
    @NotNull
    @AvailableUsernameCheck
    private String username;

    @Email
    @NotBlank (message = "Email cannot be empty!")
    @AvailableEmailCheck
    private String email;

    @Size (min = 3, message = "Password length must be more than 3 characters!")
    @NotNull
    private String password;

    @NotNull
    @NotBlank (message = "Confirm Password cannot be empty!")
    private String confirmPassword;
}
