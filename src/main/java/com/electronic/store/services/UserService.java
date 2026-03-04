package com.electronic.store.services;

import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.dtos.UserDTO;

import java.io.IOException;
import java.util.List;

public interface UserService {



    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(int id , UserDTO userDTO);
    void deleteUser(int UserId) throws IOException;
    PageableResponse<UserDTO> getAllUser(int pageNo, int pageSize, String sortBy, String sortOrder);
    UserDTO getUserById(int id);
    UserDTO getSingleUserByEmail(String email);
    List<UserDTO> searchByName(String userName);





}
