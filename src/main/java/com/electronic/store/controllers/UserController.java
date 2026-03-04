package com.electronic.store.controllers;

import com.electronic.store.dtos.APIResponseMessage;
import com.electronic.store.dtos.ImageResponse;
import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.dtos.UserDTO;
import com.electronic.store.services.FileService;
import com.electronic.store.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Value("${user.profile.image.path}")
    String FILE_PATH;
    Logger logger  = LoggerFactory.getLogger(UserController.class);
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO savedDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") int id,
                                              @Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(id, userDTO), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<APIResponseMessage> deleteUser(@PathVariable("userID") int id) throws IOException {
        userService.deleteUser(id);
       APIResponseMessage msg =  APIResponseMessage.builder()
                .message("User deleted successfully with id :" + id)
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<PageableResponse<UserDTO>> getAllUsers(
            @RequestParam(value = "PageNo" ,required = false,defaultValue = "0") int pageNo,
            @RequestParam(value = "PageSize",required = false,defaultValue = "10") int pageSize,
            @RequestParam(value = "SortBy",required = false,defaultValue = "userName") String sortBy,
            @RequestParam(value = "SortOrder",required = false,defaultValue = "ASC") String sortOrder
    ) {
        return new ResponseEntity<>(userService.getAllUser(pageNo,pageSize,sortBy,sortOrder), HttpStatus.OK);
    }

    @GetMapping("getByUserId/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/getByUserEmail/{userEmail}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable("userEmail") String email) {
        return new ResponseEntity<>(userService.getSingleUserByEmail(email), HttpStatus.OK);
    }
    @GetMapping("/getByUserName/{userName}")
    public ResponseEntity<List<UserDTO>> getUserByName(@PathVariable("userName") String name) {
        return new ResponseEntity<>(userService.searchByName(name), HttpStatus.OK);
    }
    @PostMapping("/uploadImage/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("userImage")MultipartFile file,
                                                         @PathVariable("userId") int id) throws IOException {
        String imageName = fileService.uploadImage(file, FILE_PATH);
        UserDTO user = userService.getUserById(id);
        user.setUserImageName(imageName);
        UserDTO userDTO = userService.updateUser(id, user);
        ImageResponse resp = ImageResponse.builder()
                .imageName(imageName)
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .message("Image Uploaded")
                .build();
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }
    @GetMapping("/getImage/{userId}")
    public void getUserUploadedImage(@PathVariable("userId") int id, HttpServletResponse response) throws IOException {
        logger.info("User id eneterd by user {}",id);
        UserDTO userDTO = userService.getUserById(id);
        String imageName = userDTO.getUserImageName();
        logger.info("User image name in database {}",imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        InputStream inputStream =  fileService.getResource(FILE_PATH,imageName);
/*        StringUtil ,We are using this to copy image to our response*/
        StreamUtils.copy(inputStream,response.getOutputStream());
    }


}
