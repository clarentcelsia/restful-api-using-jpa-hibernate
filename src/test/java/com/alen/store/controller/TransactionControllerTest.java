package com.alen.store.controller;

import com.alen.store.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    TransactionController transactionController;

    @Test
    public void contextLoads(){
        assertNotNull(transactionController);
    }

    @Test
    public void test_requestHttpToGetTransactionLists(){
        String url = "http://localhost:" + port + "/api/v3/transactions";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity("{}", httpHeaders);

        ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println(response.getBody());
        assertNotNull(response);
    }

    @Test
    public void test_findTransactionById_shouldReturnSuccess(){
        String url = "http://localhost:" + port + "/api/v3/transactions/e5008f58-ec65-4407-92ac-ea0ec9dac6e1";

        Transaction trx = testRestTemplate.getForObject(url, Transaction.class);
        System.out.println("customer: " + trx.getCustomer_id() + " productId: " + trx.getProduct_id());
        assertNotNull(trx);
    }

    @Test
    public void test_requestHttpToCreateTransaction(){
        String url = "http://localhost:" + port + "/api/v3/transactions";

        Transaction transaction = new Transaction();
        transaction.setCustomer_id(UUID.fromString("9449605d-d858-4faf-9781-6cee204f58be"));
        transaction.setProduct_id(2);
        transaction.setQty(2);
        transaction.setDate(new Date());

        ResponseEntity<Transaction> response = testRestTemplate.postForEntity(url, transaction, Transaction.class);
        System.out.println(response.getBody());
        assertNotNull(response);
        assertNotNull(response.getBody());
    }
}