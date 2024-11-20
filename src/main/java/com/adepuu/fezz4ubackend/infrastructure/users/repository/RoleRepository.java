package com.adepuu.fezz4ubackend.infrastructure.users.repository;

import com.adepuu.fezz4ubackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(String name);

  @Query("SELECT r FROM Role r WHERE r.name = ?1")
  Role findByNameQuery(String name);
}