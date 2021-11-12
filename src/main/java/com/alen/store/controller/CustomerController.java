package com.alen.store.controller;

import com.alen.store.dao.CustomerDao;
import com.alen.store.handler.ResourceNotFoundException;
import com.alen.store.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v2")
public class CustomerController {

    @Autowired
    CustomerDao customerDao;

    @GetMapping("/customers")
    public List<Customer> getCustomerLists() {
        return customerDao.findAll();
    }

    /**
     * This response entity represents the whole HTTP status including header, body and status.
     * Using this if you want to configure your HTTP status by your own code logic.
     * https://zetcode.com/springboot/responseentity/
     */
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable(value = "id") UUID customerId)
            throws ResourceNotFoundException {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + customerId + " not found"));

        return ResponseEntity.ok(customer);
    }


    /* If you want to print the result in postman,
     *  method needs to return the object or print the result
     *  in cli psql by "select" command to make sure the data has inserted (void).
     */
    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerDao.save(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable(value = "id") UUID customerId,
            @RequestBody Customer customer
    ) throws ResourceNotFoundException {
        Customer updateCust = customerDao.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + customerId + " not found"));

        updateCust.setName(customer.getName());
        updateCust.setEmail(customer.getEmail());
        return ResponseEntity.ok(customerDao.save(updateCust));
    }

    @DeleteMapping("/customers/{id}")
    public Map<String, Boolean> deleteCustomer(
            @PathVariable(value = "id") UUID customerId)
            throws ResourceNotFoundException {

        Customer deleteCustomer = customerDao.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Id " + customerId + " not found"));

        this.customerDao.delete(deleteCustomer);

        Map<String, Boolean> responseMap = new HashMap<>();
        responseMap.put("deleted", Boolean.TRUE);
        return responseMap;
    }
}
