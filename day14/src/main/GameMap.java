package main;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GameMap {

    private final Set<Position> blockedPositions;
    private final Position sandDroppingPosition;
    private final Position lowestBlockedPosition;
    private int unitsOfSandDropped;

    public GameMap(final Position sandDroppingPosition, final Set<Position> initialBlockedPositions) {
        this.sandDroppingPosition = sandDroppingPosition;
        this.blockedPositions = new HashSet<>(initialBlockedPositions);
        lowestBlockedPosition = blockedPositions.stream().max(Comparator.comparingInt(p -> p.y)).get();
        unitsOfSandDropped = 0;
    }

    public int calculateSandsDropped() {
        while (dropOneSand()) {

        }

        return unitsOfSandDropped;
    }

    private boolean dropOneSand() {
        final Position sandPosition = sandDroppingPosition.copyOf();
        Optional<Position> restingPosition = findRestingPosition(sandPosition);
        if (restingPosition.isPresent()) {
            unitsOfSandDropped++;
            return true;
        } else {
            return false;
        }
    }

    private Optional<Position> findRestingPosition(final Position sandPosition) {
        if (isMovable(sandPosition) && sandPosition.y <= lowestBlockedPosition.y) {
            return findRestingPosition(move(sandPosition));
        }
        if (atRest(sandPosition)) {
            blockedPositions.add(sandPosition);
            return Optional.of(sandPosition);
        } else {
            return Optional.empty();
        }
    }

    private Position move(final Position position) {
        if (isFree(position.getPositionUnder())) {
            return position.getPositionUnder();
        } else if (isFree(position.getPositionDownLeft())) {
            return position.getPositionDownLeft();
        } else if (isFree(position.getPositionDownRight())) {
            return position.getPositionDownRight();
        }
        throw new IllegalStateException();
    }

    private boolean atRest(final Position position) {
        return !isMovable(position);
    }

    private boolean isMovable(final Position position) {
        if (isFree(position.getPositionUnder())) {
            return true;
        } else if (isFree(position.getPositionDownLeft())) {
            return true;
        } else if (isFree(position.getPositionDownRight())) {
            return true;
        } else {
            return false;
        }
    }

    private void setBlocked(final Position position) {
        blockedPositions.add(position);
    }

    private boolean isBlocked(final Position position) {
        return blockedPositions.contains(position);
    }

    private boolean isFree(final Position position) {
        return !blockedPositions.contains(position);
    }
}
