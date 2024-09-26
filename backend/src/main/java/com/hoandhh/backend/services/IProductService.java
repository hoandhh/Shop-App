package com.hoandhh.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.hoandhh.backend.dtos.ProductDTO;
import com.hoandhh.backend.dtos.ProductImageDTO;
import com.hoandhh.backend.models.*;
import com.hoandhh.backend.responses.ProductResponse;

public interface IProductService {
    Product createProduct(ProductDTO productDTO) throws Exception;

    Product getProductById(long id) throws Exception;

    Page<ProductResponse> getAllProducts(PageRequest pageRequest);

    Product updateProduct(long id, ProductDTO productDTO) throws Exception;

    void deleteProduct(long id);

    Boolean existsByName(String name);

    ProductImage createProductImage(long productId, ProductImageDTO productImageDTO) throws Exception;

}
