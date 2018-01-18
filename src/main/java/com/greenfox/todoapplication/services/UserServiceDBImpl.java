package com.greenfox.todoapplication.services;

import com.greenfox.todoapplication.models.Assignee;
import com.greenfox.todoapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceDBImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public Assignee login(Assignee assigneeInput) {
    List<Assignee> lista = (List<Assignee>) userRepository.findAll();
    if (lista.size() == 0) {
      register(assigneeInput);
    } else {
      for (Assignee assignee : userRepository.findAll()) {
        if (!assignee.getName().equals(assigneeInput.getName())) {
          register(assigneeInput);
        }
      }
    }
    return assigneeInput;
  }

  //OK
  @Override
  public void register(Assignee assignee) {
    userRepository.save(assignee);
  }

  //OK
  @Override
  public List<Assignee> findAll() {
    List<Assignee> assignees = new ArrayList<>();
    userRepository.findAll().forEach(assignees::add);
    return assignees;
  }

  //OK
  @Override
  public Assignee findOne(String userName) {
    for (Assignee assignee : userRepository.findAll()) {
      if (assignee.getName().equals(userName)) {
        return assignee;
      }
    }
    Assignee assignee = new Assignee();
    assignee.setName(userName);
    register(assignee);
    return assignee;
  }

  //OK
  @Override
  public Assignee getUserById(Integer id) {
    return userRepository.findOne(id);
  }
}
