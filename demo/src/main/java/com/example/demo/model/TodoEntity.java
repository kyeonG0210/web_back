package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

// lombok이 제공하는 어노테이션
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="todo") // 이 엔티티는 데이터베이스의 Todo 테이블에 매핑됨

public class TodoEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid") //deprecated
    private String id; //primary key; 이 오브젝트의 아이디
    private String userId; // 이 오브젝트를 생성한 유저의 아이디
    private String title; // Todo 타이틀 예) 운동하기
    private String author;
    private String publisher;
    public boolean done; // true - todo를 완료한 경우(checked)
}
