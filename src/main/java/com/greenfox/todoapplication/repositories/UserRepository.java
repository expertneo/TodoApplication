package com.greenfox.todoapplication.repositories;

import com.greenfox.todoapplication.models.Assignee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<Assignee, Integer> {
}
