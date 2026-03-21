package com.electronic.store.controllers;

import com.electronic.store.dtos.CategoryDTO;
import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO)
    {
          CategoryDTO createdCategoryDTO = categoryService.create(categoryDTO);
          return ResponseEntity.ok(createdCategoryDTO);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") int id, @Valid @RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO updateCategory = categoryService.update(id,categoryDTO);
        return ResponseEntity.ok(updateCategory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable int id)
    {
       CategoryDTO deletedCategory =  categoryService.delete(id);
       return ResponseEntity.ok(deletedCategory);
    }
    @GetMapping("/getAll")
    public PageableResponse<CategoryDTO> getAllCategory(@RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
                                                        @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
                                                        @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
                                                        @RequestParam(value = "sortOrder",defaultValue = "asc",required = false) String sortOrder)
    {
        return categoryService.getAllCategory(pageNo,pageSize,sortBy,sortOrder);
    }
    @GetMapping("/getCategoryById/{id}")
    public ResponseEntity<CategoryDTO> getOneById(@PathVariable("id") int id)
    {
        CategoryDTO categoryDTO = categoryService.getCategory(id);
        return ResponseEntity.ok(categoryDTO);
    }
}
