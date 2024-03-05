package com.example.springbootweb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class TestD {

    private String msg;
    private Integer code;
    private List<DataDTO> data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        private String id;
        private DetailDTO detail;

        @NoArgsConstructor
        @Data
        public static class DetailDTO {
        }
    }
}
