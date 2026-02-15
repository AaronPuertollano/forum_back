package com.esliceu.Myforum.controller;

import com.esliceu.Myforum.dto.CategoryDTO;
import com.esliceu.Myforum.dto.CreateCategoryDTO;
import com.esliceu.Myforum.model.Category;
import com.esliceu.Myforum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
