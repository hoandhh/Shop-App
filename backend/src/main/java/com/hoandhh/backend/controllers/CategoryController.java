package com.hoandhh.backend.controllers;

import com.hoandhh.backend.dtos.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
    @GetMapping("")
    public ResponseEntity<String> getAllCategories(@RequestParam int page, @RequestParam int limit) {
        return ResponseEntity.ok(String.format("getAllCategories, page = %d, limit = %d", page, limit));
    }

    @PostMapping("")
    public ResponseEntity<?> insertCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok(String.format("This is insertCategory: %s", categoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> addCategory(@PathVariable Long id) {
        return ResponseEntity.ok(String.format("addCategory, id = %d", id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok(String.format("deleteCategory, id = %d", id));
    }

}
