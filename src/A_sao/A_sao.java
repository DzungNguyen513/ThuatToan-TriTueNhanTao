package A_sao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A_sao {
    public int n, m;
    public ArrayList<EdgeWeight>[] adj;
    public boolean[] visited;
    public String inPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\A_sao\\input.txt";
    public String outPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\BestFirstSearch\\output.txt";

    public A_sao() {
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
                char source = tokens[0].charAt(0);
                char target = tokens[2].charAt(0);
                int cost = Integer.parseInt(tokens[4]);
                adj[source - 'A'].add(new EdgeWeight(target, cost));
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aStar(char start, char goal) {
        PriorityQueue<NodeWeight> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.fCost));
        Map<Character, Character> parent = new HashMap<>();
        Map<Character, Integer> gCosts = new HashMap<>();
        Map<Character, Integer> hCosts = new HashMap<>();

        priorityQueue.add(new NodeWeight(start, 0, heuristic(start, goal), 0));
        parent.put(start, null);
        gCosts.put(start, 0);

        System.out.println("=============================================================================");
        System.out.printf("%-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-30s\n", "TT", "TTK", "k(u,v)", "h(v)", "g(v)", "f(v)", "Danh sách L");
        System.out.println("=============================================================================");

        while (!priorityQueue.isEmpty()) {
            NodeWeight current = priorityQueue.poll();
            char currentName = current.tenDinh;

            if (currentName == goal) {
            	System.out.println(current.tenDinh + "     | Trạng thái kết thúc");
                System.out.println("=> Found " + currentName+" !");
                printPath(parent, goal);
                return;
            }

            if (!visited[currentName - 'A']) {
                visited[currentName - 'A'] = true;

                for (EdgeWeight edge : adj[currentName - 'A']) {
                    char neighborName = edge.dinhDen;
                    int newGCost = gCosts.get(currentName) + edge.cost;

                    if (!visited[neighborName - 'A'] || newGCost < gCosts.getOrDefault(neighborName, Integer.MAX_VALUE)) {
                        int heuristic = heuristic(neighborName, goal);
                        int fCost = newGCost + heuristic;

                        priorityQueue.add(new NodeWeight(neighborName, newGCost, heuristic, fCost));
                        parent.put(neighborName, currentName);
                        gCosts.put(neighborName, newGCost);
                        hCosts.put(neighborName, heuristic);
                        
                        System.out.printf("%-5s | %-5s | %-5s  | %-5s | %-5s | %-5s | %-30s\n",
                                currentName, neighborName, edge.cost, heuristic, newGCost, fCost, priorityQueue);
                    }
                }
            }
        }
        System.out.println("Not Found " + goal+ " !");
    }

    private int heuristic(char node, char goal) {
        Map<Character, Integer> hm = new HashMap<>();
        hm.put('A', 14);
        hm.put('B', 0);
        hm.put('C', 15);
        hm.put('D', 6);
        hm.put('E', 8);
        hm.put('F', 7);
        hm.put('G', 12);
        hm.put('H', 10);
        hm.put('I', 4);
        hm.put('K', 2);
        return hm.getOrDefault(node, Integer.MAX_VALUE);
    }

    private void printPath(Map<Character, Character> parent, char goal) {
    	List<Character> path = new ArrayList<>();
        Character current = goal;
        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }
        Collections.reverse(path);
        
        System.out.print("=> Đường đi: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print("->");
            }
        }
        System.out.println();
        System.out.println("=> Độ dài ngắn nhất: " + tinhKC(path));
    }
    private int tinhKC(List<Character> p) {
        int kc = 0;

        for (int i = 0; i < p.size() - 1; i++) {
            char current = p.get(i);
            char next = p.get(i + 1);

            for (EdgeWeight edge : adj[current - 'A']) {
                if (edge.dinhDen == next) {
                	kc += edge.cost;
                    break;
                }
            }
        }

        return kc;
    }
}


