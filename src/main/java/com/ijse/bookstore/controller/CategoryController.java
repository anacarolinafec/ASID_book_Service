package com.ijse.bookstore.controller;

import com.ijse.bookstore.dto.CategoryCreationDTO;
import com.ijse.bookstore.entity.Category;
import com.ijse.bookstore.repository.CategoryRepository;
import com.ijse.bookstore.service.CategoryService;
import com.ijse.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CategoryController {
   
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory(){
         List<Category> categories = categoryService.getAllCategory();
         return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/category/register")
    public ResponseEntity<Category> createAuthor(@RequestBody CategoryCreationDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());

        Category saved = categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
   
}
