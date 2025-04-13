package com.example.demo.dto;

import com.example.demo.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class TodoDTO {
    private String id;
    private String userId;
    private String title;
    private String author;
    private String publisher;
    private boolean done;

    public TodoDTO(final TodoEntity todoEntity) {
        this.id = todoEntity.getId();
        this.userId = todoEntity.getUserId();
        this.title = todoEntity.getTitle();
        this.author = todoEntity.getAuthor();
        this.publisher = todoEntity.getPublisher();
        this.done = todoEntity.isDone();
    }

    public static TodoEntity toEntity(final TodoDTO dto) {
        return TodoEntity.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .publisher(dto.getPublisher())
                .done(dto.isDone())
                .build();
    }
}
