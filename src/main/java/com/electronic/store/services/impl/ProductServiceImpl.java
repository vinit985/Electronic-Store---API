package com.electronic.store.services.impl;

import com.electronic.store.Helper.PageableResponseImpl;
import com.electronic.store.dtos.CategoryDTO;
import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.dtos.ProductDTO;
import com.electronic.store.entities.Category;
import com.electronic.store.entities.Product;
import com.electronic.store.exception.ResourceNotFoundException;
import com.electronic.store.repositories.CategoryRepo;
import com.electronic.store.repositories.ProductRepo;
import com.electronic.store.services.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        logger.info("Inside create method of product service class ");
        productDTO.setAddedDate(new Date());
        Product product = mapper.map(productDTO, Product.class);
        Product savedProduct = productRepo.save(product);
        return mapper.map(savedProduct, ProductDTO.class);
    }
    //Create product with category
    public ProductDTO createProductWithCategory(ProductDTO productDTO,int categoryId)
    {
       //fetching category to check do we have this category or not?
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("No category found with given id"));
        productDTO.setAddedDate(new Date());
        Product product = mapper.map(productDTO, Product.class);
        product.setCategory(category);
        Product savedProduct = productRepo.save(product);
        logger.info("category id {}",savedProduct.getCategory().getCategoryId());

     ProductDTO productDTO1 =  mapper.map(savedProduct, ProductDTO.class);
     productDTO1.setCategoryDTO(mapper.map(category,CategoryDTO.class));
     return productDTO1;
    }


    @Override
    public ProductDTO update(Integer id, ProductDTO productDTO) {
        logger.info("Inside update method of product service class ");
        Product productDraft = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is no product with given id"));
        productDraft.setTitle(productDTO.getTitle());
        productDraft.setDescription(productDTO.getDescription());
        productDraft.setPrice(productDTO.getPrice());
        productDraft.setDiscountedPrice(productDTO.getDiscountedPrice());
        productDraft.setAvailableQuantity(productDTO.getAvailableQuantity());
        productDraft.setProductImageName(productDTO.getProductImageName());
        productDraft.setLive(productDTO.isLive());
        productDraft.setInStock(productDTO.isInStock());
        Product updatedProduct = productRepo.save(productDraft);
        return mapper.map(updatedProduct, ProductDTO.class);


    }

    @Override
    public ProductDTO updateCategoryOfExistingProduct(int productId, int categoryId) {
        Product product = productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product with given id not found"));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category not found with given id"));
        product.setCategory(category);
        productRepo.save(product);
      ProductDTO productDTO = mapper.map(product,ProductDTO.class);
      productDTO.setCategoryDTO(mapper.map(category,CategoryDTO.class));
      return productDTO;
    }

    @Override
    public void delete(int id,String filePath) throws IOException {
        logger.info("Inside delete method of product service class ");
        String filename = getById(id).getProductImageName();
        String completePath = filePath+filename;
        Path path = Paths.get(completePath);
        Files.deleteIfExists(path);
        productRepo.deleteById(id);
    }

    @Override
    public ProductDTO getById(int id) {
        logger.info("Inside getById method of product service class ");
        Product product = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is no product with given id to fetch"));
        logger.info("Category id inside getById method of product controller class {}",product.getCategory().getCategoryId());
        ProductDTO productDTO =  mapper.map(product, ProductDTO.class);
        productDTO.setCategoryDTO(mapper.map(categoryRepo.findById(product.getCategory().getCategoryId()),CategoryDTO.class));
       return productDTO;
    }

    @Override
    public PageableResponse<ProductDTO> getAll(int pageNo, int pageSize, String sortOrder, String sortBy) {
        logger.info("Inside getAll method of product service class ");
        Sort sort = sortOrder.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> listOfProduct = productRepo.findAll(pageable);
        return new PageableResponseImpl().getPageableResponse(listOfProduct, ProductDTO.class);

    }

    @Override
    public PageableResponse<ProductDTO> getAllLive(int pageNo, int pageSize, String sortOrder, String sortBy) {
        logger.info("Inside getAllLive method of product service class ");
        Sort sort = sortOrder.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> pageableCategory = productRepo.findByLiveTrue(pageable);
        return new PageableResponseImpl().getPageableResponse(pageableCategory, ProductDTO.class);

    }

    @Override
    public PageableResponse<ProductDTO> searchByTitle(String title, int pageNo, int pageSize, String sortOrder, String sortBy) {
        logger.info("Inside searchByTitle method of product service class ");
        Sort sort = sortOrder.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> pageableCategory = productRepo.findByTitleContaining(title,pageable);
        return new PageableResponseImpl().getPageableResponse(pageableCategory, ProductDTO.class);
    }
}
