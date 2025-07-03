package com.assesment.spring.data.mongodb.dto;

import com.assesment.spring.data.mongodb.model.AccountEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class CustomerDto {

    private String firstName;

    private String lastName;

    private AccountEntity accountId;
}
