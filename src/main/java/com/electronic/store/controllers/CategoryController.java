package com.electronic.store.controllers;

import com.electronic.store.dtos.APIResponseMessage;
import com.electronic.store.dtos.CategoryDTO;
import com.electronic.store.dtos.ImageResponse;
import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.services.CategoryService;
import com.electronic.store.services.FileService;
import com.electronic.store.services.impl.FileServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Value("${user.profile.image.path.category}")
    String FILE_PATH;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO createdCategoryDTO = categoryService.create(categoryDTO);
        return ResponseEntity.ok(createdCategoryDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") int id, @Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updateCategory = categoryService.update(id, categoryDTO);
        return ResponseEntity.ok(updateCategory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable int id) throws IOException {
        CategoryDTO deletedCategory = categoryService.delete(id);
        return ResponseEntity.ok(deletedCategory);
    }

    @GetMapping("/getAll")
    public PageableResponse<CategoryDTO> getAllCategory(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                        @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
                                                        @RequestParam(value = "sortOrder", defaultValue = "asc", required = false) String sortOrder) {
        return categoryService.getAllCategory(pageNo, pageSize, sortBy, sortOrder);
    }

    @GetMapping("/getCategoryById/{id}")
    public ResponseEntity<CategoryDTO> getOneById(@PathVariable("id") int id) {
        CategoryDTO categoryDTO = categoryService.getCategory(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping("/uploadImage/{id}")
    public ResponseEntity<ImageResponse> uploadImage(@PathVariable("id") int id, @RequestParam("cateGoryFile") MultipartFile file) throws IOException {
       String filename = categoryService.uploadImage(id,file);
        return ResponseEntity.ok(ImageResponse.builder().imageName(filename).message("Image uploaded").success(true).httpStatus(HttpStatus.OK).build());

    }
    @GetMapping("/getImage/{id}")
    public void getUploadedUserImage(@PathVariable("id") int id, HttpServletResponse resp) throws IOException {
        InputStream stream = categoryService.getUploadedImage(id);
        resp.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(stream,resp.getOutputStream());

    }
}
