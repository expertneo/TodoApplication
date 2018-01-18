package com.greenfox.todoapplication.services;

import com.greenfox.todoapplication.models.Assignee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

  void register(Assignee assignee);
  Assignee login(Assignee assignee);
  Assignee getUserById(Integer id);
  Assignee findOne(String userName);
  List<Assignee> findAll();
}
