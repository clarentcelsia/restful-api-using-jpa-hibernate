package com.alen.store.controller;

import com.alen.store.dao.TransactionDao;
import com.alen.store.handler.ResourceNotFoundException;
import com.alen.store.model.Product;
import com.alen.store.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v3")
public class TransactionController {

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    ProductController productController;

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactionLists() {
        return transactionDao.findAll();
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> findTransactionId(
            @PathVariable(value = "id") UUID transactionId
    ) throws ResourceNotFoundException {

        Transaction getTransaction = transactionDao.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("TransactionId " + transactionId + "was not found"));

        return ResponseEntity.ok(getTransaction);
    }


    /* [@Transactional]  creates a proxy(=perwakilan) class where behave the same as
     *  the actual class/method annotated by @Transactional.
     *  Proxy will generate at runtime then If exception occurred ,
     *  while executing the entity then they will be rollback(= return to the original state)
     */

    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Transaction> insertTransaction(@RequestBody Transaction transaction)
            throws ResourceNotFoundException {

        //TODO: UPDATING PRODUCT
        Product product = productController.getProductListById(transaction.getProduct_id()).getBody();
        if (product != null) {
            product.setStock((product.getStock() - transaction.getQty()));
            productController.responseUpdateModel(product.getId(), product);

            transaction.setSubtotal((product.getPrice() * transaction.getQty()));
            return ResponseEntity.ok(transactionDao.save(transaction));
        } else {
            transaction.setSubtotal(0);
            return null;
        }
    }

}
