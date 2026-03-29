package com.electronic.store.services;

import com.electronic.store.dtos.CategoryDTO;
import com.electronic.store.dtos.PageableResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface CategoryService {

    //create
    CategoryDTO create(CategoryDTO categoryDTO);
    //update
    CategoryDTO update(int id ,CategoryDTO categoryDTO);
    //delete
    CategoryDTO delete(int id) throws IOException;
    //getAll
    PageableResponse<CategoryDTO> getAllCategory(int pageNo, int pageSize, String sortBy, String sortOrder);
    //getOne
    CategoryDTO getCategory(int id);
    String uploadImage(int id, MultipartFile file) throws IOException;
    //search
    InputStream getUploadedImage(int id) throws FileNotFoundException;

}
