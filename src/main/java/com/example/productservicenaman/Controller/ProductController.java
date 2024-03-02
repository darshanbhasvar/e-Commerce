package com.example.productservicenaman.Controller;


import com.example.productservicenaman.Models.Product;
import com.example.productservicenaman.Services.FakeStoreProductService;
import com.example.productservicenaman.Services.ProductService;
import com.example.productservicenaman.common.AuthenticationCommon;
import com.example.productservicenaman.dtos.ArithmaticExceptionDto;
import com.example.productservicenaman.dtos.Role;
import com.example.productservicenaman.dtos.UserDto;
import com.example.productservicenaman.exceptions.ProductNotExistException;
import com.example.productservicenaman.repos.CategoryRepository;
import com.example.productservicenaman.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private RestTemplate restTemplate;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private AuthenticationCommon authenticationCommon;

    @Autowired


    public ProductController(@Qualifier("fakestoreproductservice") ProductService productService , RestTemplate restTemplate,
                             CategoryRepository categoryRepository,
                             ProductRepository productRepository,AuthenticationCommon authenticationCommon) {
        this.productService = productService;
        this.restTemplate = restTemplate;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.authenticationCommon = authenticationCommon;
    }

        @GetMapping() // localhost:8080/products
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader String token) {

            //manual authentication
//        UserDto userDto = authenticationCommon.validateToken(token);
//        if( userDto== null){
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//        boolean isAdmin = false;
//        for(Role role : userDto.getRoles()){
//            if(role.getName().equals("ADMIN")){
//                isAdmin = true;
//                break;
//            }
//        }
//        if( ! isAdmin){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }

            List<Product> products = productService.getAllProducts(); // o p q

            //List<Product> finalProducts = new ArrayList<>();

//            for (Product p : products) { // o  p q
//                p.setTitle("Hello" + p.getTitle());
//                finalProducts.add(p);
//            }

//            ResponseEntity<List<Product>> response = new ResponseEntity<>(
//                    finalProducts, HttpStatus.OK
//            );
            ResponseEntity<List<Product>> productResponseEntity = new ResponseEntity<>(products, HttpStatus.OK);
            return productResponseEntity;
            
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
        return productService.updateProduct(id,product);
    }
    @PutMapping("/{id}")
//    public Product replaceProduct (@PathVariable("id")Long id) {
//        return productService.updateSingleProduct(id);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        productService.deteteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmaticExceptionDto> handleProductNotExistException() {
        ArithmaticExceptionDto dto = new ArithmaticExceptionDto();
        dto.setMessage("at controller level");
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
