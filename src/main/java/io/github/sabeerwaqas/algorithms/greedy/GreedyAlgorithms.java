package io.github.sabeerwaqas.algorithms.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/** A greedy algorithm with a well-defined correctness condition. */
public final class GreedyAlgorithms {
    private GreedyAlgorithms() {}

    public record Activity(int start, int finish) {
        public Activity {
            if (finish < start) throw new IllegalArgumentException("finish must not be before start");
        }
    }

    /** Maximum-cardinality activity selection; next.start >= last.finish is compatible. */
    public static List<Activity> selectActivities(List<Activity> activities) {
        Objects.requireNonNull(activities, "activities must not be null");
        List<Activity> sorted = activities.stream()
                .map(activity -> Objects.requireNonNull(activity, "activity must not be null"))
                .sorted(Comparator.comparingInt(Activity::finish).thenComparingInt(Activity::start))
                .toList();
        List<Activity> selected = new ArrayList<>();
        int lastFinish = Integer.MIN_VALUE;
        for (Activity activity : sorted) {
            if (selected.isEmpty() || activity.start() >= lastFinish) {
                selected.add(activity);
                lastFinish = activity.finish();
            }
        }
        return List.copyOf(selected);
    }
}
