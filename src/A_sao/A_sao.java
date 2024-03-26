package A_sao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A_sao {
    public int n, m;
    public ArrayList<EdgeWeight>[] adj;
    public boolean[] visited;
    public String inPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\A_sao\\input.txt";
    public String outPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\A_sao\\output.txt";
    public FileWriter writer;
    private Map<Character, Integer> heuristicValues = new HashMap<>();

   
    public A_sao() {
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

            for (int i = 0; i < n; i++) {
                line = br.readLine();
                tokens = line.split(" ");
                char node = tokens[0].charAt(0);
                int heuristicValue = Integer.parseInt(tokens[1]);
                heuristicValues.put(node, heuristicValue);
            }
            for (int i = 0; i < m; i++) {
                line = br.readLine();
                tokens = line.split(" ");
                char from = tokens[0].charAt(0);
                char to = tokens[1].charAt(0);
                int weight = Integer.parseInt(tokens[2]);
                adj[from - 'A'].add(new EdgeWeight(to, weight));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void aSao(char a, char b) {
    	try {
    		PriorityQueue<NodeWeight> PQ = new PriorityQueue<>(Comparator.comparingInt(node -> node.f));
            Map<Character, Character> parent = new HashMap<>(); 
            Map<Character, Integer> gCosts = new HashMap<>();
            Map<Character, Integer> hCosts = new HashMap<>();

            PQ.add(new NodeWeight(a, 0, heuristic(a, b), 0));
            parent.put(a, null);
            gCosts.put(a, 0);

            System.out.println("=============================================================================");
            writer.write("=============================================================================" + System.lineSeparator());

            System.out.printf("%-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-30s\n", "TT", "TTK", "k(u,v)", "h(v)", "g(v)", "f(v)", "Danh sách L");
            String header = String.format("%-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-30s\n", "TT", "TTK", "k(u,v)", "h(v)", "g(v)", "f(v)", "Danh sách L");
            writer.write(header);
            System.out.println("=============================================================================");
            writer.write("=============================================================================" + System.lineSeparator());

            while (!PQ.isEmpty()) {
                NodeWeight x = PQ.poll(); // Đỉnh đầu của PQ
                char xName = x.tenDinh;

                if (xName == b) {
                	System.out.println(x.tenDinh + "     | Trạng thái kết thúc");
                    writer.write(x.tenDinh + "     | Trạng thái kết thúc" +System.lineSeparator());

                    System.out.println("=> Found " + xName+" !");
                    writer.write("=> Found " + xName+" !" +System.lineSeparator());
                    inDuongDi(parent, b);
                    return;
                }

                if (!visited[xName - 'A']) {
                    visited[xName - 'A'] = true;

                    for (EdgeWeight edge : adj[xName - 'A']) { // Xử lý đỉnh kề
                        char xDen = edge.dinhDen;
                        int newGCost = gCosts.get(xName) + edge.cost;

                        if (!visited[xDen - 'A'] || newGCost < gCosts.getOrDefault(xDen, Integer.MAX_VALUE)) {
                            int heuristic = heuristic(xDen, b);
                            int fCost = newGCost + heuristic;

                            PQ.add(new NodeWeight(xDen, newGCost, heuristic, fCost));
                            parent.put(xDen, xName);
                            gCosts.put(xDen, newGCost);
                            hCosts.put(xDen, heuristic);
                            List<NodeWeight> sapXep = new ArrayList<>(PQ);
                            Collections.sort(sapXep, Comparator.comparingInt(node -> node.f));

                            System.out.printf("%-5s | %-5s | %-5s  | %-5s | %-5s | %-5s | %-30s\n",
                            		xName, xDen, edge.cost, heuristic, newGCost, fCost, sapXep);
                            String line = String.format("%-5s | %-5s | %-5s  | %-5s | %-5s | %-5s | %-30s\n",
                            		xName, xDen, edge.cost, heuristic, newGCost, fCost, sapXep);
                            writer.write(line);
                        }
                    }
                }
            }
            System.out.println("Not Found " + b+ " !");
            writer.write("Not Found " + b+ " !" +System.lineSeparator());

    	}catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }

    private int heuristic(char node, char goal) {
        return heuristicValues.getOrDefault(node, Integer.MAX_VALUE);
    }

    private void inDuongDi(Map<Character, Character> parent, char goal) {
    	
    	try {
    		List<Character> path = new ArrayList<>();
            Character xName = goal;
            while (xName != null) {
                path.add(xName);
                xName = parent.get(xName);
            }
            Collections.reverse(path);
            
            System.out.print("=> Đường đi: ");
            writer.write("=> Đường đi: ");

            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                writer.write(path.get(i));

                if (i < path.size() - 1) {
                    System.out.print("->");
                    writer.write("->");

                }
            }
            System.out.println();
            writer.write(System.lineSeparator());

            System.out.println("=> Độ dài ngắn nhất: " + tinhKC(path));
            writer.write("=> Độ dài ngắn nhất: " + tinhKC(path)+System.lineSeparator());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private int tinhKC(List<Character> p) {
        int kc = 0;

        for (int i = 0; i < p.size() - 1; i++) {
            char x = p.get(i);
            char xNext = p.get(i + 1);

            for (EdgeWeight edge : adj[x - 'A']) {
                if (edge.dinhDen == xNext) {
                	kc += edge.cost;
                    break;
                }
            }
        }
        return kc;
    }
}


