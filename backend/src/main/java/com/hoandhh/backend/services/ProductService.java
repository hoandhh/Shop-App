package com.hoandhh.backend.services;

import java.lang.classfile.ClassFile.Option;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hoandhh.backend.dtos.ProductDTO;
import com.hoandhh.backend.dtos.ProductImageDTO;
import com.hoandhh.backend.exceptions.DataNotFoundException;
import com.hoandhh.backend.exceptions.InvalidParamException;
import com.hoandhh.backend.models.Category;
import com.hoandhh.backend.models.Product;
import com.hoandhh.backend.models.ProductImage;
import com.hoandhh.backend.repositories.CategoryRepository;
import com.hoandhh.backend.repositories.ProductImageRepository;
import com.hoandhh.backend.repositories.ProductRepository;
import com.hoandhh.backend.responses.ProductResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    public final ProductRepository productRepository;
    public final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;

    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException {
        Category existingCategory = categoryRepository
                .findById(productDTO.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find category with id: " + productDTO.getCategoryId()));
        Product newProduct = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .thumbnail(productDTO.getThumbnail())
                .description(productDTO.getDescription())
                .category(existingCategory)
                .build();
        return productRepository.save(newProduct);
    }

    public Product getProductById(long productId) throws Exception {
        return productRepository.findById(productId).orElseThrow(() -> new DataNotFoundException(
                "Cannot find product with id =" + productId));
    }

    public Page<ProductResponse> getAllProducts(PageRequest pageRequest) {
        // Lấy danh sách sản phẩm theo page và limit
        return productRepository.findAll(pageRequest).map(product -> ProductResponse.fromProduct(product));
    }

    public Product updateProduct(long id, ProductDTO productDTO) throws Exception {
        Product existingProduct = getProductById(id);
        if (existingProduct != null) {
            Category existingCategory = categoryRepository
                    .findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new DataNotFoundException(
                            "Cannot find category with id: " + productDTO.getCategoryId()));
            // Copy các thuộc tính từ productDTO -> existingProduct, dùng ModelMapper
            existingProduct.setName(productDTO.getName());
            existingProduct.setCategory(existingCategory);
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setThumbnail(productDTO.getThumbnail());
            productRepository.save(existingProduct);
        }
        return null;
    }

    public void deleteProduct(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
        }
    }

    public Boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    public ProductImage createProductImage(long productId, ProductImageDTO productImageDTO) throws Exception {
        Product existingProduct = productRepository
                .findById(productId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find product with id =" + productId));
        ProductImage newProductImage = ProductImage.builder()
                .product(existingProduct)
                .imageUrl(productImageDTO.getImageUrl())
                .build();
        int size = productImageRepository.findByProductId(productId).size();
        if (size > 5) {
            throw new InvalidParamException("Product's images must be <= 5");
        }
        return productImageRepository.save(newProductImage);
    }
}
