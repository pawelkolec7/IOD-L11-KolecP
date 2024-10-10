package pl.poznan.put.cs.io.errors.processors;

import java.util.*;

public class BFSProcessor {

    List<Integer> list = new ArrayList<Integer>();
    private Set<Integer> visitedNodes = new HashSet<>();
    private Queue<Integer> queue = new LinkedList<>();
    private List<Integer> result = new ArrayList<>();
    private int[][] matrix;

    /**
     * Starts the BFS algorithms for the whole graph
     * Klasa BFSProcessor przeszukuje graf, zaczynając od węzła z najniższym indeksem, który ma sąsiadów.
     * BFS eksploruje węzły i ich sąsiadów, dodając odwiedzone węzły do listy wynikowej, a nieodwiedzone węzły są przetwarzane iteracyjnie.
     * Paweł Kolec
     * usunąłem tworzenie nowego obietku new Integer(beginningNode).
     * @param matrix holds the graph
     * @return list of nodes
     */
    public List<Integer> process(int[][] matrix) {
        this.matrix = matrix;
        int beginningNode = getBeginningNode();
        if (beginningNode == -1) {
            return null;
        }
        queue.add(beginningNode);
        search(beginningNode);
        result.addAll(findNodesWithoutNeighbors());
        return result;
    }

    /**
     * Runs the BFS algorithm for the given node
     * Paweł Kolec
     * usunąłem tworzenie nowego obiektu new Integer(nodeNo).
     * usunąłem dodawanie wszystkich sąsiadów do odwiedzonych węzłów przed ich przetworzeniem.
     * zmianiłem sposob przetwarzania kolejnych węzłów w kolejce na iteracyjny.
     * upeweniam sie w ifie e węzeł zostanie odwiedzony i przetworzony tylko wtedy, gdy nie był wcześniej odwiedzony
     * @param nodeNo number of the node
     */
    private void search(int nodeNo) {
        visitedNodes.add(nodeNo);
        result.add(nodeNo);
        List<Integer> allValidNeighbors = getAllValidNeighbors(nodeNo);
        queue.addAll(allValidNeighbors);

        while (!queue.isEmpty()) {
            Integer currentNode = queue.poll();
            if (currentNode != null && !visitedNodes.contains(currentNode)) {
                search(currentNode);
            }
        }
    }

    /**
     * Looks for the node with the lowest index number, which has at least
     * one neighbor
     *
     * @return the lowest possible index of the node, which has at least one
     * neighbor
     */
    private int getBeginningNode() {
        for (int i = 0; i < matrix.length; i++) {
            if (getAllValidNeighbors(i).size() > 0) {
                return i; // Return the first node with neighbors
            }
        }
        return -1; // No node found with neighbors
    }

    /**
     * Returns the neighbors of a node which haven't been visited yet
     * Paweł Kolec
     * Zmieniłem kod i sprawdzam czy sąsiad nie został odwiedzony, zamina na not
     * @param nodeNo node index
     * @return the list of node indexes which haven't been visited yet
     */
    private List<Integer> getAllValidNeighbors(int nodeNo) {
        List<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[nodeNo][i] == 1 && !visitedNodes.contains(i)) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    /**
     * Finds all nodes without neighbors
     * Dodałem sprawdzenie czy getAllValidNeighbors(i) jest puste.
     * @return list of nodes indexes without neighbors
     */
    private List<Integer> findNodesWithoutNeighbors() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            if (!visitedNodes.contains(i) && getAllValidNeighbors(i).isEmpty()) {
                result.add(i); // Add nodes without neighbors
            }
        }
        return result;
    }
}
