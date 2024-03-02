package com.example.productservicenaman;

import com.example.productservicenaman.Models.Product;
import com.example.productservicenaman.repos.CategoryRepository;
import com.example.productservicenaman.repos.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
class ProductServicenamanApplicationTests {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

   // private ProductInfoWithIdAndTitle;


    void contextLoads() {
    }

    @Test
    @Transactional
    @Commit
            void testQueries(){
        //List<Product> productList = productRepository.productfindByhql();

//        List<ProductInfo> productInfos = productRepository.something();
//
//        for(ProductInfo productInfo : productInfos){
//            System.out.println(productInfo.getId());
//            System.out.println(productInfo.getTitle());
//        }
        List<ProductInfoWithIdAndTitle> p = productRepository.somesome();
//        for(ProductInfoWithIdAndTitle : p){
//            System.out.println(productInfoWithIdAndTitle.)
//        }
    }
}
