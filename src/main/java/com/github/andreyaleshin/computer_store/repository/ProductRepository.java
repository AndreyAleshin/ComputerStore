package com.github.andreyaleshin.computer_store.repository;

import com.github.andreyaleshin.computer_store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    List<Product> findAllByOrderByIdAsc();

}
