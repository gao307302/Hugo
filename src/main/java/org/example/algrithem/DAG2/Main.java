package org.example.algrithem.DAG2;

import java.util.*;

class Node {
    private String id;
    private double value;
    private List<Node> upstream;
    private List<Node> downstream;
    private boolean dirty;

    public Node(String id, double value) {
        this.id = id;
        this.value = value;
        this.upstream = new ArrayList<>();
        this.downstream = new ArrayList<>();
        this.dirty = true; // 初始时所有节点都需要重新计算值
    }

    public String getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public List<Node> getUpstream() {
        return upstream;
    }

    public List<Node> getDownstream() {
        return downstream;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }
}

class Graph {
    List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void calculateDirtyNodes() {
        Queue<Node> queue = new LinkedList<>();
        for (Node node : nodes) {
            if (node.isDirty()) {
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Node downstream : node.getDownstream()) {
                if (!downstream.isDirty()) {
                    downstream.setDirty(true);
                    queue.add(downstream);
                }
            }
        }
    }

    public void calculateValues(List<Node> nodes) {
        Set<Node> visited = new HashSet<>();
        for (Node node : nodes) {
            if (!visited.contains(node)) {
                dfs(node, visited);
            }
        }
    }

    private void dfs(Node node, Set<Node> visited) {
        visited.add(node);
        for (Node upstream : node.getUpstream()) {
            if (!visited.contains(upstream)) {
                dfs(upstream, visited);
            }
        }
        if (node.isDirty()) {
            double value = 0;
            for (Node upstream : node.getUpstream()) {
                value += upstream.getValue();
            }
            node.setValue(value);
            node.setDirty(false);
        }
    }

    public List<List<Node>> calculate() {
        List<List<Node>> result = new ArrayList<>();
        calculateDirtyNodes();
        Set<Node> visited = new HashSet<>();
        for (Node node : nodes) {
            if (node.isDirty() && !visited.contains(node)) {
                List<Node> group = new ArrayList<>();
                dfsGroup(node, visited, group);
                result.add(group);
            }
        }
        return result;
    }

    private void dfsGroup(Node node, Set<Node> visited, List<Node> group) {
        visited.add(node);
        for (Node downstream : node.getDownstream()) {
            if (downstream.isDirty() && !visited.contains(downstream)) {
                dfsGroup(downstream, visited, group);
            }
        }
        if (node.isDirty()) {
            group.add(node);
            node.setDirty(false);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Node a = new Node("a", 1);
        Node b = new Node("b", 2);
        Node c = new Node("c", 3);
        Node d = new Node("d", 4);
        Node e = new Node("e", 5);

        a.getDownstream().add(b);
        a.getDownstream().add(c);
        b.getUpstream().add(a);
        b.getDownstream().add(d);
        c.getUpstream().add(a);
        c.getDownstream().add(d);
        d.getUpstream().add(b);
        d.getUpstream().add(c);
        d.getDownstream().add(e);
        e.getUpstream().add(d);

        Graph graph = new Graph();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);

        List<List<Node>> result = graph.calculate();
        for (List<Node> group : result) {
            for (Node node : group) {
                System.out.print(node.getId() + " ");
            }
            System.out.println();
            graph.calculateValues(group);
        }
        System.out.println("Final values:");
        for (Node node : graph.nodes) {
            System.out.println(node.getId() + ": " + node.getValue());
        }
    }
}