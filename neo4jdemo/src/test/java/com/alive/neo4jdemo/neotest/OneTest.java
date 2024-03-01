package com.alive.neo4jdemo.neotest;

import com.alive.neo4jdemo.dto.PersonNode;
import com.alive.neo4jdemo.dto.RelationshipNode;
import com.alive.neo4jdemo.entity.Nodes;
import com.alive.neo4jdemo.result.Results;
import com.alive.neo4jdemo.utils.NeoUtil;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;

import java.util.List;

import static com.alive.neo4jdemo.utils.NeoUtil.*;

public class OneTest {

    public static void main(String[] args) {


//        List<PersonNode> nodeList = GetNodeList(PersonNode.class, "人物").getData();
//        for (PersonNode personNode : nodeList) {
//            System.out.println("personNode = " + personNode);
//        }
//        String title = "人物";
//        PersonNode personNode = new PersonNode();
//        personNode.setName("刘备");
//
//        List<PersonNode> data = GetPremiseNodeList(PersonNode.class, title, personNode).getData();
//        for (PersonNode datum : data) {
//            System.out.println("datum = " + datum);
//        }

//        List<RelationshipNode<Node, Node>> relationshipNodes = GetNotRelationship("关系", "对手").getData();
//        for (RelationshipNode<Node, Node> relationshipNode : relationshipNodes) {
//            Node startNode = relationshipNode.getStartNode();
//            System.out.println("startNode = " + startNode.asMap());
//            Node endNode = relationshipNode.getEndNode();
//            System.out.println("endNode = " + endNode.asMap());
//            Relationship relationship = relationshipNode.getRelationship();
//            System.out.println("relationship = " + relationship.asMap());
//        }


//        List<PersonNode> data = GetPremiseNodeList(PersonNode.class, title, personNode).getData();
//        for (PersonNode datum : data) {
//            System.out.println("datum = " + datum);
//        }


    }
}
