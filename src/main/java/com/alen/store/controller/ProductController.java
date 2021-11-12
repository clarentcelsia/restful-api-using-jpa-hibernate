package com.alen.store.controller;

import com.alen.store.dao.ProductDao;
import com.alen.store.handler.ResourceNotFoundException;
import com.alen.store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** This will handle a data got from/to server to/from client */
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    ProductDao productDao;

    @GetMapping("/products")
    public List<Product> getProductLists() {
        return productDao.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductListById(@PathVariable(value = "id") int productId)
            throws ResourceNotFoundException
    {
        Product product = productDao.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No Product Available defining by id: " + productId));

        return ResponseEntity.ok().body(product);
    }


    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productDao.save(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> responseUpdateModel(
            @PathVariable(value = "id") int productId,
            @RequestBody Product product
    ) throws ResourceNotFoundException
    {
        Product updateProduct = productDao.findById(productId)
                .orElseThrow( ()-> new ResourceNotFoundException(
                        "No Product Available defining by id: " + productId) );

        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setStock(product.getStock());
        return ResponseEntity.ok(this.productDao.save(updateProduct));
    }

    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteProductById(
            @PathVariable(value = "id") int productId
    ) throws ResourceNotFoundException
    {
        Product product = productDao.findById(productId)
                .orElseThrow( ()-> new ResourceNotFoundException(
                        "No Product Available defining by id: " + productId) );

        this.productDao.delete(product);

        Map<String, Boolean> responseMap = new HashMap<>();
        responseMap.put("deleted", Boolean.TRUE);
        return responseMap;
    }

}

