package com.hoandhh.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.hoandhh.backend.dtos.ProductDTO;
import com.hoandhh.backend.models.*;

public interface IProductService {
    public Product createProduct(ProductDTO productDTO);

    Product getProductById(long id);

    Page<Product> getAllProducts(PageRequest pageRequest);

    Product updateProduct(long id, ProductDTO productDTO);

    void deleteProduct(long id);

    Boolean existsByName(String name);

}
