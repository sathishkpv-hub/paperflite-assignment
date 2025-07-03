package com.assesment.spring.data.mongodb.controller;

import com.assesment.spring.data.mongodb.dto.ApiResponse;
import com.assesment.spring.data.mongodb.dto.CustomerDto;
import com.assesment.spring.data.mongodb.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity<ApiResponse> addCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.addCustomer(customerDto);
    }

    @PutMapping("/updateCustomer/{customerId}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable String customerId,
                                                      @RequestBody CustomerDto customerDto) {

        return customerService.updateCustomer(customerId,customerDto);
    }

    @GetMapping("/getCustomerByAccId/{accountId}")
    public ResponseEntity<ApiResponse> getCustomerByAccountId(@PathVariable String accountId,
                                                              @RequestParam(required = false,defaultValue = "0") int page,
                                                              @RequestParam(required = false,defaultValue = "5")int limit){
        return customerService.getCustomersByAccountId(accountId,page,limit);
    }

}
