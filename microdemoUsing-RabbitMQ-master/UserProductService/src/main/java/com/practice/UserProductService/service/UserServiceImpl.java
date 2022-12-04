package com.practice.UserProductService.service;

import com.practice.UserProductService.domain.Product;
import com.practice.UserProductService.domain.User;
import com.practice.UserProductService.exception.ProductAlreadyExistException;
import com.practice.UserProductService.exception.ProductNotFoundException;
import com.practice.UserProductService.exception.UserAlreadyExistException;
import com.practice.UserProductService.exception.UserNotFoundException;
import com.practice.UserProductService.rabbitMq.CommonUser;
import com.practice.UserProductService.rabbitMq.Producer;
import com.practice.UserProductService.rabbitMq.UserDTO;
import com.practice.UserProductService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    private Producer producer;


    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User addUser(User user) throws UserAlreadyExistException {
        if(userRepository.findById(user.getUserId()).isPresent()){
            throw new UserAlreadyExistException();
        }
        return userRepository.insert(user);
    }

    @Override
    public User addUser1(CommonUser commonUser) {
        UserDTO userDTO=new UserDTO(commonUser.getUserId(),commonUser.getPassword());
        producer.sendDtoToQueue(userDTO);
        User user=new User(commonUser.getUserId(),new ArrayList<>(),commonUser.getUserName(),commonUser.getEmail(),commonUser.getMobileNo());

        return userRepository.insert(user);
    }

    @Override
    public User addProductForUser(int userId, Product product) throws ProductAlreadyExistException {
        if(userRepository.findById(product.getProductId()).isPresent()) {
            throw new ProductAlreadyExistException();
        }
        User user = userRepository.findByUserId(userId);

        if(user.getProductsList()==null){
            user.setProductList(Arrays.asList(product));
        }else{
            List<Product> products=user.getProductsList();
            products.add(product);
            user.setProductList(products);
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllProducts() throws ProductNotFoundException {
        return userRepository.findAll();
    }

    @Override
    public User deleteUserAndProduct(int userId,int productId) throws UserNotFoundException,ProductNotFoundException {
        boolean result;
        if(userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user=userRepository.findById(userId).get();
        List<Product> productList=user.getProductsList();
        result=productList.removeIf(obj->obj.getProductId()==productId);

        if(!result){throw  new ProductNotFoundException();}

        user.setProductList(productList);
        return userRepository.save(user);
    }
}
