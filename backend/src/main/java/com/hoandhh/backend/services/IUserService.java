package com.hoandhh.backend.services;

import com.hoandhh.backend.dtos.UserDTO;
import com.hoandhh.backend.models.User;

public interface IUserService {
    User createUser(UserDTO userDTO);

    String login(String phoneNumber, String password);
}
