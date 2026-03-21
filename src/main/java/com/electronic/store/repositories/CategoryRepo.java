package com.electronic.store.repositories;

import com.electronic.store.dtos.CategoryDTO;
import com.electronic.store.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepo extends JpaRepository<Category,Integer> {


}
