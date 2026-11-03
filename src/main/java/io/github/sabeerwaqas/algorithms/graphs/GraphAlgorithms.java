package io.github.sabeerwaqas.algorithms.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

/** Adjacency-list traversal and non-negative single-source shortest paths. */
public final class GraphAlgorithms {
    private GraphAlgorithms() {}

    public record WeightedEdge(int to, int weight) {}
    private record QueueEntry(int vertex, long distance) {}

    public static List<Integer> breadthFirstSearch(List<List<Integer>> graph, int source) {
        validateGraph(graph);
        validateVertex(source, graph.size());
        boolean[] visited = new boolean[graph.size()];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        List<Integer> order = new ArrayList<>();
        visited[source] = true;
        queue.addLast(source);
        while (!queue.isEmpty()) {
            int current = queue.removeFirst();
            order.add(current);
            for (int neighbour : graph.get(current)) {
                validateVertex(neighbour, graph.size());
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.addLast(neighbour);
                }
            }
        }
        return List.copyOf(order);
    }

    public static List<Integer> depthFirstSearch(List<List<Integer>> graph, int source) {
        validateGraph(graph);
        validateVertex(source, graph.size());
        boolean[] visited = new boolean[graph.size()];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        List<Integer> order = new ArrayList<>();
        stack.push(source);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (visited[current]) continue;
            visited[current] = true;
            order.add(current);
            List<Integer> neighbours = graph.get(current);
            for (int i = neighbours.size() - 1; i >= 0; i--) {
                int neighbour = neighbours.get(i);
                validateVertex(neighbour, graph.size());
                if (!visited[neighbour]) stack.push(neighbour);
            }
        }
        return List.copyOf(order);
    }

    /** Dijkstra: edge weights must be non-negative. Unreachable vertices retain Long.MAX_VALUE. */
    public static long[] dijkstra(List<List<WeightedEdge>> graph, int source) {
        Objects.requireNonNull(graph, "graph must not be null");
        validateVertex(source, graph.size());
        for (List<WeightedEdge> edges : graph) {
            Objects.requireNonNull(edges, "edge list must not be null");
            for (WeightedEdge edge : edges) {
                Objects.requireNonNull(edge, "edge must not be null");
                validateVertex(edge.to(), graph.size());
                if (edge.weight() < 0) throw new IllegalArgumentException("Dijkstra requires non-negative weights");
            }
        }

        long[] distance = new long[graph.size()];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[source] = 0;
        PriorityQueue<QueueEntry> queue = new PriorityQueue<>(
                Comparator.comparingLong(QueueEntry::distance));
        queue.add(new QueueEntry(source, 0));

        while (!queue.isEmpty()) {
            QueueEntry entry = queue.remove();
            int current = entry.vertex();
            if (entry.distance() != distance[current]) continue;
            for (WeightedEdge edge : graph.get(current)) {
                if (distance[current] > Long.MAX_VALUE - edge.weight()) continue;
                long candidate = distance[current] + edge.weight();
                if (candidate < distance[edge.to()]) {
                    distance[edge.to()] = candidate;
                    queue.add(new QueueEntry(edge.to(), candidate));
                }
            }
        }
        return distance;
    }

    private static void validateGraph(List<List<Integer>> graph) {
        Objects.requireNonNull(graph, "graph must not be null");
        for (List<Integer> neighbours : graph) Objects.requireNonNull(neighbours, "neighbour list must not be null");
    }

    private static void validateVertex(int vertex, int count) {
        if (vertex < 0 || vertex >= count) throw new IllegalArgumentException("vertex outside graph");
    }
}
