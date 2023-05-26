package com.abelkelly.ControllersRoutes;

import com.abelkelly.DBServices.CustomerService;
import com.abelkelly.Models.Customer;
import com.abelkelly.Repository.CustomerRepository;
import com.abelkelly.RequestSchema.NewCustomerRequest;
import com.abelkelly.RequestSchema.UpdateCustomerRequest;
import com.abelkelly.ResponseSchema.ResponseMessage;
import io.micrometer.common.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customers")
public class CustomersController {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    public CustomersController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    @PostMapping("/add_customer")
    public ResponseMessage addCustomer(@RequestBody NewCustomerRequest request){
        List<Customer> customer = customerRepository.findByEmail(request.getEmail());
        if(!customer.isEmpty()){
            return new ResponseMessage(404, "Customer with email already exists");
        }
        customerService.addCustomer(request);
        return new ResponseMessage(200, "Customer added successfully");
    }

    @DeleteMapping("/delete_customer")
    public ResponseMessage deleteCustomer(@RequestParam("customerId") String id){
        System.out.println("id = " + id);
        if(StringUtils.isBlank(id) || StringUtils.isEmpty(id)){
            return new ResponseMessage(400, "Invalid request");
        }
        Optional<Customer> customer = customerRepository.findById(Integer.valueOf(id));
        if(!customer.isPresent()){
            return new ResponseMessage(404, "Customer not found");
        }
        customerRepository.deleteById(Integer.valueOf(id));
        return new ResponseMessage(200, "Customer deleted successfully");
    }

    @PutMapping("/update_customer_email")
    public String updateCustomerEmail(@RequestBody UpdateCustomerRequest request){
        return customerService.updateCustomer(request);
    }

}
