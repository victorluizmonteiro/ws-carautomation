package com.fiap.carautomation.repository;

import com.fiap.carautomation.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
