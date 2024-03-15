package com.example.springbootweb.utils;

import com.example.springbootweb.entity.Entity;

import java.util.List;

public class EntityUtils {

    public static Entity findPrevNonEmptyEntity(List<Entity> entities, int startIndex) {
        for (int i = startIndex; i >= 0; i--) {
            Entity entity = entities.get(i);
            if (entity.getField() != null && !entity.getField().isEmpty()) {
                return entity;
            }
        }
        return null;
    }

    public static Entity findNextNonEmptyEntity(List<Entity> entities, int startIndex) {
        for (int i = startIndex; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.getField() != null && !entity.getField().isEmpty()) {
                return entity;
            }
        }
        return null;
    }

    public static void findEmptyEntities(List<Entity> entities) {
        System.out.println("\n找到空的实体类前一个和后一个非空的实体类：");
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.getField() == null || entity.getField().isEmpty()) {
                Entity prevEntity = findPrevNonEmptyEntity(entities, i - 1);
                Entity nextEntity = findNextNonEmptyEntity(entities, i + 1);
                if (prevEntity != null && nextEntity != null) {
                    System.out.println("空的实体类：" + entity + "，前一个非空的实体类：" + prevEntity + "，后一个非空的实体类：" + nextEntity);
                } else {
                    System.out.println("空的实体类：" + entity + "，前一个或后一个非空的实体类不存在");
                }
            }
        }
    }
}
