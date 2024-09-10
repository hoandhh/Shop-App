package com.hoandhh.backend.repositories;

import com.hoandhh.backend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
