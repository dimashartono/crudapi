package com.dimas.crud.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BaseResponseDTO {

    private String resMessage;
    private Object data;

}
