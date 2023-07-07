package org.example.algrithem.DAG;
import java.util.*;

class Node {
    int id;
    List<Node> predecessors;
    List<Node> successors;
    int value;

    public Node(int id) {
        this.id = id;
        this.predecessors = new ArrayList<>();
        this.successors = new ArrayList<>();
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

class DAG {
    List<Node> nodes;

    public DAG() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void addEdge(Node from, Node to) {
        from.successors.add(to);
        to.predecessors.add(from);
    }

    public List<Node> topologicalSort() {
        List<Node> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();

        // 计算每个节点的入度
        Map<Node, Integer> indegrees = new HashMap<>();
        for (Node node : nodes) {
            indegrees.put(node, 0);
        }
        for (Node node : nodes) {
            for (Node successor : node.successors) {
                indegrees.put(successor, indegrees.get(successor) + 1);
            }
        }

        // 将入度为 0 的节点加入队列
        for (Node node : nodes) {
            if (indegrees.get(node) == 0) {
                queue.offer(node);
            }
        }

        // 按照拓扑排序顺序依次访问节点
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            result.add(node);
            for (Node successor : node.successors) {
                indegrees.put(successor, indegrees.get(successor) - 1);
                if (indegrees.get(successor) == 0) {
                    queue.offer(successor);
                }
            }
        }

        return result;
    }

    public int calculate(Node node, Map<Node, Integer> cache) {
        // 如果已经计算过节点的输出，则直接返回缓存中的结果
        if (cache.containsKey(node)) {
            return cache.get(node);
        }

        // 如果节点有脏值，则重新计算节点的输出
        boolean dirty = false;
        int sum = 0;
        for (Node predecessor : node.predecessors) {
            if (cache.containsKey(predecessor) && predecessor.getValue() != cache.get(predecessor)) {
                dirty = true;
                break;
            }
            sum += calculate(predecessor, cache);
        }
        if (dirty) {
            sum = 0;
            for (Node predecessor : node.predecessors) {
                sum += calculate(predecessor, cache);
            }
        }

        // 缓存结果并返回
        node.setValue(sum);
        cache.put(node, sum);
        return sum;
    }
}

public class DirtyValueCalculation {
    public static void main(String[] args) {
        // 构造有向无环图
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        DAG dag = new DAG();
        dag.addNode(node1);
        dag.addNode(node2);
        dag.addNode(node3);
        dag.addNode(node4);
        dag.addNode(node5);
        dag.addEdge(node1, node2);
        dag.addEdge(node1, node3);
        dag.addEdge(node2, node4);
        dag.addEdge(node3, node4);
        dag.addEdge(node4, node5);

        // 计算节点的输出
        Map<Node, Integer> cache = new HashMap<>();
        int result = dag.calculate(node5, cache);
        System.out.println("Result: " + result);
    }
}