package com.alen.store.dao;

import com.alen.store.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerDao extends JpaRepository<Customer, UUID> {}
