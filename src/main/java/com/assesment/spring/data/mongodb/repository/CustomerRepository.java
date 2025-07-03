package com.assesment.spring.data.mongodb.repository;

import com.assesment.spring.data.mongodb.model.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository  extends MongoRepository<CustomerEntity, String> {

    Page<CustomerEntity> findByAccountId_Id(String accountId, PageRequest pageable);
}
