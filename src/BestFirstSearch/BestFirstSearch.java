package BestFirstSearch;

import java.io.*;
import java.util.*;

public class BestFirstSearch {
    public int n, m;
    public ArrayList<Edge>[] adj;
    public boolean[] visited;
    public String inputFilePath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\BestFirstSearch\\input.txt";
    public String outputFilePath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\BestFirstSearch\\output.txt";
    public FileWriter writer;

    public BestFirstSearch() {
        try {
            writer = new FileWriter(outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void input() {
        try {
            FileInputStream fis = new FileInputStream(inputFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            String[] tokens = line.split(" ");
            n = Integer.parseInt(tokens[0]);
            m = Integer.parseInt(tokens[1]);
            adj = new ArrayList[1001];
            visited = new boolean[1001];

            for (int i = 0; i < 1001; i++) {
                adj[i] = new ArrayList<>();
            }
            Arrays.fill(visited, false);
            for (int i = 0; i < m; i++) {
                line = br.readLine();
                tokens = line.split(" ");
                char source = tokens[0].charAt(0);
                int cost = Integer.parseInt(tokens[1]);
                char destination = tokens[2].charAt(0);
                adj[source - 'A'].add(new Edge(destination, cost));
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bestFirstSearch(char start, char goal) {
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));
        Map<Character, Character> p = new HashMap<>();
        Map<Character, Integer> costs = new HashMap<>();
        q.add(new Node(start, 0, uocTinh(start, goal)));
        p.put(start, null);
        costs.put(start, 0);

        System.out.println("==========================================================================================");
        System.out.println("Phat trien trang thai\tTrang thai ke\t\t\tDanh sach L");
        System.out.println("==========================================================================================");

        while (!q.isEmpty()) {
            Node current = q.poll();
            char currentChar = current.tenDinh;
            int currentCost = costs.getOrDefault(currentChar, 0);

            if (currentChar == goal) {
                System.out.println(current.tenDinh + "-" + current.heuristic + "\t\t\t| Trạng thái kết thúc");
                System.out.println("Found " + goal + " !");
                inDuongDi(p, goal);
                return;
            }
            System.out.print(currentChar + "-" + current.heuristic + "\t\t\t| ");
            List<Node> children = new ArrayList<>();
            if (!visited[currentChar - 'A']) {
                visited[currentChar - 'A'] = true;
                for (Edge edge : adj[currentChar - 'A']) {
                    if (!visited[edge.dinhDen - 'A']) {
                        int newCost = currentCost + edge.cost;
                        int heuristic = uocTinh(edge.dinhDen, goal);
                        Node childNode = new Node(edge.dinhDen, newCost, heuristic);
                        q.add(childNode);
                        p.put(edge.dinhDen, currentChar);
                        costs.put(edge.dinhDen, newCost);
                        children.add(childNode);
                    }
                }
            }
            for (Node child : children) {
                System.out.print(child.tenDinh + "-" + child.heuristic + " ");
            }
            System.out.print("\t\t\t| ");
            PriorityQueue<Node> sortedQueue = new PriorityQueue<>(q);
            while (!sortedQueue.isEmpty()) {
                Node node = sortedQueue.poll();
                System.out.print(node.tenDinh + "-" + node.heuristic + " ");
            }
            System.out.println();
        }
        System.out.println("Not Found !");
    }

    private int uocTinh(char node, char goal) {
        Map<Character, Integer> hm = new HashMap<>();
        hm.put('A', 20);
        hm.put('B', 0);
        hm.put('C', 15);
        hm.put('D', 6);
        hm.put('E', 7);
        hm.put('F', 10);
        hm.put('G', 5);
        hm.put('H', 3);
        hm.put('I', 8);
        hm.put('K', 12);
        return hm.getOrDefault(node, Integer.MAX_VALUE);
    }

    private void inDuongDi(Map<Character, Character> path, char goal) {
        try {
            List<Character> duongDi = new ArrayList<>();
            Character v = goal;
            while (v != null) {
                duongDi.add(v);
                v = path.get(v);
            }
            Collections.reverse(duongDi);
            System.out.println("=> " + duongDi);
            writer.write("=> " + duongDi + System.lineSeparator());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


