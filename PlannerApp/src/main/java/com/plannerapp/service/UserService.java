package com.plannerapp.service;

import com.plannerapp.model.LoggedUser;
import com.plannerapp.model.dto.UserLoginDTO;
import com.plannerapp.model.dto.UserRegisterDTO;
import com.plannerapp.model.entity.User;
import com.plannerapp.model.models.UserModel;
import com.plannerapp.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public void registerUser (UserRegisterDTO userRegisterDTO){
        userRepository.save(modelMapper.map(userRegisterDTO, User.class));
    }

    public void loginUser (UserLoginDTO userLoginDTO){

        User user = userRepository.findByUsername(userLoginDTO.getUsername()).orElseThrow();

        loggedUser.setId(user.getId());
        loggedUser.setUsername(user.getUsername());

    }

    public void logoutUser (){
        this.loggedUser.setId(null);
    }


    public UserModel UserModelById (Long id){
        return modelMapper.map(userRepository.findById(id), UserModel.class);
    }
}

