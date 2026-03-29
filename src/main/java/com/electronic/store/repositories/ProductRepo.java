package com.electronic.store.repositories;

import com.electronic.store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    //SearchByTitle
    Page<Product> findByTitleContaining(String subtitle,Pageable pageable);
    Page<Product> findByLiveTrue(Pageable pageable);
}
