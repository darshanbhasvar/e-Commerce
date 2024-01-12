package com.example.productservicenaman.repos;

import com.example.productservicenaman.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    //different method for demo
    List<Product> findByTitleContaining(String word);

    @Override
    List<Product> findAll();

    Product save(Product product);

    @Override
    void deleteById(Long Long);
}
