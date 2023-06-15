package com.dimas.crud.services;


import com.dimas.crud.dto.BaseResponseDTO;
import com.dimas.crud.entity.User;
import com.dimas.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<BaseResponseDTO> getDataUser(String userId) {

        ResponseEntity<BaseResponseDTO> baseResponse;

        if(userId.equalsIgnoreCase("all")){

            List<User> users = userRepository.findAll();
            baseResponse = ResponseEntity.ok(
                    BaseResponseDTO.builder()
                            .resMessage("Success get all users")
                            .data(users)
                            .build()
            );

        } else {

            Optional<User> user = userRepository.findById(Integer.valueOf(userId));
            if(user.isPresent()){
                baseResponse = ResponseEntity.ok(
                        BaseResponseDTO.builder()
                                .resMessage("Success get data user")
                                .data(user)
                                .build()
                );
            } else {
                baseResponse = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(
                                BaseResponseDTO.builder()
                                        .resMessage("User not found")
                                        .data(List.of())
                                        .build()
                        );
            }

        }

        return baseResponse;
    }

    @Override
    public ResponseEntity<BaseResponseDTO> setDataUser(User user) {

        String hashPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);

        User saveUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        BaseResponseDTO.builder()
                                .resMessage("Success save user")
                                .data(saveUser)
                                .build()
                );
    }

    @Override
    public ResponseEntity<BaseResponseDTO> delDataUser(Integer userId) {

        ResponseEntity<BaseResponseDTO> baseResponse;

        userRepository.deleteById(userId);
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            baseResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(
                            BaseResponseDTO.builder()
                                    .resMessage("Failed delete user")
                                    .data(List.of())
                                    .build()
                    );
        } else {
            baseResponse = ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(
                            BaseResponseDTO.builder()
                                    .resMessage("Success deleted user")
                                    .data(List.of())
                                    .build()
                    );
        }

        return baseResponse;
    }
}
