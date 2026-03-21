package com.electronic.store.services.impl;

import com.electronic.store.Helper.PageableResponseImpl;
import com.electronic.store.dtos.CategoryDTO;
import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.entities.Category;
import com.electronic.store.exception.ResourceNotFoundException;
import com.electronic.store.repositories.CategoryRepo;
import com.electronic.store.services.CategoryService;
import org.hibernate.boot.model.source.internal.hbm.Helper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper mapper;
    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        logger.info("Inside create method of category service class");
        Category category = mapper.map(categoryDTO,Category.class);
        Category returnedCategory =  categoryRepo.save(category);
        return mapper.map(returnedCategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO update(int id, CategoryDTO categoryDTO) {
        logger.info("Inside update method of category service class");
        Category category  =categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("There is no category with given id "+id));
        category.setTitle(categoryDTO.getTitle());
        category.setDescription(categoryDTO.getDescription());
        category.setCoverImage(categoryDTO.getCoverImage());
        Category updatedCategory = categoryRepo.save(category);
        return mapper.map(updatedCategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO delete(int id) {
        logger.info("Inisde delete method of category service class");
        Category deletedCategory = categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("No category with given id "+id));
        categoryRepo.deleteById(id);
        return mapper.map(deletedCategory,CategoryDTO.class);
    }

    @Override
    public PageableResponse<CategoryDTO> getAllCategory(int pageNo, int pageSize, String sortBy, String sortOrder) {
        logger.info("Inside getAllCategory method inside category service class");
        Sort sort = sortBy.equalsIgnoreCase("ASC")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        final var pageRequest = PageRequest.of(pageNo, pageSize, sort);
        Page<Category> listOfCategory = categoryRepo.findAll(pageRequest);
        return new PageableResponseImpl().getPageableResponse(listOfCategory, CategoryDTO.class);

    }

    @Override
    public CategoryDTO getCategory(int id) {
        logger.info("Inside getCategory method of category service class");
       return mapper.map(categoryRepo.findById(id),CategoryDTO.class);
    }
}
