package com.practice.UserProductService.service;

import com.practice.UserProductService.domain.Product;
import com.practice.UserProductService.domain.User;
import com.practice.UserProductService.exception.ProductAlreadyExistException;
import com.practice.UserProductService.exception.ProductNotFoundException;
import com.practice.UserProductService.exception.UserAlreadyExistException;
import com.practice.UserProductService.exception.UserNotFoundException;
import com.practice.UserProductService.rabbitMq.CommonUser;

import java.util.List;

public interface UserService {

    User addUser(User user) throws UserAlreadyExistException;

    User addUser1(CommonUser commonUser);
    User addProductForUser(int userId, Product product) throws ProductAlreadyExistException;

    List<User> getAllProducts() throws ProductNotFoundException;

    public User deleteUserAndProduct(int userId,int productId) throws UserNotFoundException,ProductNotFoundException;
}
