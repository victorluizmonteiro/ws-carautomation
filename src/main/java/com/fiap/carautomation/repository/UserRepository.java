package com.fiap.carautomation.repository;

import com.fiap.carautomation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
