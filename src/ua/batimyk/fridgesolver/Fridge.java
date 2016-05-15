package ua.batimyk.fridgesolver;

import java.util.Arrays;

/**
 * Created by N on 05/13/16.
 * FridgeSolver
 */
class Fridge {
    private byte[][] handlersPositions;

    Fridge(byte[][] handlersPositions) {
        this.handlersPositions = new byte[handlersPositions.length][];

        for (int x = 0; x < handlersPositions.length; x++) {
            this.handlersPositions[x] = new byte[handlersPositions[0].length];
            System.arraycopy(handlersPositions[x], 0, this.handlersPositions[x], 0, handlersPositions[0].length);
        }
    }

    Fridge(Fridge fridge) {
        this(fridge.getHandlersPositions());
    }


    void turnHandle(int x, int y) {

        for (int i = 0; i < handlersPositions.length; i++) {
            handlersPositions[i][x] *= -1;
        }
        for (int i = 0; i < handlersPositions[x].length; i++) {
            handlersPositions[y][i] *= -1;
        }
        handlersPositions[y][x] *= -1;
    }

    boolean isOpen() {

        for (byte[] yAxis : handlersPositions) {
            for (byte handlerState : yAxis) {
                if (handlerState != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    byte[][] getHandlersPositions() {
        return handlersPositions;
    }

    @Override

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (byte[] xAxis : handlersPositions) {
            s.append('\n');
            for (byte handlerState : xAxis) {
                if (handlerState == 1) {
                    s.append("1");
                } else if (handlerState == -1) {
                    s.append("0");
                }
            }
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fridge fridge = (Fridge) o;

        return Arrays.deepEquals(handlersPositions, fridge.handlersPositions);

    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(handlersPositions);
    }

}
