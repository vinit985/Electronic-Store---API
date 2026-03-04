package com.electronic.store.repositories;

import com.electronic.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo  extends JpaRepository<User,Integer> {

    User findByuserEmail(String email);
    List<User> findByuserNameContaining(String userName);
}
