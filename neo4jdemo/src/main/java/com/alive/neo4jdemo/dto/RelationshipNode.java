package com.alive.neo4jdemo.dto;

import lombok.Data;
import org.neo4j.driver.types.Relationship;

@Data
public class RelationshipNode<S, E> {

    private S startNode;

    private Relationship relationship;

    private E endNode;

}
