package com.angelina.myWallet.controllers;

import com.angelina.myWallet.model.Category;
import com.angelina.myWallet.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        super();
        this.categoryRepository = categoryRepository;
    }
    @GetMapping("/categories")
    //The method returns a collection of Category objects.
    Collection<Category> categories(){
        return categoryRepository.findAll();
        //This line fetches all categories from the database. It's equivalent to the SQL query SELECT * FROM categories
    }
    //category/1
    @GetMapping("/categories/{id}")
    ResponseEntity<?> getCategory(@PathVariable Long id){
       // fetch a Category by its ID and returns an Optional<Category> which can either contain the category or be empty.
        Optional<Category> category = categoryRepository.findAllById(id);
        // ResponseEntity represent an HTTP response, including the status code, headers, and body
        // If the category is found, it is wrapped in a ResponseEntity with a 200 OK status.
        // If the category is not found, a ResponseEntity with a 404 Not Found status is returned.
        return category.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}