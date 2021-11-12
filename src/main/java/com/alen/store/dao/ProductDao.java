package com.alen.store.dao;

import com.alen.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* ProductDao as a bridge to get access from/to database */
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {}
