package com.electronic.store.services.impl;

import com.electronic.store.Helper.PageableResponseImpl;
import com.electronic.store.dtos.CategoryDTO;
import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.entities.Category;
import com.electronic.store.exception.ResourceNotFoundException;
import com.electronic.store.repositories.CategoryRepo;
import com.electronic.store.services.CategoryService;
import com.electronic.store.services.FileService;
import org.hibernate.boot.model.source.internal.hbm.Helper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper mapper;
    @Value("${user.profile.image.path.category}")
    String FILE_PATH;

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        logger.info("Inside create method of category service class");
        Category category = mapper.map(categoryDTO, Category.class);
        Category returnedCategory = categoryRepo.save(category);
        return mapper.map(returnedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO update(int id, CategoryDTO categoryDTO) {
        logger.info("Inside update method of category service class");
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is no category with given id " + id));
        category.setTitle(categoryDTO.getTitle());
        category.setDescription(categoryDTO.getDescription());
        category.setCoverImage(categoryDTO.getCoverImage());
        Category updatedCategory = categoryRepo.save(category);
        return mapper.map(updatedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO delete(int id) throws IOException {
        logger.info("Inisde delete method of category service class");
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is no category with given id " + id));
        Files.deleteIfExists(Paths.get(FILE_PATH + category.getCoverImage()));
        Category deletedCategory = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No category with given id " + id));
        categoryRepo.deleteById(id);
        return mapper.map(deletedCategory, CategoryDTO.class);
    }

    @Override
    public PageableResponse<CategoryDTO> getAllCategory(int pageNo, int pageSize, String sortBy, String sortOrder) {
        logger.info("Inside getAllCategory method inside category service class");
        Sort sort = sortBy.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        final var pageRequest = PageRequest.of(pageNo, pageSize, sort);
        Page<Category> listOfCategory = categoryRepo.findAll(pageRequest);
        return new PageableResponseImpl().getPageableResponse(listOfCategory, CategoryDTO.class);

    }

    @Override
    public CategoryDTO getCategory(int id) {
        logger.info("Inside getCategory method of category service class");
        return mapper.map(categoryRepo.findById(id), CategoryDTO.class);
    }

    @Override
    public String uploadImage(int id, MultipartFile file) throws IOException {

        FileService fileService = new FileServiceImpl();
        String fileName = fileService.uploadImage(file, FILE_PATH);
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is no category with this id"));
        category.setCoverImage(fileName);
        categoryRepo.save(category);
        return fileName;


    }

    @Override
    public InputStream getUploadedImage(int id) throws FileNotFoundException {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is no category with this id"));
        String fileName = category.getCoverImage();
        logger.info("File name in get file method {}", fileName);
        logger.info("Path name in get file method {}", FILE_PATH + fileName);
        InputStream inputStream = new FileServiceImpl().getResource(FILE_PATH , fileName);
        return inputStream;
    }
}
