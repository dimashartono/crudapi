package com.dimas.crud.services;

import com.dimas.crud.dto.BaseResponseDTO;
import com.dimas.crud.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<BaseResponseDTO> getDataUser(String userId);
    ResponseEntity<BaseResponseDTO> setDataUser(User user);
    ResponseEntity<BaseResponseDTO> delDataUser(Integer userId);
}
