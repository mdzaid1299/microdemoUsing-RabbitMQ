package com.practice.UserProductService.controller;

import com.practice.UserProductService.domain.Product;
import com.practice.UserProductService.domain.User;
import com.practice.UserProductService.exception.ProductAlreadyExistException;
import com.practice.UserProductService.exception.ProductNotFoundException;
import com.practice.UserProductService.exception.UserAlreadyExistException;
import com.practice.UserProductService.exception.UserNotFoundException;
import com.practice.UserProductService.rabbitMq.CommonUser;
import com.practice.UserProductService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/userproductservice/v1/")
public class UserController {

    ResponseEntity responseEntity;

    @Autowired
    private UserService userService;


    @PostMapping("/register/")
    public ResponseEntity<?> insertUser(@RequestBody User user) throws UserAlreadyExistException {
        try {
            user.setProductList(new ArrayList<>());
            User user1 = userService.addUser(user);
            responseEntity = new ResponseEntity<>(user1, HttpStatus.OK);
        }catch (UserAlreadyExistException e){
            throw new UserAlreadyExistException();
        }
        return responseEntity;
    }

    @PostMapping("/common")
    public ResponseEntity<?> adduserDetails(@RequestBody CommonUser commonUser){

        return new ResponseEntity<>(userService.addUser1(commonUser),HttpStatus.OK);
    }

    @PutMapping("/user/addProduct/{userId}")
    public ResponseEntity<?> addProductForUser(@PathVariable(value = "userId") int userId, @RequestBody Product product) throws ProductAlreadyExistException {

        try{
            User user1=userService.addProductForUser(userId, product);
            responseEntity=new ResponseEntity<>(user1,HttpStatus.OK);

        } catch (ProductAlreadyExistException e) {
            throw new ProductAlreadyExistException();
        }

        return responseEntity;
    }

    @GetMapping("/user/products/")
    public ResponseEntity<?> fetchAllProducts()
    {
       // ResponseEntity responseEntity = null;
        try {
            responseEntity= new ResponseEntity<>(userService.getAllProducts(),HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return responseEntity;
    }

    @DeleteMapping("/user/product/{userId}/{productId}")
    public ResponseEntity<?> deleteSingleUser(@PathVariable(value = "userId" ) int userId ,@PathVariable(value = "productId") int productId) throws UserNotFoundException {


        try{
            responseEntity = new ResponseEntity<>(userService.deleteUserAndProduct(userId,productId), HttpStatus.OK);

        }catch (UserNotFoundException e){

            throw new UserNotFoundException();

        }catch (Exception e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
