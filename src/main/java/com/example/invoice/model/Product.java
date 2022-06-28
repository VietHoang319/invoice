package com.example.invoice.model;

import com.example.invoice.model.validation.IProductValidatior;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Tên không được trống")
    @Pattern(regexp = "[a-zA-Z\\s]{3,16}", message = "Không được nhập số")
    @IProductValidatior
    private String name;
    private long price;
    @Min(value=3, message = "Nhập lớn hơn 3")
    @Max(value = 10, message = "Nhập nhỏ hơn 10")
    private int quantity;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
