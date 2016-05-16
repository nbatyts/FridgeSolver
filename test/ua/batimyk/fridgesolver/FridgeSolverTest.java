package ua.batimyk.fridgesolver;

import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by N on 05/16/16.
 * FridgeSolver
 */
public class FridgeSolverTest {
    byte[][] positions4x4 = {{-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}};
    byte[][] pilotBroPositions = {{1, -1, 1, -1}, {-1, 1, 1, 1}, {-1, 1, 1, -1}, {-1, 1, -1, 1}};
    byte[][] positions4Open = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
    byte[][] positions4_1turn = {{1, -1, 1, 1}, {1, -1, 1, 1}, {-1, -1, -1, -1}, {1, -1, 1, 1}};
    byte[][] positions4_2turn = {{1, -1, 1, -1}, {-1, -1, -1, 1}, {1, -1, 1, -1}, {-1, 1, -1, -1}};


    Fridge openFridge4x4 = new Fridge(positions4Open);
    FridgeNode rootFridgeNode = new FridgeNode(new Fridge(positions4Open));
    FridgeSolver fridgeSolver = new FridgeSolver(rootFridgeNode);


    @Test
    public void testGetSolutionChain4x4_Open() throws Exception {
        fridgeSolver.setRootFridgeNode(new FridgeNode(new Fridge(positions4Open)));
        ArrayList<FridgeNode> fridgeNodes = fridgeSolver.getSolutionChain();

        assertEquals(fridgeNodes.size(), 0);
    }

    @Test
    public void testGetSolutionChain4x4_1turn() throws Exception {
        fridgeSolver.setRootFridgeNode(new FridgeNode(new Fridge(positions4_1turn)));
        ArrayList<FridgeNode> fridgeNodes = fridgeSolver.getSolutionChain();

        assertEquals(fridgeNodes.size(), 1);
        assertEquals(fridgeNodes.get(0).getFridge(), openFridge4x4);
    }

    @Test
    public void testGetSolutionChain4x4_2turn() throws Exception {
        fridgeSolver.setRootFridgeNode(new FridgeNode(new Fridge(positions4_2turn)));
        ArrayList<FridgeNode> fridgeNodes = fridgeSolver.getSolutionChain();

        assertEquals(fridgeNodes.size(), 2);
        assertEquals(fridgeNodes.get(1).getFridge(), openFridge4x4);
    }

    @Test
    public void testGetSolutionChain4x4_Pilot() throws Exception {
        fridgeSolver.setRootFridgeNode(new FridgeNode(new Fridge(pilotBroPositions)));
        ArrayList<FridgeNode> fridgeNodes = fridgeSolver.getSolutionChain();

        assertEquals(fridgeNodes.size(), 9);
        assertEquals(fridgeNodes.get(fridgeNodes.size() - 1).getFridge(), openFridge4x4);
    }

    @Test
    public void testGetSolutionChain4x4() throws Exception {
        fridgeSolver.setRootFridgeNode(new FridgeNode(new Fridge(positions4x4)));
        ArrayList<FridgeNode> fridgeNodes = fridgeSolver.getSolutionChain();

        assertEquals(fridgeNodes.size(), 16);
        assertEquals(fridgeNodes.get(fridgeNodes.size() - 1).getFridge(), openFridge4x4);
    }

    @Test(expected = AssertionError.class)
    public void testGetSolutionChain4x4_Negative() throws Exception {
        fridgeSolver.setRootFridgeNode(new FridgeNode(new Fridge(positions4x4)));
        ArrayList<FridgeNode> fridgeNodes = fridgeSolver.getSolutionChain();

        assertEquals(fridgeNodes.size(), 17);
        assertEquals(fridgeNodes.get(fridgeNodes.size() - 1).getFridge(), openFridge4x4);
    }
}