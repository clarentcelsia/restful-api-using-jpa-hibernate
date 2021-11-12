package com.alen.store.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    public void test_inputProductSuccessfully(){
        Product product = new Product();
        product.setId(1);
        product.setName("Bracelet");
        product.setPrice(50);
        product.setStock(20);
        assertNotNull(product);
    }

    @Test
    public void test_canGetIdOfProduct(){
        Product product = new Product();
        product.setId(1);
        int expectedResult = 1;
        assertEquals(expectedResult, product.getId());
    }

    @Test
    public void test_canGetPriceOfProduct(){
        Product product = new Product();
        product.setPrice(50);
        int expectedResult = 50;
        assertEquals(expectedResult, product.getPrice());
    }

    @Test
    public void test_canGetStockOfProduct(){
        Product product = new Product();
        product.setStock(20);
        int expectedResult = 20;
        assertEquals(expectedResult, product.getStock());
    }

    @Test
    public void test_canGetNameOfProduct(){
        Product product = new Product();
        product.setName("Bracelet");
        String expectedResult = "Bracelet";
        assertEquals(expectedResult, product.getName());
    }

}