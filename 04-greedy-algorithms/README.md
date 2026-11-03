# 4. Greedy Algorithms

**VU LMS index:** 24–28  
**Prerequisites:** sorting, priority queues, and basic proof reasoning.

A greedy algorithm makes a locally attractive choice and commits to it. The key question is not whether a choice looks reasonable; it is whether that choice can be shown to belong to an optimal solution.

## 1. Coin change

### Problem

Given coin denominations and a target amount, make the amount using the fewest coins.

A common greedy rule repeatedly takes the largest coin that does not exceed the remaining amount. It works for many familiar coin systems, but not for every denomination system.

Counterexample:
- Coins: {1, 3, 4}
- Amount: 6
- Greedy: 4 + 1 + 1 = 3 coins
- Optimal: 3 + 3 = 2 coins

Greedy choice needs proof or a known property of the input system. For arbitrary denominations, dynamic programming can solve the minimum-coin version.

## 2. Huffman encoding

### Problem

Compress symbols when some occur more frequently than others. A frequent symbol should ideally use a shorter bit string.

Huffman coding builds a binary prefix tree:

1. Create a leaf for each symbol, weighted by its frequency.
2. Put the leaves in a min-priority queue.
3. Remove the two least-frequent nodes.
4. Combine them into a new node whose weight is their sum.
5. Insert the new node.
6. Repeat until one tree remains.
7. Label left edges 0 and right edges 1; a symbol's code is the path from root to its leaf.

The code is **prefix-free**: no symbol's code is the prefix of another symbol's code. This makes decoding unambiguous.

With a priority queue, construction takes O(n log n) for n symbols. Exact bit strings may differ when frequencies tie, while weighted cost can remain optimal.

### Check a Huffman result

- Every original symbol appears at a leaf.
- Merge weights add correctly.
- No leaf code is a prefix of another.
- Frequent symbols tend to have shorter codes.
- Weighted path length is the sum of frequency × code length.

## 3. Activity selection

### Problem

Choose the largest number of mutually compatible activities, each with a start and finish time.

Greedy rule: sort activities by increasing finish time, then select the next activity whose start is at or after the finish of the last selected activity.

Why earliest finish? It leaves as much room as possible for the remaining activities.

- Sorting: O(n log n).
- Selection after sorting: O(n).
- Total: O(n log n), or O(n) if already sorted by finish time.

This solves **unweighted maximum-cardinality** activity selection. It is not weighted interval scheduling, where activities have values and dynamic programming is generally needed.

## 4. Fractional knapsack

### Problem

Each item has a weight and value, but unlike 0/1 knapsack, an item can be split.

Greedy rule: sort by value per unit weight (value/weight), descending. Take as much as possible from the highest-ratio item, then continue.

- Sorting: O(n log n).
- Greedy scan: O(n).
- The rule is optimal because fractions are allowed: taking a smaller value-per-weight fraction while a larger one remains cannot improve the total.

**Do not apply this proof to 0/1 Knapsack.** If items cannot be split, the value/weight ratio greedy rule can fail.

## How to test a greedy idea

1. State the local choice precisely.
2. Identify the property that makes the choice safe.
3. Try an exchange argument: transform an optimal solution to include the greedy choice without making it worse.
4. Search for a small counterexample.
5. State the conditions under which the proof applies.

## Self-check

1. Give a coin system where largest-first greedy fails.
2. Why does Huffman repeatedly combine the two least-frequent nodes?
3. What makes a Huffman code prefix-free?
4. Why does earliest-finish-time work for unweighted activity selection?
5. Why is weighted interval scheduling a different problem?
6. Why does the fractional-knapsack proof not establish an optimal rule for 0/1 knapsack?

## Java coverage

The Java project includes the activity-selection routine. Coin change is deliberately shown with a counterexample: greedy coin change is not universally correct. Huffman tree construction and fractional knapsack are guided exercises, not claimed as completed implementations.
