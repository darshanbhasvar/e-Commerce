package com.example.productservicenaman.Services;

import com.example.productservicenaman.Models.Category;
import com.example.productservicenaman.Models.Product;
import com.example.productservicenaman.exceptions.ProductNotExistException;
import com.example.productservicenaman.repos.CategoryRepository;
import com.example.productservicenaman.repos.ProductRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("selfproductservice")
public class SelfProductService implements ProductService{


    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {

        Product[] p = productRepository.findAll().toArray(new Product[0]);
        List<Product> products = new ArrayList<>();
        for(Product pd : p){
        products.add(pd);
        }
        return products;
    }

    @Override
    public Product addNewProduct(Product product) {
        Optional<Category> optionalCategory = categoryRepository.findByName(product.getCategory().getName());

        if(optionalCategory.isEmpty()){
            product.setCategory(categoryRepository.save(product.getCategory()));
        }
        else{
            product.setCategory(optionalCategory.get());
        }
        return productRepository.save(product);
    }

    @Override
    public void deteteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
