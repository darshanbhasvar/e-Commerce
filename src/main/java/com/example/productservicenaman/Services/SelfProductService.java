package com.example.productservicenaman.Services;

import com.example.productservicenaman.Models.Product;
import com.example.productservicenaman.exceptions.ProductNotExistException;
import com.example.productservicenaman.repos.ProductRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfproductservice")
public class SelfProductService implements ProductService{


    @Autowired
    ProductRepository productRepository;
    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    @Override
    public void deteteProduct(Long id) {

    }
}
