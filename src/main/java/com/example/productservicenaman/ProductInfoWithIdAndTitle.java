package com.example.productservicenaman;

/**
 * Projection for {@link com.example.productservicenaman.Models.Product}
 */
public interface ProductInfoWithIdAndTitle {
    Long getId();

    String getTitle();

    Double getPrice();
}