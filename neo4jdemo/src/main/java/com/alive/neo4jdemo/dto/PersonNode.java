package com.alive.neo4jdemo.dto;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@Node(labels = "人物")
public class PersonNode {

    @Id
    @GeneratedValue
    private Long id;

    @Property("name")
    private String name;

}
