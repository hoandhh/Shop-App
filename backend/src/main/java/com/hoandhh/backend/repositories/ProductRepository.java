package com.hoandhh.backend.repositories;

import com.hoandhh.backend.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Boolean existsByName(String name);

    // Phân trang
    Page<Product> findAll(Pageable pageable);
}
