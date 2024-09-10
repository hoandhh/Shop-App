package com.hoandhh.backend.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.hoandhh.backend.dtos.UserDTO;
import com.hoandhh.backend.models.Role;
import com.hoandhh.backend.models.User;
import com.hoandhh.backend.repositories.RoleRepository;
import com.hoandhh.backend.repositories.UserRepository;
import com.hoandhh.backend.exceptions.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User createUser(UserDTO userDTO) {
        String phoneNumber = userDTO.getPhoneNumber();
        // Kiểm tra xem số điện thoại đã tồn tại hay chưa
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("Phone number already exists");
        }
        // Convert from userDTO => user
        User newUser = User.builder()
                .fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(userDTO.getPassword())
                .address(userDTO.getAddress())
                .dateOfBirth(userDTO.getDateOfBirth())
                .facebookAccountId(userDTO.getFacebookAccountId())
                .googleAccountId(userDTO.getGoogleAccountId())
                .build();
        try {
            Role role = roleRepository.findById(userDTO.getRoleId())
                    .orElseThrow(() -> new DataNotFoundException("Role not found"));
            newUser.setRole(role);
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Kiểm tra nếu có accountId, không yêu cầu password
        if (userDTO.getFacebookAccountId() == 0 && userDTO.getGoogleAccountId() == 0) {
            String password = userDTO.getPassword();
            // String encodedPassword = passwordEncoder.encode(password);
            // newUser.setPassword(encodedPassword);
        }
        return userRepository.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password) {
        // Spring sercurity
        return null;
    }

}
