package BestFirstSearch;

import java.io.*;
import java.util.*;

public class BestFirstSearch {
    public int n, m;
    public ArrayList<Edge>[] adj;
    public boolean[] visited;
    public String inPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\BestFirstSearch\\input.txt";
    public String outPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\BestFirstSearch\\output.txt";
    public FileWriter writer;

    public BestFirstSearch() {
        try {
            writer = new FileWriter(outPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void input() {
        try {
            FileInputStream fis = new FileInputStream(inPath);
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
                char s = tokens[0].charAt(0);
                int cost = Integer.parseInt(tokens[1]);
                char destination = tokens[2].charAt(0);
                adj[s - 'A'].add(new Edge(destination, cost));
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bestFirstSearch(char a, char b) throws IOException {
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));
        Map<Character, Character> p = new HashMap<>();
        Map<Character, Integer> costs = new HashMap<>();
        q.add(new Node(a, 0, uocTinh(a, b)));
        p.put(a, null);
        costs.put(a, 0);

        System.out.println("==========================================================================================");
        writer.write("==========================================================================================" + System.lineSeparator());
        System.out.println("Phat trien trang thai\t Trang thai ke\t\t\t Danh sach L");
        writer.write("Phat trien trang thai\t Trang thai ke\t\t\t Danh sach L"+ System.lineSeparator());
        System.out.println("==========================================================================================");
        writer.write("==========================================================================================" + System.lineSeparator());

        while (!q.isEmpty()) {
            Node x = q.poll();
            char v = x.tenDinh;
            int vCost = costs.getOrDefault(v, 0);

            if (v == b) {
                System.out.println(x.tenDinh + "-" + x.heuristic + "\t\t\t| Trạng thái kết thúc");
                writer.write(x.tenDinh + "-" + x.heuristic + "\t\t\t| Trạng thái kết thúc" + System.lineSeparator());
                System.out.println("Found " + b + " !");
                writer.write("Found " + b + " !" + System.lineSeparator());
                inDuongDi(p, b);
                return;
            }
            String outLine = v + "-" + x.heuristic + "\t\t\t| ";
            System.out.print(outLine);
            writer.write(outLine);
            
            List<Node> child = new ArrayList<>();
            String ttKe = ""; 

            if (!visited[v - 'A']) {
                visited[v - 'A'] = true;
                for (Edge edge : adj[v - 'A']) {
                    if (!visited[edge.dinhDen - 'A']) {
                        int newCost = vCost + edge.cost;
                        int heuristic = uocTinh(edge.dinhDen, b);
                        Node dinhKe = new Node(edge.dinhDen, newCost, heuristic);
                        q.add(dinhKe);
                        p.put(edge.dinhDen, v);
                        costs.put(edge.dinhDen, newCost);
                        child.add(dinhKe);
                        ttKe += dinhKe.tenDinh + "-" + dinhKe.heuristic + " ";
                    }
                }
            }
            if (ttKe.isEmpty()) {
            	ttKe = "| ";
                int padding = 32 - ttKe.length(); 
                System.out.print(String.format("%-" + padding + "s%s", " ", ttKe));
                writer.write(String.format("%-" + padding + "s%s", " ", ttKe));
            } else {
                System.out.print(ttKe);
                writer.write(ttKe);
                outLine = "\t\t\t| ";
                System.out.print(outLine);
                writer.write(outLine);
            }
            PriorityQueue<Node> sortedQueue = new PriorityQueue<>(q);
            while (!sortedQueue.isEmpty()) {
                Node node = sortedQueue.poll();
                outLine = node.tenDinh + "-" + node.heuristic + " ";
                System.out.print(outLine);
                writer.write(outLine);
            }
            System.out.println();
            writer.write(System.lineSeparator());
        }
        System.out.println("Not Found !");
        writer.write("Not Found !"+System.lineSeparator());
        writer.close();
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

    private void inDuongDi(Map<Character, Character> path, char goal) throws IOException {
        List<Character> duongDi = new ArrayList<>();
        Character v = goal;
        while (v != null) {
            duongDi.add(v);
            v = path.get(v);
        }
        Collections.reverse(duongDi);

        StringBuilder duongDiStr = new StringBuilder();
        for (int i = 0; i < duongDi.size(); i++) {
            duongDiStr.append(duongDi.get(i));
            if (i < duongDi.size() - 1) {
                duongDiStr.append("->");
            }
        }
        System.out.println("=> " + duongDiStr.toString());
        writer.write("=> " + duongDiStr.toString() + System.lineSeparator());
        writer.flush();
    }

}


