package io.github.sabeerwaqas.algorithms.graphs;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GraphAlgorithmsTest {
    @Test
    void bfsAndDfsVisitReachableVertices() {
        List<List<Integer>> graph = List.of(
                List.of(1, 2), List.of(0, 3), List.of(0), List.of(1), List.of());
        assertEquals(List.of(0, 1, 2, 3), GraphAlgorithms.breadthFirstSearch(graph, 0));
        assertEquals(List.of(0, 1, 3, 2), GraphAlgorithms.depthFirstSearch(graph, 0));
    }

    @Test
    void dijkstraFindsShortestDistances() {
        List<List<GraphAlgorithms.WeightedEdge>> graph = List.of(
                List.of(new GraphAlgorithms.WeightedEdge(1, 4), new GraphAlgorithms.WeightedEdge(2, 1)),
                List.of(new GraphAlgorithms.WeightedEdge(3, 1)),
                List.of(new GraphAlgorithms.WeightedEdge(1, 2), new GraphAlgorithms.WeightedEdge(3, 5)),
                List.of());
        assertArrayEquals(new long[]{0, 3, 1, 4}, GraphAlgorithms.dijkstra(graph, 0));
    }

    @Test
    void dijkstraRejectsNegativeWeights() {
        List<List<GraphAlgorithms.WeightedEdge>> graph = List.of(
                List.of(new GraphAlgorithms.WeightedEdge(1, -1)), List.of());
        assertThrows(IllegalArgumentException.class, () -> GraphAlgorithms.dijkstra(graph, 0));
    }
}
