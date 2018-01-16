package com.greenfox.todoapplication.controllers;

import com.greenfox.todoapplication.models.ToDo;
import com.greenfox.todoapplication.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class TodoController {

  @Autowired
  TodoService todoService;

  @GetMapping(value = {"/", "/list"})
  public String list(Model model,
                     @RequestParam(value = "isActive", required = false) Boolean parameter) {
    if (parameter == null) {
      List<ToDo> todos = todoService.getAllTodos();
      model.addAttribute("todos", todos);
    } else if (parameter) {
      List<ToDo> todos = todoService.getAllTodos();
      List<ToDo> filteredTodos = new ArrayList<>();
      for (int i = 0; i < todos.size(); i++) {
        if (!todos.get(i).getIsDone()) {
          filteredTodos.add(todos.get(i));
        }
      }
      model.addAttribute("todos", filteredTodos);
    } else {
      List<ToDo> todos = todoService.getAllTodos();
      List<ToDo> filteredTodos = new ArrayList<>();
      for (int i = 0; i < todos.size(); i++) {
        if (todos.get(i).getIsDone()) {
          filteredTodos.add(todos.get(i));
        }
      }
      model.addAttribute("todos", filteredTodos);
    }
    return "index";
  }

  @GetMapping("/add")
  public String showAdd(Model model){
    ToDo todo = new ToDo();
    model.addAttribute("todo", todo);
    return "addTodo";
  }

  @PostMapping(value = {"/add"})
  public ModelAndView add(@ModelAttribute ToDo todo) {
    todoService.create(todo);
    return new ModelAndView("redirect:/");
  }

  @RequestMapping(value = "/", params = {"delete"})
  public String deleteTodo(final HttpServletRequest request) {
    final Integer todoIndex = Integer.valueOf(request.getParameter("delete"));
    todoService.delete(todoIndex);
    return "redirect:/";
  }

  @GetMapping("/")
  public String showEditToDo(Model model,
                        @PathVariable(value = "edit") Integer id){
    model.addAttribute("todo", todoService.getTodo(id));
    return "editTodo";
  }


  @PostMapping(value = {"/edit"})
  public ModelAndView editToDo(@PathVariable(value = "edit") Integer id,
                               @ModelAttribute ToDo todo) {
    todo.setId(id);
    todoService.create(todo);
    return new ModelAndView("redirect:/");
  }
}
