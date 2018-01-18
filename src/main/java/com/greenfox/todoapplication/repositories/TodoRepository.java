package com.greenfox.todoapplication.repositories;

import com.greenfox.todoapplication.models.Assignee;
import com.greenfox.todoapplication.models.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<ToDo, Integer>{
  ToDo findByTitle(String title);
  List<ToDo> findByIsUrgentFalse();
  List<ToDo> findByIsUrgentTrue();
  List<ToDo> findByIsDoneFalse();
  List<ToDo> findByIsDoneTrue();
  List<ToDo> findByAssignee(Assignee assignee);
  List<ToDo> findByAssigneeName(String input);
}
