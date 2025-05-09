package com.ebooksystem.ebookmanagementsystem.repositories;

import com.ebooksystem.ebookmanagementsystem.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for eBook categories. Extends JpaRepository to access its classes.
 */
public interface CategoryRepository extends JpaRepository<Category, Byte> {
}
