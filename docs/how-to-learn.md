# How to learn from this repository

This repository is designed for independent study. Follow the steps in order; do not jump directly to the Java solution.

## A repeatable study session

1. **Read the problem.** Restate the input, output, and constraints in your own words.
2. **Explain the need.** What makes this problem worth solving? What would a straightforward solution do?
3. **Work one example by hand.** Write down the state after each important step.
4. **Read the algorithm steps.** Understand the idea before Java syntax.
5. **Predict the result.** Work out what the code should return before running it.
6. **Implement or inspect the Java version.** Trace it against your handwritten example.
7. **Analyse it.** Count the operations that grow with input size; simplify the growth rate.
8. **Try the self-check.** Explain why the answer is correct rather than treating it as a memory test.

## What the labels mean

- **Concept guide:** a focused explanation with an example and questions.
- **Java example:** runnable code is included in the Maven project.
- **Animation:** an original visual shows an algorithm's changing state.
- **Further implementation:** the concept is explained, but a tested Java implementation is not included yet.

A topic is implementation-complete only when code, meaningful edge-case tests, and complexity notes are present together.

## How to use an animation

Watch it once without reading the explanation. On the second pass, pause after each meaningful change and answer:
- What information is being maintained?
- Why is this item selected, compared, moved, or removed?
- What invariant should still be true?
- What would change if the input were different?

A visual is an aid to reasoning, not a substitute for it.

## What to do when stuck

Reduce the example size. For a graph, use three or four vertices. For an array, use four or five values. For dynamic programming, fill a tiny table by hand. If you cannot explain what each variable represents, return to the problem statement rather than adding more code.

## Mastery check

You understand a topic when you can state the problem and its assumptions, explain the core idea without reading, trace a small input, implement it independently, justify time and space complexity, and identify a limitation or edge case.
