package com.assesment.spring.data.mongodb.serviceimpl;

import com.assesment.spring.data.mongodb.model.CustomerEntity;
import com.assesment.spring.data.mongodb.repository.AccountRepository;
import com.assesment.spring.data.mongodb.repository.CustomerRepository;
import com.assesment.spring.data.mongodb.service.CustomerMigrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerMigrationServiceImpl implements CustomerMigrationService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerMigrationServiceImpl.class);

    private static final int PAGE_SIZE = 100;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    public int deleteCustomersWithInvalidAccounts() {
        int totalDeleted = 0;
        int pageNumber = 0;

        try {
            while (true) {
                PageRequest pageable = PageRequest.of(pageNumber, PAGE_SIZE);
                Page<CustomerEntity> customersPage = customerRepository.findAll(pageable);

                if (customersPage.isEmpty()) {
                    break;
                }

                List<CustomerEntity> customers = customersPage.getContent();

                for (CustomerEntity customer : customers) {
                    if (customer.getAccountId() == null ||
                            !accountRepository.existsById(customer.getAccountId().getId())) {

                        logger.info("Deleting customer: {} {} (ID: {})",
                                customer.getFirstName(),
                                customer.getLastName(),
                                customer.getId());

                        customerRepository.delete(customer);
                        totalDeleted++;
                    }
                }

                pageNumber++;
            }

            logger.info("Total customers deleted: {}", totalDeleted);
        } catch (Exception e) {
            logger.error("Error occurred while deleting customers with invalid accounts", e);
        }

        return totalDeleted;
    }
}
