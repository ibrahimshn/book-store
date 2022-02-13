package com.getir.bookstore.service;

import com.getir.bookstore.entity.Customer;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Customer findCustomerById(int customerId);
}
