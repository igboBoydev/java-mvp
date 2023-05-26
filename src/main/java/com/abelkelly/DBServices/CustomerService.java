package com.abelkelly.DBServices;

import com.abelkelly.Models.Customer;
import com.abelkelly.Repository.CustomerRepository;
import com.abelkelly.RequestSchema.NewCustomerRequest;
import com.abelkelly.RequestSchema.UpdateCustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public String addCustomer(NewCustomerRequest request){
        Customer customer = new Customer(request.getName(), request.getEmail(), request.getAge());
        customerRepository.save(customer);
        return "done";
    }

    public String updateCustomer(UpdateCustomerRequest request){
       customerRepository.findById(request.getId()).ifPresent(user -> {
           user.setEmail(request.getEmail());
           customerRepository.save(user);
       } );
       return "user updated successfully";
    }

}
