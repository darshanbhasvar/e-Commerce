package com.example.productservicenaman.Services;

import com.example.productservicenaman.Models.Product;
import com.example.productservicenaman.exceptions.ProductNotExistException;

import java.util.List;


public interface ProductService {
    Product getSingleProduct(Long id) throws ProductNotExistException;

    List<Product> getAllProducts();

   // Product updateSingleProduct(Long id);

    Product addNewProduct(Product product);

    void deteteProduct(Long id);
}
