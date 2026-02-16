package com.esliceu.Myforum.controller;

import com.esliceu.Myforum.dto.CategoryDTO;
import com.esliceu.Myforum.dto.CreateCategoryDTO;
import com.esliceu.Myforum.model.Category;
import com.esliceu.Myforum.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories() {

        List<CategoryDTO> categories = categoryService.getAllCategories()
                .stream()
                .map(CategoryDTO::new)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(
            @RequestBody CreateCategoryDTO request) {

        Category category = categoryService.createCategory(request);

        return ResponseEntity.ok(new CategoryDTO(category));
    }

    @PutMapping("/{slug}")
    public ResponseEntity<CategoryDTO> updateCategory(
            @PathVariable String slug,
            @RequestBody CreateCategoryDTO request) {

        Category updated = categoryService.updateCategory(slug, request);

        return ResponseEntity.ok(new CategoryDTO(updated));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<CategoryDTO> getCategoryBySlug(
            @PathVariable String slug) {

        Category category = categoryService.getBySlug(slug);
        return ResponseEntity.ok(new CategoryDTO(category));
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable String slug,
                                                  HttpServletRequest request) {

        boolean deleted = categoryService.deleteCategory(slug);
        return ResponseEntity.ok(deleted);
    }

}
