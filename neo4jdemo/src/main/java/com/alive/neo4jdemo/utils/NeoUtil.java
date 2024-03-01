package com.alive.neo4jdemo.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.alive.neo4jdemo.dto.PersonNode;
import com.alive.neo4jdemo.dto.RelationshipNode;
import com.alive.neo4jdemo.result.Results;
import org.neo4j.driver.*;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NeoUtil {

    private final static String url = "bolt://192.168.91.132:7687";
    private final static String username = "neo4j";
    private final static String password = "200381";

    private final static Driver driver;
    private final static Session session;

    static {
        driver = GraphDatabase.driver(url, AuthTokens.basic(username, password));
        session = driver.session();
    }

    /**
     * 根据传入进来的类型查找出所有节点
     *
     * @param type  返回的实体类型
     * @param title 查找的类型
     * @return 返回节点的一个集合
     */
    public static <R> Results<List<R>> GetNodeList(Class<R> type, String title) {
        if (isEmpty(type) || isEmpty(title)) {
            return Results.fail();
        }
        Record record;
        String url;
        List<R> list = new ArrayList<>();
        try {
            url = "match (n:`" + title + "`) return n";
            Result result = session.run(url);
            while (result.hasNext()) {
                record = result.next();
                Node node = record.get("n").asNode();
                R r = BeanUtil.toBean(node.asMap(), type);
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Results.ok(list);
    }

    /**
     * 根据传入进来的类型和对应的参数条件查找出所有节点
     *
     * @param type    返回的实体类型
     * @param title   要查找的对应的类型
     * @param premise 参数条件
     * @return 返回符合参数条件的节点集合
     */
    public static <R> Results<List<R>> GetPremiseNodeList(Class<R> type, String title, R premise) {
        if (isEmpty(type) || isEmpty(title) || isEmpty(premise)) {
            return Results.fail();
        }
        Record record;
        String url;
        List<R> list = new ArrayList<>();
        try {
            String s = " where " + buildWhereClause(premise);
            url = "match (n:`" + title + "`) " + s + "  return n";
            Result result = session.run(url);
            while (result.hasNext()) {
                record = result.next();
                Node node = record.get("n").asNode();
                R r = BeanUtil.toBean(node.asMap(), type);
                list.add(r);
            }
            System.out.println("title = " + title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Results.ok(list);
    }

    /**
     * 根据关系和条件获取双方节点
     *
     * @param premise 条件参数
     * @param title   关系类型
     */
    public static <R> Results<List<RelationshipNode<Node, Node>>> GetRelationship(R premise, String title) {
        if (isEmpty(premise) || isEmpty(title)) {
            return Results.fail();
        }
        List<RelationshipNode<Node, Node>> list = new ArrayList<>();
        RelationshipNode<Node, Node> node;
        try {

            String s = buildClause(premise);

            String url = " match (a) - [c:`" + title + "`{" + s + "}] - (b)  return a,c,b";
            Result result = session.run(url);
            Node nodeA;
            Node nodeB;
            Record record;
            Relationship relationship;
            while (result.hasNext()) {
                node = new RelationshipNode<>();
                record = result.next();
                nodeA = record.get("a").asNode();
                nodeB = record.get("b").asNode();
                node.setStartNode(nodeA);
                node.setEndNode(nodeB);
                relationship = record.get("c").asRelationship();
                node.setRelationship(relationship);
                list.add(node);
//                System.out.println("Node A: " + nodeA.asMap());
//                System.out.println("Node B: " + nodeB.asMap());
//                System.out.println("relationship: " + relationship.asMap());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Results.ok(list);
    }

    /**
     * 根据条件获取有关系的节点
     *
     * @param type          查找结点的类型
     * @param premise       关系的条件参数
     * @param title         关系类型
     */
    public static <R> Results<List<RelationshipNode<R, Node>>> GetNotEndList(Class<R> type, R premise, String title) {
        if (isEmpty(type) || isEmpty(premise) || isEmpty(title)) {
            return Results.fail();
        }
        List<RelationshipNode<R, Node>> list = new ArrayList<>();
        RelationshipNode<R, Node> node;
        try {
            String s = buildClause(premise);
            String url = "match (a:`" + title + "`{" + s + "}) - [r] - (b) return a,r,b;";
            Result result = session.run(url);
            Record record;
            Node nodeA;
            Node nodeB;
            Relationship relationship;
            while (result.hasNext()) {
                node = new RelationshipNode<>();
                record = result.next();
                nodeA = record.get("a").asNode();
                nodeB = record.get("b").asNode();

                R r = BeanUtil.toBean(nodeA.asMap(), type);
                node.setStartNode(r);
                node.setEndNode(nodeB);
                relationship = record.get("r").asRelationship();
                node.setRelationship(relationship);
                list.add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Results.ok(list);
    }

    /**
     * 根据条件获取箭头指向左边的箭头
     * @param type          节点对应的实体类
     * @param premise       节点的条件参数
     * @param title         节点的类型
     */
    public static <R> Results<List<RelationshipNode<R, Node>>> GetLeftList(Class<R> type, R premise, String title) {
        if (isEmpty(type) || isEmpty(premise) || isEmpty(title)) {
            return Results.fail();
        }
        List<RelationshipNode<R, Node>> list = new ArrayList<>();
        RelationshipNode<R, Node> node;
        try {
            String s = buildClause(premise);
            String url = "match (a:`" + title + "`{" + s + "}) - [r] -> (b) return a,r,b;";
            Result result = session.run(url);
            Record record;
            Node nodeA;
            Node nodeB;
            Relationship relationship;
            while (result.hasNext()) {
                node = new RelationshipNode<>();
                record = result.next();
                nodeA = record.get("a").asNode();
                nodeB = record.get("b").asNode();

                R r = BeanUtil.toBean(nodeA.asMap(), type);
                node.setStartNode(r);
                node.setEndNode(nodeB);
                relationship = record.get("r").asRelationship();
                node.setRelationship(relationship);
                list.add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Results.ok(list);
    }

    /**
     * 根据条件获取箭头指向右边的箭头
     * @param type          节点对应的实体类
     * @param premise       节点的条件参数
     * @param title          节点的类型
     */
    public static <R> Results<List<RelationshipNode<Node, R>>> GetRightList(Class<R> type, R premise, String title) {
        if (isEmpty(type) || isEmpty(premise) || isEmpty(title)) {
            return Results.fail();
        }
        List<RelationshipNode<Node, R>> list = new ArrayList<>();
        RelationshipNode<Node, R> node;
        try {
            String s = buildClause(premise);
            String url = "match (a:`" + title + "`{" + s + "}) <- [r] - (b) return a,r,b;";
            Result result = session.run(url);
            Record record;
            Node nodeA;
            Node nodeB;
            Relationship relationship;
            while (result.hasNext()) {
                node = new RelationshipNode<>();
                record = result.next();
                nodeA = record.get("a").asNode();
                nodeB = record.get("b").asNode();
                R r = BeanUtil.toBean(nodeB.asMap(), type);
                node.setStartNode(nodeA);
                node.setEndNode(r);
                relationship = record.get("r").asRelationship();
                node.setRelationship(relationship);
                list.add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Results.ok(list);
    }


    private static <R> Results<R> GetOneNode(Class<R> type, R premise, String title) {
        try {
            String s = " where " + buildWhereClause(premise);
            String url = "match (n:`" + title + "`) " + s + " return n";
            Result result = session.run(url);
            while (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("n").asNode();
                Map<String, Object> stringObjectMap = node.asMap();


                stringObjectMap.forEach((K, V) -> {
                    System.out.println("K = " + K);
                    System.out.println("V = " + V);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Results.ok();
    }

    /**
     * 删除所有数据
     */
    public static void DeleteAll() {
        try {
            String cql = "match (n) detach delete n";
            session.run(cql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static <T> String buildClause(T entity) {
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
                            whereClause.append(field.getName()).append(" : '").append(value).append("' , ");
                        } else {
                            whereClause.append(field.getName()).append(" : ").append(value).append(" , ");
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        // 移除最后一个AND
        if (whereClause.length() > 0) {
            whereClause.setLength(whereClause.length() - 3);
        }
        return whereClause.toString();
    }

    private static <T> String buildWhereClause(T entity) {
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
                            whereClause.append("n.").append(field.getName()).append(" = '").append(value).append("' AND ");
                        } else {
                            whereClause.append("n.").append(field.getName()).append(" = ").append(value).append(" AND ");
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


    private static boolean isEmpty(Object o) {
        return o == null || "".equals(o);
    }


    public static void main(String[] args) {


        PersonNode personNode = new PersonNode();
        personNode.setName("张飞");
        List<RelationshipNode<Node, PersonNode>> relationshipNodeList = GetRightList(PersonNode.class, personNode, "人物").getData();
        for (RelationshipNode<Node, PersonNode> relationshipNode : relationshipNodeList) {
            Node startNode = relationshipNode.getStartNode();
            PersonNode endNode = relationshipNode.getEndNode();
            Relationship relationship = relationshipNode.getRelationship();

            System.out.println("startNode = " + startNode.asMap());
            System.out.println("relationship = " + relationship.asMap());
            System.out.println("endNode = " + endNode);
        }

//        List<PersonNode> nodeList = GetNodeList(PersonNode.class, "人物").getData();
//        nodeList.forEach(System.out::println);

//
//        Results<PersonNode> oneNode = GetOneNode(PersonNode.class, personNode, "人物");
//        PersonNode nodeData = oneNode.getData();
//        System.out.println("nodeData = " + nodeData);

//        List<RelationshipNode<PersonNode, Node>> nodeList = GetLeftList(PersonNode.class, personNode, "人物").getData();
//        for (RelationshipNode<PersonNode, Node> relationshipNode : nodeList) {
//            PersonNode startNode = relationshipNode.getStartNode();
//            Relationship relationship = relationshipNode.getRelationship();
//            Node endNode = relationshipNode.getEndNode();
//
//            System.out.println("startNode = " + startNode);
//            System.out.println("relationship = " + relationship.asMap());
//            System.out.println("endNode = " + endNode.asMap());
//        }
    }

}

