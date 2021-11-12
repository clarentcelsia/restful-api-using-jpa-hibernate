package com.alen.store.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/** DEFINING BEAN BY ANNOTATION METHOD
 *  Product class with annotation @Component no need to create constructor.
 *  Basically, bean creates [Product] product obj as a bean with no constructor,
 *  Define a new bean with constructor in appConfig but with no annotation @Commponent
 *  in this class.
 *
 *
 *  https://stackoverflow.com/questions/35108778/spring-bean-with-runtime-constructor-arguments
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "stock")
    private int stock;

    public int getId() { return this.id; }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
