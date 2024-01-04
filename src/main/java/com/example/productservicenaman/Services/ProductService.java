package com.example.productservicenaman.Services;

import com.example.productservicenaman.Models.Product;

import java.util.List;


public interface ProductService {
    Product getSingleProduct(Long id);

    List<Product> getAllProducts();

    Product updateSingleProduct(Long id);

    Product addNewProduct(Product product);

    void deteteProduct(Long id);
}
