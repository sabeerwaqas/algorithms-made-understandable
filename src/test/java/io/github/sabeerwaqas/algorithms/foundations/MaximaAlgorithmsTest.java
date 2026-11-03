package io.github.sabeerwaqas.algorithms.foundations;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class MaximaAlgorithmsTest {
    @Test
    void bothAlgorithmsReturnTheSameMaximalPoints() {
        List<Point2D> input = List.of(
                new Point2D(2, 5), new Point2D(3, 13), new Point2D(4, 11),
                new Point2D(5, 1), new Point2D(7, 7), new Point2D(9, 10),
                new Point2D(10, 5), new Point2D(12, 12), new Point2D(14, 10),
                new Point2D(15, 7));
        Set<Point2D> expected = Set.of(
                new Point2D(3, 13), new Point2D(12, 12),
                new Point2D(14, 10), new Point2D(15, 7));
        assertEquals(expected, Set.copyOf(BruteForceMaxima.findMaxima(input)));
        assertEquals(expected, Set.copyOf(PlaneSweepMaxima.findMaxima(input)));
    }

    @Test
    void emptyAndSinglePointInputsAreHandled() {
        assertTrue(BruteForceMaxima.findMaxima(List.of()).isEmpty());
        assertTrue(PlaneSweepMaxima.findMaxima(List.of()).isEmpty());
        List<Point2D> single = List.of(new Point2D(-2, 4));
        assertEquals(single, BruteForceMaxima.findMaxima(single));
        assertEquals(single, PlaneSweepMaxima.findMaxima(single));
    }

    @Test
    void duplicateCoordinatesAreReturnedOnce() {
        List<Point2D> input = List.of(new Point2D(1, 3), new Point2D(1, 3), new Point2D(2, 2));
        assertEquals(2, BruteForceMaxima.findMaxima(input).size());
        assertEquals(2, PlaneSweepMaxima.findMaxima(input).size());
    }

    @Test
    void equalXAndEqualYCoordinatesAreHandled() {
        List<Point2D> input = List.of(new Point2D(1, 2), new Point2D(1, 5),
                new Point2D(3, 5), new Point2D(4, 1));
        Set<Point2D> expected = Set.of(new Point2D(3, 5), new Point2D(4, 1));
        assertEquals(expected, Set.copyOf(BruteForceMaxima.findMaxima(input)));
        assertEquals(expected, Set.copyOf(PlaneSweepMaxima.findMaxima(input)));
    }
}
