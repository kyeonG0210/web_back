package com.example.demo.persistence;

import com.example.demo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Component 어노테이션 포함
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    // 자동으로 save(), delete(), findByID() 메소드 등이 생성됨

    // @Query(value = "select * from Todo t where t.userId = ?1") : @Query 직접 지정하는 방식
    public List<TodoEntity> findByUserId(String userID);

    List<TodoEntity> findByIdAndTitle(String id, String title);
    List<TodoEntity> findByTitle(String title); // 자동으로 생성 안 해주므로 선언해야함
}
