package com.example.mysqllesson.services;

import com.example.mysqllesson.Exceptions.ResourceNotFoundException;
import com.example.mysqllesson.entities.Customer;
import com.example.mysqllesson.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> customerDb = this.customerRepository.findById(customer.getId());

        if(customerDb.isPresent()){
            Customer customerupdate = customerDb.get();
            customerupdate.setId(customer.getId());
            customerupdate.setName(customer.getName());
            customerupdate.setLastName(customer.getLastName());
            customerRepository.save(customerupdate);
            return customerupdate;
        }
        else {
            throw new ResourceNotFoundException("Verilen Id Ile Ilgili Kayit Bulunamadi : " + customer.getId());
        }
    }

    @Override
    public List<Customer> getAllCustomer() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(long id) {

        Optional<Customer> customerDb = this.customerRepository.findById(id);
        if(customerDb.isPresent())
        {

            return customerDb.get();

        }else {
            throw new ResourceNotFoundException("Verilen Id Ile Ilgili Kayit Bulunamadi : " + id);
        }

    }

    @Override
    public void deleteCustomer(long id)
    {

        Optional<Customer> customerDb = this.customerRepository.findById(id);
        if(customerDb.isPresent())
        {
            this.customerRepository.delete(customerDb.get());
        }else {
            throw new ResourceNotFoundException("Verilen Id Ile Ilgili Kayit Bulunamadi : " + id);
        }

    }
}
