package com.devsuperior.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dscatalog.entities.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    Optional<Category> findByName(String name);
}
