package ua.batimyk.fridgesolver;

/**
 * Created by N on 05/13/16.
 * FridgeSolver
 */
class FridgeNode {
    private Fridge fridge;
    private FridgeNode parentFridgeNode;

    private int x;
    private int y;


    FridgeNode(Fridge fridge) {
        this.fridge = fridge;
    }


    Fridge getFridge() {
        return fridge;
    }

    @Override
    public String toString() {
        return "FridgeNode{" +
                "fridge=" + fridge +
                ", parentFridgeNode=" + parentFridgeNode +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FridgeNode that = (FridgeNode) o;

        return fridge.equals(that.fridge);

    }

    FridgeNode getParentFridgeNode() {
        return parentFridgeNode;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public int hashCode() {

        return fridge.hashCode();

    }

    FridgeNode(FridgeNode parentFridgeNode, int x, int y) {
        this.parentFridgeNode = parentFridgeNode;
        fridge = new Fridge(parentFridgeNode.getFridge());
        fridge.turnHandle(x, y);
        this.x = x;
        this.y = y;
    }
}
