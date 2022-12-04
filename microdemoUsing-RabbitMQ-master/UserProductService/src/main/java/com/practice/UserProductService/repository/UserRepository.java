package com.practice.UserProductService.repository;

import com.practice.UserProductService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Integer> {

    User findByUserId(int userId);
}
