# Plain-language glossary

Algorithm notes sometimes use short technical phrases to describe a precise idea. You do not need to memorize these definitions. Use this page when a term is unfamiliar, then return to the example where you first saw it.

The explanations below use small, everyday descriptions. Formal definitions and conditions remain in the relevant chapter.

## Foundations and analysis

| Term | Plain-language meaning |
|---|---|
| **Algorithm** | A clear set of steps that takes input and produces the required result. |
| **Input size (n)** | A count describing how much data an algorithm receives. For 2-D Maxima, n is the number of points, not how large their coordinates are. |
| **Basic operation** | The repeated action we count to estimate an algorithm's work, such as comparing two points. |
| **Quadratic growth / a quadratic number of comparisons** | The work grows roughly like n × n or n². If the input doubles, the work can become about four times as large. |
| **Brute force** | A direct approach that tries the straightforward checks, even if it repeats work. It is often a useful first correct solution. |
| **Dominate (2-D Maxima)** | One point dominates another when it is at least as good on both coordinates and strictly better on at least one. |
| **Maximal point** | A point that no other point dominates. There can be several maximal points. |
| **RAM model** | A simplified imaginary computer used for analysis. It gives basic operations a consistent cost so algorithms can be compared without relying on a particular laptop. |
| **Constant time — O(1)** | The amount of work stays bounded as the input grows. It does not mean the operation takes zero seconds. |
| **Wall-clock time** | The actual time measured on a real machine, such as milliseconds. It changes with hardware, software, and system load. |
| **Auxiliary space** | Extra working memory an algorithm needs while running. State whether output storage is included when reporting space. |
| **Best / worst / average case** | The least work, most work, or expected work for inputs of the same size. An average-case claim needs a stated assumption about the inputs. |
| **Asymptotic notation** | A way to describe how resource use grows for large inputs, without focusing on machine-specific seconds or constant factors. |
| **O, Ω, and Θ** | O gives an upper growth bound, Ω a lower growth bound, and Θ a tight growth bound. |
| **Dominant term** | The part of a cost formula that grows fastest and therefore matters most for large inputs. In 4n² + 2n + 7, it is n². |
| **Sweep line / plane sweep** | An imaginary line moves across a set of points in a chosen order. The algorithm processes points as the line passes them. |

## Sorting, recursion, and heaps

| Term | Plain-language meaning |
|---|---|
| **Divide and conquer** | Split a problem into smaller versions, solve those, then combine their results. |
| **Recursion** | A method or algorithm solves a problem by calling itself on a smaller version of that problem. |
| **Recurrence** | An equation that describes an algorithm's cost using the cost of smaller inputs, such as T(n) = 2T(n/2) + n. |
| **Stable sorting** | Equal-valued items keep their original relative order after sorting. This matters when items have other fields. |
| **In-place algorithm** | An algorithm that uses only a small, constant amount of extra working memory, rather than another array proportional to the input size. |
| **Pivot** | A chosen value used to divide an array during partitioning. |
| **Partition** | Rearrange an array around a pivot so values are placed on the appropriate sides. The exact handling of equal values depends on the partition method. |
| **Complete binary tree** | A binary tree where every level is full except possibly the last, and the last level is filled from left to right. |
| **Heap** | A complete binary tree arranged so each parent follows an ordering rule relative to its children. In a max-heap, a parent is at least as large as its children. |
| **Priority queue** | A structure that lets us efficiently retrieve the item with the highest or lowest priority. |
| **Lower bound** | A limit showing that, under a stated model, no algorithm can solve a problem using fewer than a certain amount of work in the relevant case. |
| **Comparison-based sorting** | Sorting that learns the order of values only by comparing pairs, rather than using properties such as a small integer range. |

## Dynamic programming and greedy algorithms

| Term | Plain-language meaning |
|---|---|
| **Subproblem** | A smaller question that helps answer the original problem. |
| **Overlapping subproblems** | The same smaller question is needed more than once. Storing its answer avoids solving it repeatedly. |
| **DP state** | The precise meaning of one stored value, often one cell in a table. For example, dp[i][w] might mean the best value using the first i items with capacity w. |
| **Transition** | The rule that calculates one DP state from other states. |
| **Memoization** | Solve recursively and save each answer so a repeated request can reuse it. |
| **Tabulation** | Build answers in a table, starting from known small cases and moving toward the required answer. |
| **Optimal substructure** | An optimal answer can be built using optimal answers to suitable smaller subproblems. |
| **Greedy choice** | The locally preferred decision a greedy algorithm makes at each step. It needs a proof that making this choice is safe for the problem. |
| **Local vs global** | A local choice looks best at the current step; a global optimum is the best complete solution overall. A locally best choice does not always lead to a globally best result. |
| **Prefix-free code** | A set of codes where no complete code is the beginning of another. This lets a decoder identify a symbol without needing separators. |
| **Exchange argument** | A proof technique: start with an optimal solution and show it can be changed to include the greedy choice without making the solution worse. |

## Graph algorithms

| Term | Plain-language meaning |
|---|---|
| **Vertex / edge** | A vertex is an object in a graph; an edge is a connection between two vertices. |
| **Degree** | In an undirected graph, the number of edges touching a vertex. |
| **Indegree** | In a directed graph, the number of edges pointing into a vertex. |
| **DAG** | A directed acyclic graph: a graph with directed edges but no directed cycle. |
| **Strongly connected component (SCC)** | A largest possible group in a directed graph where every vertex can reach every other vertex in that group. |
| **Transpose graph** | A directed graph made by reversing the direction of every edge. |
| **Minimum spanning tree (MST)** | A lowest-total-weight set of edges that connects every vertex of a connected, undirected graph without cycles. |
| **Disjoint-set union (DSU)** | A structure that tracks which items belong to the same group and can merge groups. Kruskal's algorithm uses it to avoid adding cycle-forming edges. |
| **Relax an edge** | Check whether going through an edge gives a shorter known route, and update the distance if it does. |
| **Tentative distance** | The best route length found so far. It may improve as the algorithm explores more edges. |
| **Negative-weight cycle** | A cycle whose edge weights add up to a negative number. Repeating it can keep reducing a path's total cost, so a finite shortest path may not exist. |

## Complexity theory

| Term | Plain-language meaning |
|---|---|
| **Decision problem** | A problem whose answer is yes or no. |
| **Polynomial time** | Running time bounded by a polynomial such as n, n², or n³. It is commonly used as a mathematical definition of efficient solvability. |
| **Certificate** | Evidence supplied with a proposed yes-answer that can be checked efficiently. For graph coloring, it is the proposed color assigned to each vertex. |
| **Reduction** | A way to translate instances of one problem into another while preserving the answer. It lets us compare problem difficulty. |
| **P** | Decision problems that can be solved in polynomial time. |
| **NP** | Decision problems for which a proposed yes-answer can be verified in polynomial time. NP does not mean “not polynomial.” |
| **NP-hard** | At least as hard as every problem in NP, under the reduction type being used. An NP-hard problem does not have to belong to NP. |
| **NP-complete** | A problem that is both in NP and NP-hard. |

---

## How this glossary is maintained

- Important terms are explained briefly where they first matter.
- This page gives a quick reference when a phrase is still unfamiliar.
- New terms are added only when they are useful for understanding a course topic.
- Definitions should stay short; examples belong in the relevant chapter.
