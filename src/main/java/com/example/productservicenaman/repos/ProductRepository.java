package com.example.productservicenaman.repos;

import com.example.productservicenaman.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
