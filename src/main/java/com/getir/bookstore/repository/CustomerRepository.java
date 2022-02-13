package com.getir.bookstore.repository;

import com.getir.bookstore.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
    boolean existsByUsernameOrEmail(String userName, String email);
    Optional<Customer> findCustomerById(int id);
}
