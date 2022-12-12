package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class Monkey {
    private List<Long> items;
    private final Function<Long, Long> worryLevelChange;
    private final Function<Long, Boolean> decidingWhoToThrowTo;
    private long itemsInspected;
    public final int trueMonkeyNumber;
    public final int falseMonkeyNumber;
    private Monkey trueMonkey;
    private Monkey falseMonkey;

    public Monkey(final List<Long> items, final Function<Long, Long> worryLevelChange, final Function<Long, Boolean> decidingWhoToThrowTo, final int trueMonkeyNumber, final int falseMonkeyNumber) {
        this.items = new ArrayList<>(items);
        this.worryLevelChange = worryLevelChange;
        this.decidingWhoToThrowTo = decidingWhoToThrowTo;
        this.trueMonkeyNumber = trueMonkeyNumber;
        this.falseMonkeyNumber = falseMonkeyNumber;
        this.itemsInspected = 0;
    }

    public void setFalseMonkey(final Monkey monkey) {
        if (falseMonkey == null) {
            this.falseMonkey = monkey;
        } else {
            throw new IllegalStateException("Can't set falseMonkey twice?");
        }
    }

    public void setTrueMonkey(final Monkey monkey) {
        if (trueMonkey == null) {
            this.trueMonkey = monkey;
        } else {
            throw new IllegalStateException("Can't set trueMonkey twice?");
        }
    }

    public void inspectItems() {
        for (long item : items) {
            final long itemWithNewWorryLevel = worryLevelChange.apply(item) / 3; // Part 1
            //final long itemWithNewWorryLevel = worryLevelChange.apply(item); // Part 2
            itemsInspected++;
            if (decidingWhoToThrowTo.apply(itemWithNewWorryLevel)) {
                trueMonkey.catchItem(itemWithNewWorryLevel);
            } else {
                falseMonkey.catchItem(itemWithNewWorryLevel);
            }
        }
        items = new ArrayList<>();
    }

    public long getItemsInspected() {
        return itemsInspected;
    }

    private void catchItem(final Long item) {
        items.add(item);
    }
}
