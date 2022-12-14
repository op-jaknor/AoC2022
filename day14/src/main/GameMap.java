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
        while (!dropOneSand().equals(sandDroppingPosition)) {

        }

        return unitsOfSandDropped;
    }

    private Position dropOneSand() {
        final Position sandPosition = sandDroppingPosition.copyOf();
        unitsOfSandDropped++;
        return findRestingPosition(sandPosition);
    }

    private Position findRestingPosition(final Position sandPosition) {
        if (isMovable(sandPosition)) {
            return findRestingPosition(move(sandPosition));
        }
        if (atRest(sandPosition)) {
            setBlocked(sandPosition);
            return sandPosition;
        } else {
            throw new IllegalStateException();
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
        return position.y > lowestBlockedPosition.y+1 || blockedPositions.contains(position);
    }

    private boolean isFree(final Position position) {
        return !isBlocked(position);
    }
}
