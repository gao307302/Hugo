package org.example.algrithem;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Hugo.Gao
 */
public class TopologicalSort {
    /**
     * 邻接表表示的有向无环图
     */
    private final Map<Integer, List<Integer>> graph;

    public TopologicalSort(Map<Integer, List<Integer>> graph) {
        this.graph = graph;
    }

    /**
     * 对有向无环图进行可并行的拓扑排序
     * @return 拓扑排序结果
     */
    public List<List<Integer>> sort() {
        // 统计每个节点的前置依赖数
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (List<Integer> neighbors : graph.values()) {
            for (int neighbor : neighbors) {
                inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
            }
        }

        // 将所有没有前置依赖关系的节点加入结果中
        List<Integer> nodeList = new ArrayList<>();
        for (int node : graph.keySet()) {
            if (!inDegree.containsKey(node)) {
                nodeList.add(node);
            }
        }

        // 依次将节点加入拓扑排序结果中，并更新其下游节点的前置依赖数
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> nextNodes = new ArrayList<>();
        while (!nodeList.isEmpty()) {
            result.add(nodeList);
            for (Integer node : nodeList) {
                List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
                for (Integer nextNode : neighbors) {
                    if (!inDegree.containsKey(nextNode)) {
                        inDegree.put(nextNode, 0);
                    }
                    inDegree.put(nextNode, inDegree.get(nextNode) - 1);
                    if (inDegree.get(nextNode) == 0) {
                        nextNodes.add(nextNode);
                    }
                }
            }
            nodeList = nextNodes;
            nextNodes = new ArrayList<>();
        }

        return result;
    }

    /**
     * 对某些节点及其下游节点进行拓扑排序
     * @param nodeList 节点编号
     * @return 节点及其下游节点的拓扑排序结果
     */
    public List<List<Integer>> sortFromNode(Set<Integer> nodeList) {
        // 构造以变化节点为起点的子图
        Map<Integer, List<Integer>> subGraph = new HashMap<>();
        Set<Integer> visited = nodeList;
        Set<Integer> nextNodes = new HashSet<>();
        while (!nodeList.isEmpty()) {
            for (Integer curNode : new HashSet<>(nodeList)) {
                List<Integer> neighbors = graph.getOrDefault(curNode, new ArrayList<>());
                List<Integer> nextNeighbors = new ArrayList<>();
                for (Integer nextNode : neighbors) {
                    nextNeighbors.add(nextNode);
                    if (!visited.contains(nextNode)) {
                        nextNodes.add(nextNode);
                        visited.add(nextNode);
                    }
                }
                subGraph.putIfAbsent(curNode, nextNeighbors);
            }
            nodeList = nextNodes;
            nextNodes = new HashSet<>();
        }

        // 对子图进行拓扑排序
        TopologicalSort subSorter = new TopologicalSort(subGraph);
        List<List<Integer>> subResult = subSorter.sort();

        return subResult;
    }

    /**
     * 对已有的拓扑排序添加节点
     * @param nodeList 节点编号
     * @param sortedResult 已有的拓扑排序
     * @return 不在List<List<Integer>> sortedResult中但是在Set<Integer> nodeList中的所有点
     */
    public List<Integer> addNodesForSortedResult(Set<Integer> nodeList, List<List<Integer>> sortedResult) {
        return nodeList.stream()
                .filter(node -> sortedResult.stream().noneMatch(list -> list.contains(node)))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // 构造通用全量的有向无环图
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(CalcNode.RENT_ROLL_RENT_END_DATE.getCode(), Arrays.asList(CalcNode.CASHFLOW_TENANT_POTENTIAL_RENT.getCode()));
        graph.put(CalcNode.RENT_ROLL_RENT_FEE.getCode(), Arrays.asList(CalcNode.RENT_ROLL_RENT_FEE_DAILY.getCode()));
        graph.put(CalcNode.RENT_ROLL_RENT_FEE_UNIT.getCode(), Arrays.asList(CalcNode.RENT_ROLL_RENT_FEE_DAILY.getCode()));
        graph.put(CalcNode.RENT_ROLL_RENT_FEE_DAILY.getCode(), Arrays.asList(CalcNode.CASHFLOW_TENANT_POTENTIAL_RENT.getCode()));
        graph.put(CalcNode.CASHFLOW_TENANT_POTENTIAL_RENT.getCode(), Arrays.asList(CalcNode.OTHERS_REVENUE_AMOUNT.getCode()));
        graph.put(CalcNode.LEASING_GROUP.getCode(), Arrays.asList(CalcNode.OTHERS_REVENUE_AMOUNT.getCode()));
        graph.put(CalcNode.OTHERS_REVENUE_AMOUNT.getCode(), new ArrayList<>());

        // 创建拓扑排序器
        TopologicalSort sorter = new TopologicalSort(graph);

        // 对整个图进行拓扑排序
        List<List<Integer>> result = sorter.sort();
        // [[1, 2, 3, 7], [4], [5], [6]]
        System.out.println(result);

        Set<Integer> changedNodes = new HashSet<>();
        // 修改了台账终止日
        changedNodes.add(CalcNode.RENT_ROLL_RENT_END_DATE.getCode());
        // 对节点台账终止日及其下游节点进行拓扑排序
        List<List<Integer>> subResult = sorter.sortFromNode(changedNodes);

        List<Integer> nodeCalculating;
        while (subResult.size() > 0) {
            nodeCalculating = subResult.get(0);
            //TODO 并行计算第一批
            if (subResult.size() > 1) {
                subResult.remove(0);
            }
        }


        // 对已有的拓扑排序添加节点，发生在计算未完成但是有新节点变更



        // 对已有的拓扑排序删除节点，发生在修改前后值没变，理论上只会发生在sortedResult.get(0）中
        // [[1], [5], [6]]
        System.out.println(subResult);
    }

    private enum CalcNode{
        RENT_ROLL_RENT_END_DATE(1, "RentRollRentEndDate"),
        RENT_ROLL_RENT_FEE(2, "RentRollRentFee"),
        RENT_ROLL_RENT_FEE_UNIT(3, "RentRollRentFeeUnit"),
        RENT_ROLL_RENT_FEE_DAILY(4, "RentRollRentFeeDaily"),
        CASHFLOW_TENANT_POTENTIAL_RENT(5, "CashflowTenantPotentialRent"),
        OTHERS_REVENUE_AMOUNT(6, "OthersRevenueAmount"),
        LEASING_GROUP(7, "leasingGroup");
        private Integer code;
        private String desc;

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        private CalcNode(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static CalcNode getByCode(Integer code) {
            for (CalcNode calcNode : CalcNode.values()) {
                if (calcNode.getCode().equals(code)) {
                    return calcNode;
                }
            }
            return null;
        }

    }
}