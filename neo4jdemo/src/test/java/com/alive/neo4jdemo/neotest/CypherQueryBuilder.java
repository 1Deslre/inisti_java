package com.alive.neo4jdemo.neotest;

import com.alive.neo4jdemo.entity.Nodes;
import com.alive.neo4jdemo.entity.Relationships;

import java.lang.reflect.Field;

public class CypherQueryBuilder {

    public static <T> String buildWhereClause(T entity) {
        Class<?> clazz = entity.getClass();
        StringBuilder whereClause = new StringBuilder();
        // 获取实体类的所有字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 排除静态字段
            if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true); // 设置字段可访问
                try {
                    Object value = field.get(entity);
                    if (value != null) {
                        if (field.getType() == String.class) {
                            whereClause.append(field.getName()).append(" = '").append(value).append("' AND ");
                        } else {
                            whereClause.append(field.getName()).append(" = ").append(value).append(" AND ");
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // 移除最后一个AND
        if (whereClause.length() > 0) {
            whereClause.setLength(whereClause.length() - 5);
        }

        return whereClause.toString();
    }

    public static void main(String[] args) {
        // 示例：创建一个Nodes对象，并设置部分字段的值
        Nodes entity = new Nodes();
        entity.setId(1);
        entity.setName("John");

        Relationships relationships = new Relationships();
        relationships.setEndNodeId(92);
        relationships.setRelationshipType("3333");

        // 构建where子句
        String whereClause = buildWhereClause(relationships);
        System.out.println("WHERE子句：" + whereClause);
    }
}
