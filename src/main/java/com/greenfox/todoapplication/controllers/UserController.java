package com.greenfox.todoapplication.controllers;

import com.greenfox.todoapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

  UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }
}
