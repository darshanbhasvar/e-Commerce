package com.example.productservicenaman.repos;

import com.example.productservicenaman.Models.Product;
import com.example.productservicenaman.ProductInfo;
import com.example.productservicenaman.ProductInfoWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    //different method for demo
    List<Product> findByTitleContaining(String word);

    @Override
    List<Product> findAll();

    Product save(Product product);

    @Override
    void deleteById(Long Long);

    Optional<Product> findById(Long id);

    @Query("select p from Product p where p.id=102")
     List<Product> productfindByhql();

    @Query("select p.id as id ,p.title as title from Product p where id=52")
    List<ProductInfo> something();

    //@Query(value="select p from product p",nativeQuery = true)
    @Query(value = "select p.id as id, p.title as title ,p.price as price from product p where p.id = 52", nativeQuery = true)
    List<ProductInfoWithIdAndTitle> somesome();
}
