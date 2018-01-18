package com.greenfox.todoapplication.controllers;

import com.greenfox.todoapplication.models.Assignee;
import com.greenfox.todoapplication.models.ToDo;
import com.greenfox.todoapplication.services.TodoService;
import com.greenfox.todoapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {

  @Autowired
  TodoService todoService;

  @Autowired
  UserService userService;

  @GetMapping(value = {"/users/{userName}"})
  public String list(Model model, @PathVariable String userName) {
    List<ToDo> todos = todoService.findByAssigneeName(userName);
    model.addAttribute("todos", todos);
    model.addAttribute("userName", userName);
    return "index";
  }

  @GetMapping("/users/{userName}/add")
  public String showAdd(Model model,
                        @PathVariable String userName){
    ToDo todo = new ToDo();
    model.addAttribute("todo", todo);
    model.addAttribute("userName", userName);
    return "addTodo";
  }

  @PostMapping(value = {"/users/{userName}/add"})
  public ModelAndView add(@ModelAttribute ToDo todo,
                          @PathVariable String userName) {
    todo.setAssignee(userService.findOne(userName));
    todoService.create(todo);
    return new ModelAndView("redirect:/users/{userName}");
  }

  @RequestMapping(value = "/users/{userName}", params = {"delete"})
  public String deleteTodo(final HttpServletRequest request) {
    final Integer todoIndex = Integer.valueOf(request.getParameter("delete"));
    todoService.delete(todoIndex);
    return "redirect:/users/{userName}";
  }

  @GetMapping("/users/{userName}/edit/{id}")
  public String showEditToDo(Model model,
                             @PathVariable Integer id,
                             @PathVariable String userName){
    model.addAttribute("todo", todoService.getTodo(id));
    return "editTodo";
  }


  @PostMapping(value = {"/users/{userName}/edit/{id}/save"})
  public ModelAndView editToDo(@PathVariable Integer id,
                               @ModelAttribute ToDo todo,
                               @PathVariable String userName) {
    todo.setId(id);
    todo.setAssignee(userService.findOne(userName));
    todoService.create(todo);
    return new ModelAndView("redirect:/users/{userName}");
  }
}
