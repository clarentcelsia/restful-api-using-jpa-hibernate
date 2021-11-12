package com.alen.store.controller;

import com.alen.store.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    ProductController productController;

    @LocalServerPort
    private int port;

    @Test
    public void contextLoads(){
        assertNotNull(productController);
    }

    @Test
    public void test_reqHttpToGetAllProductLists_shouldReturnSuccess(){
        // Disini akan mencetak hasil daftar list product yang diambil dari server
        // server HTTP memiliki header dan body. Header yang dimiliki pada umumnya terdapat
        // Authentication dan content-type.

        // prepare header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>("{}", httpHeaders);

        String url = "http://localhost:" + port + "/api/v1/products";

        // Melakukan request method http get untuk mendapatkan keseluruhan data.
        // Request method get memberikan response body dengan tipe data yang ditentukan.
        ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println(response.getBody());
        assertNotNull(response.getBody());
    }

    @Test
    public void test_requestHttpToGetProductById_shouldReturnSuccess(){
        // Disini melakukan request body berdasarkan nilai dari 'object' yang diberikan.
        // Object disini didefine sebagai product_id.

        String url = "http://localhost:" + port + "/api/v1/products/1";

        Product product = testRestTemplate.getForObject(url, Product.class);
        System.out.println(product.getName());
        assertNotNull(product);
    }

    @Test
    public void test_requestHttpToCreateProduct_shouldReturnSuccess(){
        String url = "http://localhost:" + port + "/api/v1/products";

        Product product = new Product();
        product.setName("Scarf");
        product.setPrice(55);
        product.setStock(12);
        ResponseEntity<Product> newProduct = testRestTemplate.postForEntity(url, product, Product.class);
        System.out.println(newProduct.toString());

        assertNotNull(newProduct);
        assertNotNull(newProduct.getBody());
    }

    @Test
    public void test_requestHttpToUpdateProductById_shouldReturnSuccess(){
        String url = "http://localhost:" + port + "/api/v1/products/1";

        Product updateGetProductById = testRestTemplate.getForObject(url, Product.class);
        updateGetProductById.setName("Scarf");
        testRestTemplate.put(url, updateGetProductById);

        // verify
        Product updatedProduct = testRestTemplate.getForObject(url, Product.class);
        System.out.println(updatedProduct.getName());
        assertNotNull(updatedProduct);
    }

    @Test
    public void test_requestHttpToDeleteProductById_shouldReturnSuccess(){
        String url = "http://localhost:" + port + "/api/v1/products/1";

        // check if the item exists
        Product getProductById = testRestTemplate.getForObject(url, Product.class);
        assertNotNull(getProductById);
        testRestTemplate.delete(url);

        // verify
        try{
            getProductById = testRestTemplate.getForObject(url, Product.class);
        }catch (HttpClientErrorException e){
            // error occurred if the data doesnt exists to be deleted.
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }

    }
}