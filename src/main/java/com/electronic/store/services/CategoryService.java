package com.electronic.store.services;

import com.electronic.store.dtos.CategoryDTO;
import com.electronic.store.dtos.PageableResponse;

import java.util.List;

public interface CategoryService {

    //create
    CategoryDTO create(CategoryDTO categoryDTO);
    //update
    CategoryDTO update(int id ,CategoryDTO categoryDTO);
    //delete
    CategoryDTO delete(int id);
    //getAll
    PageableResponse<CategoryDTO> getAllCategory(int pageNo, int pageSize, String sortBy, String sortOrder);
    //getOne
    CategoryDTO getCategory(int id);
    //search

}
