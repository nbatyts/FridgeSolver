package ua.batimyk.fridgesolver;

import java.util.*;

/**
 * Created by N on 05/13/16.
 * FridgeSolver
 */
public class FridgeSolver {
    private FridgeNode solvedFridgeNode;
    private FridgeNode rootFridgeNode;

    public FridgeSolver(FridgeNode rootFridgeNode) {
        this.rootFridgeNode = rootFridgeNode;
    }

    private List<FridgeNode> turnAllHandlers(FridgeNode parentFridgeNode) {
        List<FridgeNode> fridgeNodes = new ArrayList<>();

        for (int y = 0; y < parentFridgeNode.getFridge().getHandlersPositions().length; y++) {
            for (int x = 0; x < parentFridgeNode.getFridge().getHandlersPositions()[y].length; x++) {
                fridgeNodes.add(new FridgeNode(parentFridgeNode, x, y));
            }
        }
        return fridgeNodes;
    }

    private void iterateFridge(Set<FridgeNode> parentFridgeNodes) {
        boolean isOpen = false;
        Set<FridgeNode> fridgeNodes = new HashSet<>();

        for (FridgeNode pfn : parentFridgeNodes) {
            for (FridgeNode fn : turnAllHandlers(pfn)) {
                if (fn.getFridge().isOpen()) {
                    isOpen = true;
                    solvedFridgeNode = fn;
                    break;
                }
                fridgeNodes.add(fn);
            }
        }

        if (!isOpen) {
            iterateFridge(fridgeNodes);
        }
    }

    private ArrayList<FridgeNode> getSolutionChain() {
        Set<FridgeNode> parentFridgeNodes = new HashSet<>();

        parentFridgeNodes.add(rootFridgeNode);
        iterateFridge(parentFridgeNodes);

        FridgeNode fridgeNode = solvedFridgeNode;
        ArrayList<FridgeNode> fridgeNodes = new ArrayList<>();

        while (!rootFridgeNode.equals(fridgeNode)) {
            fridgeNodes.add(fridgeNode);
            fridgeNode = fridgeNode.getParentFridgeNode();
        }
        Collections.reverse(fridgeNodes);

        return fridgeNodes;
    }

    public void printSolutionTurns() {

        System.out.println(rootFridgeNode.getFridge());
        System.out.println();
        int count = 1;
        for (FridgeNode fn : getSolutionChain()) {
            System.out.println(count++ + ":[" + fn.getX() + "," + fn.getY() + "]" + fn.getFridge());
        }
    }

    public static void main(String[] args) {

        byte[][] positions2x2 = {{-1, -1}, {-1, 1}};
        byte[][] positions3x3 = {{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};
        byte[][] positions4x4 = {{-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}};
        byte[][] pilotBroPositions = {{1, -1, 1, -1}, {-1, 1, 1, 1}, {-1, 1, 1, -1}, {-1, 1, -1, 1}};
        byte[][] positions4Open = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
        byte[][] positions4_1turn = {{1, -1, 1, 1}, {1, -1, 1, 1}, {-1, -1, -1, -1}, {1, -1, 1, 1}};
        byte[][] initPositions = {{1, 1, -1, 1}, {1, 1, -1, 1}, {-1, -1, 1, -1}, {1, 1, 1, -1}};
        byte[][] positions4x4_1 = {{-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, 1}};
        byte[][] positions4x4_2 = {{-1, -1, -1, -1}, {-1, 1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}};

        FridgeNode rootFridgeNode = new FridgeNode(new Fridge(pilotBroPositions));
        FridgeSolver fridgeSolver = new FridgeSolver(rootFridgeNode);

        fridgeSolver.printSolutionTurns();
    }
}
