// package com.hoandhh.backend.services;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.stereotype.Service;

// import com.hoandhh.backend.dtos.ProductDTO;
// import com.hoandhh.backend.exceptions.DataNotFoundException;
// import com.hoandhh.backend.models.Category;
// import com.hoandhh.backend.models.Product;
// import com.hoandhh.backend.repositories.CategoryRepository;
// import com.hoandhh.backend.repositories.ProductRepository;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class ProductService implements IProductService {
//     public final ProductRepository productRepository;
//     public final CategoryRepository categoryRepository;

//     public Product createProduct(ProductDTO productDTO) {
//         try {
//             Category existingCategory = categoryRepository
//                     .findById(productDTO.getCategoryId())
//                     .orElseThrow(() -> new DataNotFoundException(
//                             "Cannot find category with id: " + productDTO.getCategoryId()));
//             Product newProduct = Product.builder()
//                     .name(productDTO.getName())
//                     .price(productDTO.getPrice())
//                     .thumbnail(productDTO.getThumbnail())
//                     .description(productDTO.getDescription())
//                     .category(existingCategory)
//                     .build();
//             return productRepository.save(newProduct);
//         } catch (DataNotFoundException e) {
//             // Xử lý ngoại lệ, ví dụ: ghi log hoặc ném ngoại lệ runtime
//             throw new RuntimeException(e);
//         }
//     }

//     public Product getProductById(long id) {
//         return null;
//     }

//     public Page<Product> getAllProducts(PageRequest pageRequest) {
//         return null;
//     }

//     public Product updateProduct(long id, ProductDTO productDTO) {
//         return null;
//     }

//     public void deleteProduct(long id) {
//     }

//     public Boolean existsByName(String name) {
//         return null;
//     }

// }
