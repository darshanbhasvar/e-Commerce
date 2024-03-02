package com.example.productservicenaman.Services;

import com.example.productservicenaman.Models.Category;
import com.example.productservicenaman.Models.Product;
import com.example.productservicenaman.exceptions.ProductNotExistException;
import com.example.productservicenaman.repos.CategoryRepository;
import com.example.productservicenaman.repos.ProductRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

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
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()){
            throw new ProductNotExistException("product with id "+id +"does't exist");
        }
        Product product = optionalProduct.get();
        return product;
    }

    @Override
    public List<Product> getAllProducts() {

//        Product[] p = productRepository.findAll().toArray(new Product[0]);
//        List<Product> products = new ArrayList<>();
//        for(Product pd : p){
//        products.add(pd);this method also would work.
//        }
        List<Product> products = productRepository.findAll();
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

    public void deteteProduct(Long id) {
//        categoryRepository.deleteById(productRepository.findById(id));
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new RuntimeException();
        }
        Product p = optionalProduct.get();
        p.setDeleted(true);
        return;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()) throw new RuntimeException();

        Product savedProduct = optionalProduct.get();

        if(product.getTitle() != null){
            savedProduct.setTitle(product.getTitle());
        }
        if(product.getPrice() != null){
            savedProduct.setPrice(product.getPrice());
        }

        return productRepository.save(savedProduct);
    }
}
