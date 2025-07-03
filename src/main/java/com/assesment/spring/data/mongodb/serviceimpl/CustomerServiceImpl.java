package com.assesment.spring.data.mongodb.serviceimpl;

import com.assesment.spring.data.mongodb.dto.ApiResponse;
import com.assesment.spring.data.mongodb.dto.CustomerDto;
import com.assesment.spring.data.mongodb.model.CustomerEntity;
import com.assesment.spring.data.mongodb.repository.AccountRepository;
import com.assesment.spring.data.mongodb.repository.CustomerRepository;
import com.assesment.spring.data.mongodb.service.CustomerService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);


    private final CustomerRepository customerRepository;

    private final AccountRepository accountRepository;


    public ResponseEntity<ApiResponse> addCustomer(CustomerDto customerDto) {
        try {
            CustomerEntity customer = new CustomerEntity();
            BeanUtils.copyProperties(customerDto, customer);

            customerRepository.save(customer);
            logger.info("Customer added successfully with ID: {}", customer.getId());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(true, "Customer Added Successfully", customer));
        } catch (Exception e) {
            logger.error("Error while adding customer: {}", e.getMessage(), e);
            ApiResponse errorResponse = new ApiResponse(false, "Failed to add customer: ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    public ResponseEntity<ApiResponse> updateCustomer(String customerId,
                                                      CustomerDto customerDto) {
        logger.info("Received request to update customer with ID: {}", customerId);
        try {
            CustomerEntity customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new IllegalArgumentException("customer not found"));
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setAccountId(customerDto.getAccountId());
            customerRepository.save(customer);
            logger.info("Customer updated successfully with ID: {}", customerId);
            ApiResponse response = new ApiResponse(true, "Customer updated successfully", customer);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.error("Error updating customer: {}", e.getMessage());
            ApiResponse response = new ApiResponse(false, "Error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

        public ResponseEntity<ApiResponse> getCustomersByAccountId(String accountId,
                                                                   int page,
                                                                   int limit) {

            try {
                logger.info("Fetching customers for accountId: {} with page: {} and limit: {}", accountId, page, limit);

                boolean exists = accountRepository.existsById(accountId);
                if (!exists) {
                    logger.warn("Account with ID {} not found", accountId);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ApiResponse(false, "Account not found",accountId));
                }

                PageRequest pageable = PageRequest.of(page, limit);
                Page<CustomerEntity> customersPage = customerRepository.findByAccountId_Id(accountId, pageable);

                logger.info("Found {} customers for accountId: {}", customersPage.getTotalElements(), accountId);
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,"List od Customers",customersPage));

            } catch (Exception e) {
                logger.error("Error while fetching customers: {}", e.getMessage(), e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse(false, "Error while fetching customers",e.getMessage()));
            }
        }

}
