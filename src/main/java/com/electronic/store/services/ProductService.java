package com.electronic.store.services;

import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.dtos.ProductDTO;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    //create
    ProductDTO create(ProductDTO productDTO);
    ProductDTO createProductWithCategory(ProductDTO productDTO,int categoryId);
    //update
    ProductDTO update(Integer id ,ProductDTO productDTO);
    //Update category of existing product
    ProductDTO updateCategoryOfExistingProduct(int productId,int categoryId);
    //delete
    void delete(int id,String filePath) throws IOException;
    //getSingle
    ProductDTO getById(int id);
    //getAll
    PageableResponse<ProductDTO> getAll(int pageNo, int pageSize, String sortOrder, String sortBy);
    //getAll live
    PageableResponse<ProductDTO> getAllLive(int pageNo, int pageSize, String sortOrder, String sortBy);
    //search
    PageableResponse<ProductDTO> searchByTitle(String title,int pageNo, int pageSize, String sortOrder, String sortBy);
}
