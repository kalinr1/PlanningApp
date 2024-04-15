package com.plannerapp.vallidation.validateUserLoginDTO;

import com.plannerapp.model.dto.UserLoginDTO;
import com.plannerapp.repo.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserLoginValidator implements ConstraintValidator<UserLoginValidate, UserLoginDTO> {

    private final UserRepository userRepository;

    public UserLoginValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UserLoginValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginDTO userLoginDTO, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword()).isPresent();
    }
}
