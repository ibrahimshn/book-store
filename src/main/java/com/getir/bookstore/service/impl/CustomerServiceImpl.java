package com.getir.bookstore.service.impl;

import com.getir.bookstore.entity.Customer;
import com.getir.bookstore.exception.customer.CustomerAlreadyExistException;
import com.getir.bookstore.exception.customer.CustomerDoesNotExistException;
import com.getir.bookstore.repository.CustomerRepository;
import com.getir.bookstore.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer saveCustomer(Customer customer) {
        boolean isCustomerExist = customerRepository.existsByUsernameOrEmail(customer.getUsername(), customer.getEmail());
        if(isCustomerExist) {
            throw new CustomerAlreadyExistException("This username or email address is not available");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(int customerId) {
        Supplier<CustomerDoesNotExistException> s =
                () -> new CustomerDoesNotExistException(customerId + " customerId: not found");
        return customerRepository.findCustomerById(customerId).orElseThrow(s);
    }
}
