# 5. Graph Algorithms

**VU LMS index:** 28–43  
**Prerequisites:** arrays, queues, stacks, recursion, heaps, and asymptotic analysis.

Graphs model relationships. A graph consists of vertices (objects) and edges (relationships). Graph algorithms answer questions about reachability, dependencies, connectivity, spanning networks, and shortest routes.

## 1. Graph vocabulary

- **Vertex:** a node in the graph.
- **Edge:** a connection between two vertices.
- **Undirected graph:** an edge works in both directions.
- **Directed graph:** an edge has a direction.
- **Weighted graph:** each edge has a cost or weight.
- **Path:** a sequence of vertices connected by edges.
- **Cycle:** a path that returns to its starting vertex.
- **Connected component:** a maximal set of mutually reachable vertices in an undirected graph.
- **DAG:** directed acyclic graph.

## 2. Graph representations

| Operation / property | Adjacency matrix | Adjacency list |
|---|---:|---:|
| Space | O(V²) | O(V + E) |
| Check whether u-v exists | O(1) | O(degree(u)) |
| Iterate over neighbours of u | O(V) | O(degree(u)) |
| Good fit | Dense, small graphs | Sparse graphs |

An undirected edge u-v appears in both adjacency lists. A directed edge u→v appears only in u's list. The Java examples use adjacency lists.

## 3. Breadth-first search (BFS)

### Problem

Explore a graph in layers: first the source, then vertices one edge away, then two edges away, and so on.

BFS uses a queue:

1. Mark the source discovered and enqueue it.
2. Remove the front vertex.
3. For each undiscovered neighbour, mark it discovered and enqueue it.
4. Continue until the queue is empty.

Mark a vertex when it is enqueued, not when it is later removed. This prevents duplicate queue entries.

For adjacency-list graphs, BFS is O(V + E). In an unweighted graph, BFS also finds shortest paths measured by number of edges.

## 4. The general traversal pattern

BFS and DFS share a structure:
- maintain discovered/visited state;
- choose the next frontier vertex using a queue (BFS) or stack/recursion (DFS);
- inspect neighbours;
- add previously unseen neighbours;
- stop when no frontier remains.

For a disconnected graph, start a traversal from each still-unvisited vertex if every component must be visited.

## 5. Depth-first search (DFS) and timestamps

DFS explores one branch as far as possible before backtracking.

It can be implemented recursively or with an explicit stack. In the classic timestamp version, record:
- discovery time d[v], when v first becomes active;
- finishing time f[v], when all its outgoing neighbours have been processed.

The colour model is:
- white: undiscovered;
- gray: currently active (on the recursion path);
- black: finished.

For adjacency lists, DFS is O(V + E).

Timestamps help reason about edge types, cycles, and topological ordering. In a directed graph, an edge to a gray vertex identifies a back edge and therefore a directed cycle.

## 6. Cycles and topological order

### Cycle detection

- **Undirected DFS:** ignore the edge back to the parent; an edge to another already-visited vertex indicates a cycle.
- **Directed DFS:** an edge to a gray (active) vertex indicates a cycle.
- **Kahn's algorithm:** if fewer than V vertices are removed from the zero-indegree queue, some vertices remain in a directed cycle.

### Topological sorting

A topological ordering places every vertex u before v for each directed edge u→v. Such an ordering exists only for a DAG.

Kahn's algorithm:
1. Compute each vertex's indegree.
2. Enqueue all vertices with indegree zero.
3. Remove one, append it to the order, and decrement the indegree of its outgoing neighbours.
4. Enqueue a neighbour when its indegree becomes zero.
5. If the output has fewer than V vertices, the graph contains a cycle.

Time: O(V + E).

## 7. Strongly connected components

In a directed graph, a strongly connected component (SCC) is a maximal set of vertices where every vertex can reach every other vertex.

**Kosaraju's algorithm:**
1. Run DFS on the original graph and record finishing order.
2. Reverse every edge to construct the transpose graph.
3. Process vertices in decreasing finishing time, running DFS on the transpose from each unvisited vertex.
4. Each traversal discovers one SCC.

