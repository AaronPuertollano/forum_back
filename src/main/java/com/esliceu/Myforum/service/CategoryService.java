package com.esliceu.Myforum.service;

import com.esliceu.Myforum.dto.CreateCategoryDTO;
import com.esliceu.Myforum.model.Category;
import com.esliceu.Myforum.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(CreateCategoryDTO request) {

        Category category = new Category();
        category.setTitle(request.getTitle());
        category.setDescription(request.getDescription());

        String baseSlug = generateSlug(request.getTitle());
        String finalSlug = baseSlug;

        int counter = 1;

        while (categoryRepository.findBySlug(finalSlug).isPresent()) {
            finalSlug = baseSlug + "-" + counter;
            counter++;
        }

        category.setSlug(finalSlug);

        category.setColor("#" + new Random().nextInt(0xffffff));
        category.setModerators(new ArrayList<>());

        return categoryRepository.save(category);
    }

    private String generateSlug(String title) {
        return title.toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
    }

    public Category getBySlug(String slug) {

        return categoryRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category updateCategory(String slug, CreateCategoryDTO request) {

        Category category = categoryRepository.findBySlug(slug)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")
                );

        category.setTitle(request.getTitle());
        category.setDescription(request.getDescription());

        return categoryRepository.save(category);
    }

    public boolean deleteCategory(String slug) {
        Optional<Category> categoryOpt = categoryRepository.findBySlug(slug);

        if (categoryOpt.isPresent()) {
            categoryRepository.delete(categoryOpt.get());
            return true;
        } else {
            return false;
        }
    }

}
