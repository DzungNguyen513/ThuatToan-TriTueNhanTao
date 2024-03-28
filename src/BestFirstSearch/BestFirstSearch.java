package BestFirstSearch;

import java.io.*;
import java.util.*;


public class BestFirstSearch {
    private int n, m;
    private ArrayList<Edge>[] adj;
    private Map<Character, Integer> hm = new HashMap<>();
    private String inPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\BestFirstSearch\\input.txt"; // Đường dẫn tới file input
    private String outPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\BestFirstSearch\\output.txt"; // Đường dẫn tới file output
    public FileWriter writer;

    public BestFirstSearch() {
        try {
            writer = new FileWriter(outPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void input() throws IOException {
        FileInputStream fis = new FileInputStream(inPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = br.readLine();
        String[] tokens = line.split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        adj = new ArrayList[26];

        for (int i = 0; i < 26; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            line = br.readLine();
            tokens = line.split(" ");
            char node = tokens[0].charAt(0);
            int heuristicValue = Integer.parseInt(tokens[1]);
            hm.put(node, heuristicValue);
        }

        for (int i = 0; i < m; i++) {
            line = br.readLine();
            tokens = line.split(" ");
            char from = tokens[0].charAt(0);
            char to = tokens[1].charAt(0);
            adj[from - 'A'].add(new Edge(to));
        }

        br.close();
    }

    public void bestFirstSearch(char start, char goal) throws IOException {        
        PriorityQueue<Node> PQ = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));
        Map<Character, Character> duongDi = new HashMap<>();
        Set<Character> set = new HashSet<>();
        
        PQ.add(new Node(start, hm.getOrDefault(start, 0)));
        duongDi.put(start, null);
        
        boolean kt = false;

        // In tiêu đề cột
        System.out.println("==========================================================================================");
        writer.write("==========================================================================================" + System.lineSeparator());
        String header = String.format("%-25s | %-25s | %-25s", "Trạng thái phát triển", "Trạng thái kề", "Danh sách L");
        System.out.println(header);
        writer.write(header + System.lineSeparator());
        System.out.println("==========================================================================================");
        writer.write("==========================================================================================" + System.lineSeparator());

        while (!PQ.isEmpty() && !kt) {
            Node dinhHienTai = PQ.poll();

            if (dinhHienTai.dinh == goal) {
                kt = true;
                break;
            }

            set.add(dinhHienTai.dinh);

            String ttKe = "";
            // Duyệt qua các đỉnh kề
            for (Edge edge : adj[dinhHienTai.dinh - 'A']) {
                char dinhKe = edge.dinhDen;
                if (!set.contains(dinhKe) && !duongDi.containsKey(dinhKe)) {
                    int nextHeuristic = hm.getOrDefault(dinhKe, 0);
                    Node dinhTiepTheo = new Node(dinhKe, nextHeuristic);
                    PQ.add(dinhTiepTheo);
                    duongDi.put(dinhKe, dinhHienTai.dinh);
                    ttKe += dinhKe + "-" + nextHeuristic + " ";
                }
            }

            // In danh sách L
            String dsL = "";
            List<Node> sapXepPQ = new ArrayList<>(PQ);
            sapXepPQ.sort(Comparator.comparingInt(node -> node.heuristic));
            for (Node node : sapXepPQ) {
                dsL += node.dinh + "-" + node.heuristic + " ";
            }
            String buocHienTai = String.format("%-25s | %-25s | %-25s", dinhHienTai.dinh + "-" + dinhHienTai.heuristic, ttKe.toString(), dsL);
            System.out.println(buocHienTai);
            writer.write(buocHienTai + "\n");           
        }
        
        if (kt) {
        	String ketThuc = String.format("%-25s | %-25s", goal + "-" + hm.get(goal), "Trạng thái kết thúc", "");
        	System.out.println(ketThuc);
            writer.write(ketThuc + "\n");
            System.out.println("Found " + goal + "!");
            writer.write("Found " + goal + " !\n");
            inDuongDi(duongDi, start, goal);
            
        } else {
            System.out.println("Không có đường đi từ " + start + " tới " + goal + " !\n");
            writer.write("Không có đường đi từ " + start + " tới " + goal + " !\n");
            
        }

        writer.close();
    }
    private void inDuongDi(Map<Character, Character> path, char start, char goal) throws IOException {
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
        System.out.println("=> Đường đi: " + duongDiStr.toString());
        writer.write("=> Đường đi: " + duongDiStr.toString() + System.lineSeparator());
        writer.flush();
    }

}
