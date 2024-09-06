package com.hoandhh.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    //http://localhost:8888/api/v1/categories?page=1&limit=10
    @GetMapping("")
    public ResponseEntity<String> getAllCategories(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return ResponseEntity.ok(String.format("getAllCategories, page = %d, limit = %d", page, limit));
    }

    @PostMapping("")
    public ResponseEntity<String> insertCategory() {
        return ResponseEntity.ok("This is post mapping");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> addCategory(@PathVariable Long id) {
        return ResponseEntity.ok("This is put mapping, id = " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok("This is delete mapping, id = " + id);
    }

}
