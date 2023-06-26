package com.react.reactfullstack.repository;

import com.react.reactfullstack.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository <Task,Long>{
}
