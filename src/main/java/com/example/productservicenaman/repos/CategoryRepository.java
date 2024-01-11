package com.example.productservicenaman.repos;

import com.example.productservicenaman.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
