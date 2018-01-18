package com.greenfox.todoapplication.controllers;

import com.greenfox.todoapplication.models.Assignee;
import com.greenfox.todoapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class LoginController {

  UserService userservice;

  @Autowired
  public LoginController(UserService userService) {
    this.userservice = userService;
  }

  @GetMapping("/")
  public String showLogin(Model model) {
    model.addAttribute("user", new Assignee());
    return "login";
  }

  @PostMapping("/login")
  public ModelAndView handleLogin(Model model, @ModelAttribute Assignee assignee) {
    Assignee loggedInAssignee = userservice.login(assignee);
    model.addAttribute("user", loggedInAssignee);
    return new ModelAndView("redirect:/users/" + assignee.getName());
  }
}
