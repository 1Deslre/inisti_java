package com.alive.neo4jdemo.utils;

import com.alive.neo4jdemo.dto.PersonNode;
import com.alive.neo4jdemo.dto.RelationNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.alive.neo4jdemo.utils.NeoUtil.*;

class NeoUtilTest {


    @Test
    void saveNodeTest() {

        PersonNode personNode = new PersonNode();
        personNode.setName("吕布");
        SaveNode(personNode, "人物");

    }

    @Test
    void SaveListNodeTest() {
        PersonNode p1 = new PersonNode();
        PersonNode p2 = new PersonNode();
        PersonNode p3 = new PersonNode();
        List<PersonNode> list = new ArrayList<>();
        p1.setName("p1");
        p2.setName("p2");
        p3.setName("p3");
        list.add(p1);
        list.add(p2);
        list.add(p3);
        SaveListNode(list, "人物");
    }

    @Test
    void SaveLeftRelationNodeTest() {
        PersonNode p1 = new PersonNode();
        PersonNode p2 = new PersonNode();
        RelationNode node = new RelationNode();
        p1.setName("貂蝉");
        node.setRelationship("酷酷酷");
        p2.setName("吕布");
        String title = "人物";

        SaveLeftRelationNode(p2, title, node, "关系", p1, title);

    }
}