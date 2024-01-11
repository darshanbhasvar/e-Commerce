package com.example.productservicenaman.Controller;


import com.example.productservicenaman.Models.Product;
import com.example.productservicenaman.Services.FakeStoreProductService;
import com.example.productservicenaman.Services.ProductService;
import com.example.productservicenaman.dtos.ArithmaticExceptionDto;
import com.example.productservicenaman.exceptions.ProductNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private RestTemplate restTemplate;

    @Autowired


    public ProductController(@Qualifier("selfproductservice") ProductService productService , RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }
    @GetMapping() // localhost:8080/products
    public ResponseEntity<List<Product>> getAllProducts() {

    return new ResponseEntity<List<Product>>( productService.getAllProducts(), HttpStatus.FORBIDDEN);
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id")Long id ) throws ProductNotExistException {
        return productService.getSingleProduct(id);
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }
    @PatchMapping("/{id}")
    public Product updateProduct (@PathVariable("id")Long id , @RequestBody Product product) {
        return new Product();
    }
    @PutMapping("/{id}")
//    public Product replaceProduct (@PathVariable("id")Long id) {
//        return productService.updateSingleProduct(id);
//    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deteteProduct(id);
    }
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmaticExceptionDto> handleProductNotExistException() {
        ArithmaticExceptionDto dto = new ArithmaticExceptionDto();
        dto.setMessage("at controller level");
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
