package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ResponseDTO<T> {
    private String error;
    private List<T> data;
}
