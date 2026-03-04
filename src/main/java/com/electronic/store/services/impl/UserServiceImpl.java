package com.electronic.store.services.impl;

import com.electronic.store.Helper.PageableResponseImpl;
import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.dtos.UserDTO;
import com.electronic.store.entities.User;
import com.electronic.store.exception.ResourceNotFoundException;
import com.electronic.store.repositories.UserRepo;
import com.electronic.store.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SuppressWarnings("UnnecessaryLocalVariable")
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Value("${user.profile.image.path}")
    String FILE_PATH;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
//        Generating random userID
        User user = convertDtoToEntity(userDTO);
        User savedUser = userRepo.save(user);
        return convertEntityToDTO(savedUser);

    }

    private UserDTO convertEntityToDTO(User savedUser) {
       /* UserDTO userDTO = UserDTO.builder()
                .userId(savedUser.getUserId())
                .userName(savedUser.getUserName())
                .userEmail(savedUser.getUserEmail())
                .userPassword(savedUser.getUserPassword())
                .userGender(savedUser.getUserGender())
                .userAbout(savedUser.getUserAbout())
                .userImageName(savedUser.getUserImageName())
                .build();
        return userDTO;*/
        return modelMapper.map(savedUser, UserDTO.class);
    }

    private User convertDtoToEntity(UserDTO userDTO) {

        return modelMapper.map(userDTO, User.class);

    }

    @Override
    public UserDTO updateUser(int id, UserDTO userDTO) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with given id."));
        user.setUserName(userDTO.getUserName());
        user.setUserEmail(userDTO.getUserEmail());
        user.setUserPassword(userDTO.getUserPassword());
        user.setUserGender(userDTO.getUserGender());
        user.setUserAbout(userDTO.getUserAbout());
        user.setUserImageName(userDTO.getUserImageName());
        User savedUser = userRepo.save(user);
        UserDTO savedUserDTO = convertEntityToDTO(savedUser);
        return savedUserDTO;
    }

    @Override
    public void deleteUser(int userId) throws IOException {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));
        String imageName = user.getUserImageName();
        String path = FILE_PATH+imageName;
        Files.deleteIfExists(Paths.get(path));
        userRepo.delete(user);
    }

    @Override
    public PageableResponse<UserDTO> getAllUser(int pageNo, int pageSize, String sortBy, String sortOrder) {
//        Creating object of Sort
        Sort sort = (sortOrder.equalsIgnoreCase("ASC")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());
//        PageRequest has implementation of Pageable interface so we can create object of Pageable from  this
        Pageable pageable =  PageRequest.of(pageNo,pageSize,sort);

        Page<User> listOfUserPageable =  userRepo.findAll(pageable);
        PageableResponse<UserDTO> object = new PageableResponseImpl().getPageableResponse(listOfUserPageable, UserDTO.class);
        return object;


    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));
        return convertEntityToDTO(user);

    }

    @Override
    public UserDTO getSingleUserByEmail(String email) {
        User user = userRepo.findByuserEmail(email);
        UserDTO userDTOFetched = convertEntityToDTO(user);
        return userDTOFetched;
    }

    @Override
    public List<UserDTO> searchByName(String userName) {
        List<User> listUser = userRepo.findByuserNameContaining(userName);
        List<UserDTO> listUserDTO = listUser.stream().map(this::convertEntityToDTO).toList();
        return listUserDTO;
    }
}