Time: O(V + E). The transpose requires O(V + E) space.

Tarjan's algorithm is another linear-time SCC method using DFS indices and low-link values.

## 8. Minimum spanning trees

A spanning tree of a connected, undirected, weighted graph includes every vertex, has no cycle, and contains V−1 edges. A **minimum spanning tree (MST)** has the smallest total edge weight among all spanning trees.

The generic strategy repeatedly adds a safe light edge without creating a cycle. The cut property explains why a minimum-weight edge crossing a cut is safe under the standard MST conditions.

## 9. Kruskal's algorithm

1. Sort edges by increasing weight.
2. Start with every vertex in its own disjoint set.
3. Process edges from lightest to heaviest.
4. Add an edge only when its endpoints are in different sets.
5. Union those sets.
6. Stop after V−1 edges for a connected graph.

With sorting and a disjoint-set union structure using path compression and union by rank/size, time is O(E log E).

If the graph is disconnected, the result is a minimum spanning forest, not one spanning tree.

## 10. Prim's algorithm

Start from any vertex and grow one tree:
1. Keep the cheapest known edge that connects an outside vertex to the current tree.
2. Add the cheapest such vertex and edge.
3. Update candidate edges to its neighbours.
4. Stop when all reachable vertices are included.

With an adjacency list and binary heap, a common bound is O(E log V). Prim and Kruskal solve the same MST problem using different growth strategies.

## 11. Shortest paths

A shortest path minimizes the sum of edge weights. The right algorithm depends on edge weights and whether one source or all pairs are required.

| Problem | Suitable algorithm | Key condition |
|---|---|---|
| Single source, unweighted | BFS | Each edge counts as one |
| Single source, non-negative weights | Dijkstra | No negative edge weights |
| Single source, negative edges allowed | Bellman-Ford | Detect reachable negative cycles |
| All pairs | Floyd-Warshall | O(V³), matrix-based |

## 12. Dijkstra's algorithm

Dijkstra repeatedly finalizes the unvisited vertex with the smallest tentative distance and relaxes its outgoing edges.

Relaxing edge u→v with weight w means checking whether dist[u] + w is smaller than dist[v].

- With adjacency list and binary heap: O((V + E) log V).
- Requires non-negative edge weights.
- A negative edge can make a previously finalized distance improve, invalidating the greedy step.

## 13. Bellman-Ford

Bellman-Ford relaxes every edge up to V−1 times. A shortest simple path contains at most V−1 edges, so after those rounds all shortest distances are known if there is no reachable negative cycle.

One extra pass checks whether any reachable distance can still improve. If so, a reachable negative-weight cycle exists.

- Time: O(VE).
- Supports negative edge weights.
- A negative cycle reachable from the source means no finite shortest distance exists for vertices affected by that cycle.

## 14. Floyd-Warshall

Let dist[i][j] be the best known distance from i to j. Process possible intermediate vertices k one at a time:

dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])

Initialize:
- dist[i][i] = 0;
- dist[i][j] = edge weight for a direct edge;
- infinity if there is no direct edge.

After all k values have been considered, dist contains all-pairs shortest distances, provided no negative cycle makes the relevant distances undefined.

- Time: O(V³).
- Space: O(V²).
- Negative edges are allowed, but a negative cycle is not.

## Self-check

1. When is an adjacency matrix preferable to an adjacency list?
2. Why does BFS find shortest paths in an unweighted graph?
3. What is the role of the queue in BFS and the stack in DFS?
4. What do discovery and finish timestamps represent?
5. Why can a topological order exist only for a DAG?
6. What makes an SCC different from an ordinary connected component?
7. Why does Kruskal need cycle detection?
8. What is the key difference between Prim and Kruskal?
9. Why does Dijkstra require non-negative weights?
10. How does Bellman-Ford detect a reachable negative cycle?
11. What does the k loop mean in Floyd-Warshall?

## Java coverage

The Java project includes adjacency-list BFS, DFS, and Dijkstra examples. Dedicated SCC, MST, Bellman-Ford, and Floyd-Warshall implementations are further additions; this guide explains their algorithmic steps and conditions without claiming those classes already exist.
