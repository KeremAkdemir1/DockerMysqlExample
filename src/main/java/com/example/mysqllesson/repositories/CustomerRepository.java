package com.example.mysqllesson.repositories;

import com.example.mysqllesson.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
