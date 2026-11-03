# 6. Complexity Theory

**VU LMS index:** 44–46  
**Prerequisites:** graph basics, discrete mathematics, and the meaning of polynomial-time algorithms.

**New to a term?** See the [plain-language glossary](../docs/glossary.md).

Complexity theory studies how much computational resource problems require as input size grows. This chapter introduces decision problems, P and NP, polynomial-time reductions, and classic examples.

## 1. P and NP

For this introduction, focus on **decision problems**: questions with a yes/no answer, rather than questions asking for a particular arrangement or numeric optimum.

### P

P is the class of decision problems that can be solved by a **deterministic algorithm** (one that follows defined steps, without guessing a path) in **polynomial time**, such as O(n), O(n²), or O(n³).

### NP

NP is the class of decision problems for which a proposed yes-answer can be checked in polynomial time using a **certificate** — supporting information that lets us verify the answer.

Example: for graph coloring, a proposed assignment can be checked by inspecting every edge and confirming adjacent vertices have different colours.

**NP does not mean “non-polynomial.”** It means nondeterministic polynomial time, equivalently polynomial-time verifiability for decision problems.

Every problem in P is also in NP, because a solution can be computed and then verified. Whether P = NP is an open problem; no proof is known that they are equal or different.

## 2. Polynomial-time reductions

A **reduction** translates an instance of one problem `A` into an instance of another problem `B` while preserving the answer. In plain language, it turns a question about `A` into an equivalent question about `B`.

A ≤p B means:
- a transformation from A instances to B instances can be computed in polynomial time;
- the original instance is a yes-instance exactly when the transformed instance is a yes-instance.

The direction matters. If A ≤p B and B has a polynomial-time solver, then A can also be solved in polynomial time.

To show B is NP-hard, reduce a known NP-hard problem A **to B**. Reducing B to A does not establish that B is at least as hard as A.

## 3. Classic decision problems

### Boolean satisfiability (SAT)

Input: a Boolean formula. Question: is there an assignment of true/false values that makes the formula true?

A proposed assignment can be checked in polynomial time. SAT is NP-complete.

### Graph coloring

Input: a graph and a number k. Question: can each vertex be assigned one of at most k colours so adjacent vertices receive different colours?

A proposed coloring is easy to verify. The general k-coloring decision problem is NP-complete for k ≥ 3; 2-colorability is solvable in polynomial time by checking bipartiteness.

### Independent set

Input: a graph and integer k. Question: is there a set of at least k vertices with no edge connecting any pair in the set?

A proposed set can be checked in polynomial time. The decision problem is NP-complete.

## Terms that must not be confused

- **NP-hard:** at least as hard as every problem in NP, under the reduction being used. It does not have to be in NP itself.
- **NP-complete:** both in NP and NP-hard — it is in the class whose proposed yes-answers can be checked efficiently, and it is at least as hard as every problem in NP.
- **P:** efficiently solvable in polynomial time.
- **NP:** yes-certificates can be verified in polynomial time.

## Self-check

1. Does NP mean “not polynomial”? Explain.
2. Why is P a subset of NP?
3. What does a certificate provide?
4. In A ≤p B, which problem is being transformed into which?
5. What two properties must a problem have to be NP-complete?
6. Why is checking a proposed graph coloring easier than finding one?
7. Does a polynomial-time reduction prove that two problems have identical practical runtimes?
