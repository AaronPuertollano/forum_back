package com.esliceu.Myforum.service;

import com.esliceu.Myforum.dto.CreateCategoryDTO;
import com.esliceu.Myforum.model.Category;
import com.esliceu.Myforum.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        category.setSlug(generateSlug(request.getTitle()));
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

}
