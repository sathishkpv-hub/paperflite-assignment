package com.assesment.spring.data.mongodb.service;

import com.assesment.spring.data.mongodb.dto.ApiResponse;
import com.assesment.spring.data.mongodb.dto.CustomerDto;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    ResponseEntity<ApiResponse> addCustomer(CustomerDto customerDto);

    ResponseEntity<ApiResponse> updateCustomer(String customerId, CustomerDto customerDto);

    ResponseEntity<ApiResponse> getCustomersByAccountId(String accountId,
                                                        int page,
                                                        int limit);
}
