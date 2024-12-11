package com.adepuu.fezz4ubackend.infrastructure.events.repository;

import com.adepuu.fezz4ubackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
