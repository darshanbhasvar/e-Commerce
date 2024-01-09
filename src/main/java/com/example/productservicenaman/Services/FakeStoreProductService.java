package com.example.productservicenaman.Services;

import com.example.productservicenaman.Models.Category;
import com.example.productservicenaman.Models.Product;
import com.example.productservicenaman.dtos.FakeStoreProductDto;
import com.example.productservicenaman.exceptions.ProductNotExistException;
import org.apache.tomcat.util.http.FastHttpDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProduct) {
        Product product = new Product();
        product.setTitle(fakeStoreProduct.getTitle());
        product.setId(fakeStoreProduct.getId());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setDescription(fakeStoreProduct.getDescription());
        product.setImageUrl(fakeStoreProduct.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProduct.getCategory());

        return product;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
        //int a=10/0;
        FakeStoreProductDto productDto = restTemplate.getForObject(
                "http://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );
        if(productDto == null){
            throw new ProductNotExistException(
                    "Product with id "+id +"is not exist"
            );
        }
        return convertFakeStoreProductToProduct(productDto);

    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        List<Product> answer = new ArrayList<>();
        for (FakeStoreProductDto dto : response){
            answer.add(convertFakeStoreProductToProduct(dto));
        }
        return answer;
    }
//    public Product updateSingleProduct(Long id) {
//        FakeStoreProductDto productDto =
//        productDto.setTitle();
//    }

    @Override
    public Product addNewProduct(Product product) {
        FakeStoreProductDto dto = new FakeStoreProductDto();
        dto.setImage(product.getImageUrl());
        dto.setDescription(product.getDescription());;
        dto.setPrice(product.getPrice());
        dto.setId(product.getId());
        dto.setCategory("new category");

        dto.setTitle(product.getTitle());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(dto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" , HttpMethod.POST, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(response);
    }

    @Override
    public void deteteProduct(Long id) {
        restTemplate.delete(
                "http://fakestoreapi.com/products/" + id
        );
    }

}
