package com.alive.neo4jdemo.result;

import lombok.Data;

@Data
public class Results<T> {

    private Boolean success;
    private T data;

    public static <T> Results<T> ok( T data) {
        Results<T> result = new Results<>();
        if (data != null) {
            result.setData(data);
        }
        result.setSuccess(true);
        return result;
    }

    public static <T> Results<T> ok() {
        Results<T> result = new Results<>();
        result.setSuccess(true);
        return result;
    }

    public static <T> Results<T> fail() {
        Results<T> result = new Results<>();
        result.setSuccess(false);
        return result;
    }

}
