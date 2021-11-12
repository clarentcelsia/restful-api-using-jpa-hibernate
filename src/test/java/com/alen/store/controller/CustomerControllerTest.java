package com.alen.store.controller;

import com.alen.store.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    CustomerController customerController;

    /* This injected port value contains random port. */
    @LocalServerPort
    private int port;

    /* Check if the controller created successfully */
    @Test
    public void contextLoads() throws Exception {
        assertNotNull(customerController);
    }


    @Test
    public void test_shouldGetAllCustomerLists() throws Exception {
        //prepare header and body
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>("{}", httpHeaders);

        String url = "http://localhost:" + port + "/api/v2/customers";

        /*
         *  exchange(): execute HTTP method to the given url,
         *  Melakukan request entity yang mengembalikan nilai sesuai dengan respon yang diberikan.
         */
        ResponseEntity<String> getCustomers = testRestTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println(getCustomers.getBody());
        assertNotNull(getCustomers.getBody());
    }

    @Test
    public void test_getCustomerById_returns_success() {
        String url = "http://localhost:" + port + "/api/v2/customers/9449605d-d858-4faf-9781-6cee204f58be";
        Customer customer = testRestTemplate.getForObject(url, Customer.class);

        System.out.println("Customer with id: " + customer.getId() + ", name: " + customer.getName());
        assertNotNull(customer);
    }

    @Test
    public void test_createCustomerSuccessfully() {
        String url = "http://localhost:" + port + "/api/v2/customers";

        Customer customer = new Customer();
        customer.setName("Josh");
        customer.setEmail("josh@gmail.com");
        ResponseEntity<Customer> response = testRestTemplate.postForEntity(url, customer, Customer.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
    }

    @Test
    public void test_updateCustomerSuccessfully() {
        String url = "http://localhost:" + port + "/api/v2/customers/9449605d-d858-4faf-9781-6cee204f58be";

        Customer getCustomer = testRestTemplate.getForObject(url, Customer.class);
        getCustomer.setName("jacob");

        testRestTemplate.put(url, getCustomer);
        Customer getUpdatedCustomer = testRestTemplate.getForObject(url, Customer.class);
        System.out.println(getUpdatedCustomer.getName());

        assertNotNull(getUpdatedCustomer);
    }

    @Test
    public void test_deleteCustomerByIdSuccessfully() {
        String url = "http://localhost:" + port + "/api/v2/customers/9449605d-d858-4faf-9781-6cee204f58be";

        Customer customer = testRestTemplate.getForObject(url, Customer.class);
        assertNotNull(customer);

        testRestTemplate.delete(url);

        //check if the item has deleted
        try {
            customer = testRestTemplate.getForObject(url, Customer.class);
            System.out.println(customer.getName());
        }catch (HttpClientErrorException e){
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}