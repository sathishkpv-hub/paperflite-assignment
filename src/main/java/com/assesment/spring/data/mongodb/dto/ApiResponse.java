package com.assesment.spring.data.mongodb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse {

    private  boolean status;

    private String message;

    private Object data;

}
