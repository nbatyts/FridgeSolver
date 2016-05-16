package ua.batimyk.fridgesolver;

import java.util.*;

/**
 * Created by N on 05/13/16.
 * FridgeSolver
 */
public class FridgeSolver {
    private FridgeNode solvedFridgeNode;
    private FridgeNode rootFridgeNode;

    public void setRootFridgeNode(FridgeNode rootFridgeNode) {
        this.rootFridgeNode = rootFridgeNode;
    }

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

        Iterator<FridgeNode> parentFridgeNodesIterator = parentFridgeNodes.iterator();
        while (parentFridgeNodesIterator.hasNext() && !isOpen) {
            FridgeNode pfn = parentFridgeNodesIterator.next();
            if (pfn.getFridge().isOpen()) {
                isOpen = true;
                solvedFridgeNode = pfn;
            } else {
                Iterator<FridgeNode> fridgeNodesIterator = turnAllHandlers(pfn).iterator();
                while (fridgeNodesIterator.hasNext() && !isOpen) {
                    FridgeNode fn = fridgeNodesIterator.next();
                    if (fn.getFridge().isOpen()) {
                        isOpen = true;
                        solvedFridgeNode = fn;
                    }
                    fridgeNodes.add(fn);
                }
            }
        }

        if (!isOpen) {
            iterateFridge(fridgeNodes);
        }
    }

    public ArrayList<FridgeNode> getSolutionChain() {
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

        System.out.println("Initial state: ");
        System.out.println(rootFridgeNode.getFridge());
        System.out.println("\nSolution:\n");
        int count = 1;
        for (FridgeNode fn : getSolutionChain()) {
            System.out.println(count++ + ":[" + fn.getX() + "," + fn.getY() + "]" + fn.getFridge());
        }
    }

    public static void main(String[] args) {
        byte[][] pilotBroPositions = {{1, -1, 1, -1}, {-1, 1, 1, 1}, {-1, 1, 1, -1}, {-1, 1, -1, 1}};
        FridgeNode rootFridgeNode = new FridgeNode(new Fridge(pilotBroPositions));
        FridgeSolver fridgeSolver = new FridgeSolver(rootFridgeNode);

        fridgeSolver.printSolutionTurns();
    }
}
