package com.practice.UserProductService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "User Already Exist !!!!!!11")
public class UserAlreadyExistException extends Exception{
}
