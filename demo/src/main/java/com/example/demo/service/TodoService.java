package com.example.demo.service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service // Bean
public class TodoService {
    @Autowired // 알아서 Bean을 찾아서 그 빈을 인스턴스 멤버변수에 연결 (의존성 주입)
    private TodoRepository repository;
    public String testService() {
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();

        repository.save(entity);

        TodoEntity savedEntity = repository.findById(entity.getId()).get();

        return savedEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity entity) {
        validate(entity);

        repository.save(entity);

        log.info("Entity id: {} created", entity.getId());

        return repository.findByUserId(entity.getUserId());
    }

    private void validate(TodoEntity entity) {
        if(entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");

        }

        if(entity.getUserId() == null) {
            log.warn("Entity.getUserID cannot be null");
            throw new RuntimeException("Entity.getUserID cannot be null");
        }
    }

    public List<TodoEntity> retrieveByTitle(final String title) { // title로 검색하는 메소드 (GET)
        if (title == null || title.isEmpty()) {
            return repository.findAll(); // title 없으면 전체 조회
        }
        return repository.findByTitle(title);
    }

    public List<TodoEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }

    public List<TodoEntity> update(TodoEntity entity) {
        validate(entity);
        final TodoEntity original = repository.getById(entity.getId());

        original.setTitle(entity.getTitle());
        original.setDone(entity.isDone());
        original.setAuthor(entity.getAuthor());
        original.setPublisher(entity.getPublisher());

        repository.save(original);

        return retrieve(entity.getUserId());
    }

    public List<TodoEntity> delete(final TodoEntity entity) {
        validate(entity);
        try {
            repository.delete(entity);
        } catch (Exception e) {
            log.error("error deleting entity", entity.getId(),e);

            throw new RuntimeException("error deleting entity" + entity.getId());
        }
            return retrieve(entity.getUserId());
    }
}
