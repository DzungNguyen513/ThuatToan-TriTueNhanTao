package A_sao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class A_sao {

    private Map<String, Map<String, Node>> graph;

    public static class Node {
        String name;
        int cost;
        int heuristic;

        Node(String name, int cost, int heuristic) {
            this.name = name;
            this.cost = cost;
            this.heuristic = heuristic;
        }
    }

    public A_sao() {
        this.graph = new HashMap<>();
    }

    public void input(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");

                String node = parts[0];
                if (parts.length == 2) {
                    graph.put(node, new HashMap<>());
                } else {
                    Map<String, Node> neighbors = new HashMap<>();
                    for (int i = 2; i < parts.length; i += 3) {
                        String neighbor = parts[i];
                        int cost = Integer.parseInt(parts[i + 1]);
                        int heuristic = Integer.parseInt(parts[i + 2]);
                        neighbors.put(neighbor, new Node(neighbor, cost, heuristic));
                    }
                    graph.put(node, neighbors);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> astar(String start, String goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost + node.heuristic));
        Set<String> closedSet = new HashSet<>();
        Map<String, Integer> gScore = new HashMap<>();
        Map<String, String> cameFrom = new HashMap<>();

        for (String node : graph.keySet()) {
            gScore.put(node, Integer.MAX_VALUE);
        }

        gScore.put(start, 0);
        openSet.add(new Node(start, 0, 0));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.name.equals(goal)) {
                return reconstructPath(cameFrom, goal);
            }

            closedSet.add(current.name);

            for (Map.Entry<String, Node> entry : graph.get(current.name).entrySet()) {
                String neighbor = entry.getKey();
                Node data = entry.getValue();

                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGScore = gScore.get(current.name) + data.cost;

                if (tentativeGScore < gScore.get(neighbor)) {
                    gScore.put(neighbor, tentativeGScore);
                    cameFrom.put(neighbor, current.name);
                    openSet.add(new Node(neighbor, gScore.get(neighbor), graph.get(neighbor).get(neighbor).heuristic));
                }
            }
        }

        return null; // No path found
    }

    private List<String> reconstructPath(Map<String, String> cameFrom, String current) {
        List<String> path = new ArrayList<>();
        while (cameFrom.containsKey(current)) {
            path.add(current);
            current = cameFrom.get(current);
        }
        Collections.reverse(path);
        return path;
    }
}
