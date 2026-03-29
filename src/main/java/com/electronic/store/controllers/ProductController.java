package com.electronic.store.controllers;

import com.electronic.store.dtos.APIResponseMessage;
import com.electronic.store.dtos.ImageResponse;
import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.dtos.ProductDTO;
import com.electronic.store.entities.Product;
import com.electronic.store.services.FileService;
import com.electronic.store.services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;
    @Value("${user.profile.image.path.product}")
    private String filePath;
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    @PostMapping("/create")
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.create(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
    @PostMapping("/create/categories/{categoryId}/products")
    public ResponseEntity<ProductDTO> createProductWithCategory(@Valid @RequestBody ProductDTO productDTO,@PathVariable("categoryId") int categoryId) {
        logger.info("Creating product with category inside createProductWithCategory method");
        ProductDTO createdProduct = productService.createProductWithCategory(productDTO,categoryId);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
    @PutMapping("/update/category/{categoryId}/product/{productId}")
    public ResponseEntity<ProductDTO> updateProductWithCategoryId(@PathVariable("categoryId") int categoryId,@PathVariable("productId") int productId)
    {
       return new ResponseEntity<>(productService.updateCategoryOfExistingProduct(productId,categoryId),HttpStatus.ACCEPTED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable("id") int id, @RequestBody ProductDTO productDTO) {
        ProductDTO productDTO1 = productService.update(id, productDTO);
        return new ResponseEntity<>(productDTO1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponseMessage> delete(@PathVariable("id") int id) throws IOException {
        productService.delete(id,filePath);
        return new ResponseEntity<>(APIResponseMessage.builder()
                .message("Product deleted successfully with id: " + id)
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build(), HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable("id") int id) {
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<PageableResponse<ProductDTO>> getAll(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                               @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                               @RequestParam(value = "sortOrder", defaultValue = "ASC", required = false) String sortOrder,
                                                               @RequestParam(value = "sortBy", defaultValue = "price", required = false) String sortBy) {
        PageableResponse<ProductDTO> pageableResponse = productService.getAll(pageNo, pageSize, sortOrder, sortBy);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }


    @GetMapping("/getAllLive")
    public ResponseEntity<PageableResponse<ProductDTO>> getAllLive(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                                   @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                                   @RequestParam(value = "sortOrder", defaultValue = "ASC", required = false) String sortOrder,
                                                                   @RequestParam(value = "sortBy", defaultValue = "price", required = false) String sortBy) {
        PageableResponse<ProductDTO> pageableResponse = productService.getAllLive(pageNo, pageSize, sortOrder, sortBy);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }
    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ProductDTO>> searchProduct(
                                                                   @PathVariable("query") String query,
                                                                   @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                                   @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                                   @RequestParam(value = "sortOrder", defaultValue = "ASC", required = false) String sortOrder,
                                                                   @RequestParam(value = "sortBy", defaultValue = "price", required = false) String sortBy) {
        PageableResponse<ProductDTO> pageableResponse = productService.searchByTitle(query,pageNo, pageSize, sortOrder, sortBy);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }
    //Upload Image to product path
    @PostMapping("/uploadImage/{productId}")
    public ResponseEntity<ImageResponse> uploadImage(@PathVariable("productId") int productid,@RequestParam("file") MultipartFile file) throws IOException {
            String fileName = fileService.uploadImage(file, filePath);
            logger.info("File name inside uploadImage after uploading {}",fileName);
            ProductDTO productDTO = productService.getById(productid);
            productDTO.setProductImageName(fileName);
           ProductDTO savedDTO =  productService.update(productid,productDTO);
            logger.info("product title : {}",savedDTO.getProductImageName());
            ImageResponse imageResponse = ImageResponse.builder().imageName(fileName).success(true).message("Image uploaded successfully").httpStatus(HttpStatus.CREATED).build();
            return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);

    }
    //Serve Image to Users
    @GetMapping("/getImage/{id}")
    public void getProducImage(@PathVariable("id") int productId, HttpServletResponse response) throws IOException {
         ProductDTO productDTO = productService.getById(productId);
         String imageName = productDTO.getProductImageName();
         InputStream inputStream = fileService.getResource(filePath,imageName);
         response.setContentType(MediaType.IMAGE_JPEG_VALUE);
         StreamUtils.copy(inputStream,response.getOutputStream());
    }


}
