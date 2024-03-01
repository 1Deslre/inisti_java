package com.alive.neo4jdemo;

import org.junit.jupiter.api.Test;
import org.neo4j.driver.*;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.neo4j.driver.Values.parameters;

@SpringBootTest
class Neo4jdemoApplicationTests {

    @Value("${spring.neo4j.uri}")
    private String url; //= "bolt://192.168.91.132:7687"
    @Value("${spring.neo4j.authentication.username}")
    private String username; //= "neo4j"
    @Value("${spring.neo4j.authentication.password}")
    private String password; // = "200381"

    @Test
    void GetNode() {
        // 创建 Neo4j 驱动程序对象
        try (Driver driver = GraphDatabase.driver(url, AuthTokens.basic(username, password));
             Session session = driver.session()) {

            // 执行 Cypher 查询，检索两个节点之间的关系
//            String cypherQuery = "match (a:`人物`), (b:`人物`) c(c:`关系`) where a.name = '刘备' and b.name = '张飞'  return a,b";
            String cypherQuery = "match (a) - [c:`关系`{relationship: '兄长'}] - (b)  return a,c,b";
            Result result = session.run(cypherQuery);

            while (result.hasNext()) {
                Record record = result.next();
                Node nodeA = record.get("a").asNode();
                Node nodeB = record.get("b").asNode();
                Relationship relationship = record.get("c").asRelationship();

                System.out.println("Node A: " + nodeA.asMap());
                System.out.println("Node B: " + nodeB.asMap());
                System.out.println("relationship: " +relationship.asMap());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
