package com.practice.UserAuthenticationService.rabbitmqreciver;

import com.practice.UserAuthenticationService.domain.User;
import com.practice.UserAuthenticationService.exception.UserAlreadyExistException;
import com.practice.UserAuthenticationService.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
   private UserService userService;

    @RabbitListener(queues = "user_queue")
    public void getDtoFromQueueAndAddToDb(UserDTO userDTO) throws UserAlreadyExistException {

        User user=new User();
        user.setUserId(userDTO.getUserId());
        user.setPassword(userDTO.getPassword());

        User user1=userService.addUser(user);
        System.out.println("result= "+user1);

    }
}
