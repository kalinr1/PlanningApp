package com.exam1.service;

import com.exam1.model.LoggedUser;
import com.exam1.model.dto.UserLoginDTO;
import com.exam1.model.dto.UserRegisterDTO;
import com.exam1.model.entity.User;
import com.exam1.model.models.UserModel;
import com.exam1.repo.UserRepository;
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
        this.loggedUser.setId(null)
                .setUsername(null);
    }


    public UserModel findUserByIdModel (Long id){
        return modelMapper.map(userRepository.findById(id), UserModel.class);
    }
}

