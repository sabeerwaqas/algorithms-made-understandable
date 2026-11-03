# 3. Dynamic Programming

**VU LMS index:** 17–23  
**Prerequisites:** recursion, arrays, recurrence relations, and basic complexity analysis.

Dynamic programming (DP) is useful when a problem can be expressed through smaller subproblems and those subproblems overlap. Instead of solving the same subproblem repeatedly, store its result and reuse it.

## A DP design checklist

For each problem, answer these questions in order:

1. **State:** what does one subproblem represent?
2. **Choice:** what decisions are available?
3. **Transition:** how does a state depend on smaller states?
4. **Base cases:** what values are already known?
5. **Evaluation order:** in what order can dependencies be computed?
6. **Reconstruction:** do we need only the best value, or the actual choices too?

Do not start by filling a table. First define what each table cell means.

## 1. Fibonacci

The sequence is defined by F(0)=0, F(1)=1, and F(n)=F(n−1)+F(n−2) for n ≥ 2.

A direct recursive implementation recomputes values. For example, F(5) needs F(3) through both F(4) and F(3) again.

Memoization stores results from top-down recursion. Tabulation fills values from the base cases upward.

| Approach | Time | Extra space |
|---|---:|---:|
| Naive recursion | O(2ⁿ) upper bound | O(n) call stack |
| Memoization | O(n) | O(n) |
| Bottom-up table | O(n) | O(n) |
| Two-variable iteration | O(n) | O(1) |

The Java example uses two-variable iteration because only the previous two values are needed.

## 2. Edit Distance

### Problem

How many single-character edits are needed to transform one string into another? The allowed edits are insertion, deletion, and substitution.

Example: change CAT into CUT with one substitution, so the edit distance is 1.

### State

Let dp[i][j] be the minimum edits needed to transform the first i characters of string A into the first j characters of string B.

### Base cases

- dp[i][0] = i: delete all i characters.
- dp[0][j] = j: insert all j characters.

### Transition

If the last characters are equal, dp[i][j] = dp[i−1][j−1].

Otherwise:

dp[i][j] = 1 + min(
  dp[i−1][j],     deletion
  dp[i][j−1],     insertion
  dp[i−1][j−1]    substitution
)

Fill the table from top-left to bottom-right because each state depends on cells above, left, and diagonally above-left.

- Time: O(mn).
- Space: O(mn) for the full table.
- Space can be reduced to O(min(m,n)) if only the distance is needed. Reconstructing the edit sequence requires additional information.

## 3. Matrix-chain multiplication

### Why does it matter?

Matrix multiplication is associative, but changing the parenthesization can change the number of scalar multiplications dramatically.

For matrices:
- A1: 10 × 30
- A2: 30 × 5
- A3: 5 × 60

(A1A2)A3 costs 10·30·5 + 10·5·60 = 1,500 + 3,000 = 4,500 multiplications.

A1(A2A3) costs 30·5·60 + 10·30·60 = 9,000 + 18,000 = 27,000 multiplications.

The aim is not to change the order of matrices; it is to choose the cheapest parenthesization.

### State and recurrence

Let p0, p1, …, pn describe the dimensions of n matrices. Let m[i][j] be the minimum number of scalar multiplications needed to compute Ai through Aj.

- m[i][i] = 0.
- For i < j:

m[i][j] = min over i ≤ k < j of
m[i][k] + m[k+1][j] + p(i−1)·p(k)·p(j)

Try every possible final split k and keep the cheapest.

- Time: O(n³).
- Space: O(n²).

The table is filled by increasing chain length: single matrices first, then chains of length two, and so on.

## 4. 0/1 Knapsack

### Problem

Each item has a weight and a value. A bag has capacity W. Choose items to maximize total value without exceeding capacity. Each item can be selected zero or one time.

This differs from fractional knapsack: an item cannot be split.

### State

Let dp[i][w] be the best value using the first i items with capacity w.

For item i with weight wi and value vi:
- If wi > w, the item cannot fit: dp[i][w] = dp[i−1][w].
- Otherwise choose the better of excluding and including:

dp[i][w] = max(
  dp[i−1][w],
  vi + dp[i−1][w−wi]
)

Base case: dp[0][w] = 0.

- Time: O(nW).
- Space: O(nW) for the full table, reducible to O(W) with a one-dimensional array.

**Important:** O(nW) is pseudo-polynomial. W is a numeric capacity, and the number of bits needed to represent W is only logarithmic in W.

When using a one-dimensional array, iterate capacity downward. Iterating upward would allow the current item to be reused, turning the solution into an unbounded knapsack variant.

## Distinguishing DP from brute force

Brute force may enumerate every combination. DP identifies repeated states and computes each state once.

Ask:
- Do different paths reach the same subproblem?
- Can the optimal answer be built from optimal answers to smaller subproblems?
- Can I define a state that contains exactly the information needed for future decisions?

If not, do not force a DP table onto the problem.

## Self-check

1. Which Fibonacci calls are repeated in naive recursion?
2. What does dp[i][j] mean in Edit Distance?
3. Why are the first row and column of the Edit Distance table known?
4. Why can matrix parenthesization affect multiplication cost?
5. Why does Matrix Chain Multiplication try every split point?
6. Why must 0/1 Knapsack iterate capacity downward in a one-dimensional DP array?
7. Why is O(nW) called pseudo-polynomial?

## Java coverage

The Java project includes iterative Fibonacci, Edit Distance, Matrix Chain Multiplication cost, and a space-optimized 0/1 Knapsack value calculation in DynamicProgrammingAlgorithms. The knapsack method returns the optimal value; reconstructing the chosen item set requires a decision table or a second reconstruction strategy.
