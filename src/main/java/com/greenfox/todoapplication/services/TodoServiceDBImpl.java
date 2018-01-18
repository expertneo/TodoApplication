package com.greenfox.todoapplication.services;

import com.greenfox.todoapplication.models.Assignee;
import com.greenfox.todoapplication.models.ToDo;
import com.greenfox.todoapplication.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceDBImpl implements TodoService {

  @Autowired
  TodoRepository todoRepository;

  //OK
  @Override
  public List<ToDo> getAllTodos() {
    List<ToDo> todos = new ArrayList<>();
    todoRepository.findAll().forEach(todos::add);
    return todos;
  }

  //OK
  @Override
  public ToDo getTodo(Integer id) {
    return todoRepository.findOne(id);
  }

  //OK
  @Override
  public void create(ToDo todo) {
    todoRepository.save(todo);
  }

  @Override
  public void setDone(Integer id, boolean input) {
    ToDo todo = todoRepository.findOne(id);
    todo.setIsDone(input);
    todoRepository.save(todo);
  }

  @Override
  public void setUrgent(Integer id, boolean input) {
    ToDo todo = todoRepository.findOne(id);
    todo.setIsUrgent(input);
    todoRepository.save(todo);
  }

  @Override
  public void delete(Integer id) {
    todoRepository.delete(id);
  }

  @Override
  public ToDo findByTitle(String title) {
    return todoRepository.findByTitle(title);
  }

  @Override
  public List<ToDo> findByIsUrgentFalse() {
    return todoRepository.findByIsUrgentFalse();
  }

  @Override
  public List<ToDo> findByIsUrgentTrue() {
    return todoRepository.findByIsUrgentTrue();
  }

  @Override
  public List<ToDo> findByIsDoneFalse() {
    return todoRepository.findByIsDoneFalse();
  }

  @Override
  public List<ToDo> findByIsDoneTrue() {
    return todoRepository.findByIsDoneTrue();
  }

  @Override
  public List<ToDo> findByAssignee(Assignee assignee) {
    return todoRepository.findByAssignee(assignee);
  }

  @Override
  public List<ToDo> findByAssigneeName(String input) {
    return todoRepository.findByAssigneeName(input);
  }

  @Override
  public void setTitle(Integer id, String string) {
    ToDo todo = todoRepository.findOne(id);
    todo.setTitle(string);
    todoRepository.save(todo);
  }
}
