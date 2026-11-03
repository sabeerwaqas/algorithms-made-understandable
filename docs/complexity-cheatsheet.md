# Complexity analysis: quick reference

New to a term? Check the [plain-language algorithm glossary](glossary.md).

## A reliable analysis sequence

1. Identify the input-size variable (n, V, E, k, or a combination).
2. Choose the operation that represents the repeated work.
3. Count how often it executes.
4. Express the count as a function.
5. Keep the dominant growth rate.
6. State input assumptions and auxiliary space separately.

## Common patterns

| Code pattern | Typical time | Reason |
|---|---:|---|
| Fixed number of statements | O(1) | Work does not grow with n |
| One loop over n | O(n) | n iterations |
| Two independent nested loops over n | O(n²) | n × n |
| Repeatedly halve n | O(log n) | Number of halvings |
| Sort then scan | O(n log n) | Sorting dominates linear scan |
| Visit vertices and edges | O(V + E) | Adjacency-list traversal |
| Compare every pair | O(n²) | n choices × n comparisons |

A loop inside a loop is not automatically O(n²). Inspect the actual limits and how they change.

## Asymptotic notation

- O(g(n)): asymptotic upper bound.
- Ω(g(n)): asymptotic lower bound.
- Θ(g(n)): asymptotically tight bound.

If T(n) = 4n² + 2n + 7, then T(n) = Θ(n²).

## Time and space are different

- Time complexity counts growth in computational work.
- Auxiliary space counts extra memory used by the algorithm, excluding input unless stated otherwise.
- Total space may include input, output, recursion stack, and temporary buffers.

Always state which space convention you use.

## Benchmarks

Benchmarks answer how long this implementation takes on this machine and workload. Measure multiple input sizes, use representative data, warm up the JVM when benchmarking Java, and do not treat one timing as an asymptotic proof.
