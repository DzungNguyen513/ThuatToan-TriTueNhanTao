package BestFirstSearch;

import java.io.*;
import java.util.*;


public class BestFirstSearch {
    private int n, m;
    private Map<String, List<Edge>> adj = new HashMap<>();
    private Map<String, Integer> hm = new HashMap<>();
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

        for (int i = 0; i < n; i++) {
            line = br.readLine();
            tokens = line.split(" ");
            String node = tokens[0].toString();
            int heuristicValue = Integer.parseInt(tokens[1]);
            hm.put(node, heuristicValue);
            adj.put(node, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            line = br.readLine();
            tokens = line.split(" ");
            String from = tokens[0].toString();
            String to = tokens[1].toString();
            adj.get(from).add(new Edge(to));
        }

        br.close();
    }

    public void bestFirstSearch(String start, String goal) throws IOException {        
        PriorityQueue<Node> PQ = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));
        Map<String, String> duongDi = new HashMap<>();
        Set<String> set = new HashSet<>();
        
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

            if (dinhHienTai.dinh.equals(goal)) {
                kt = true;
                break;
            }

            set.add(dinhHienTai.dinh);

            String ttKe = "";
            // Duyệt qua các đỉnh kề
            List<Edge> edges = adj.get(dinhHienTai.dinh); // Truy cập bằng key kiểu String
            if (edges != null) {
                for (Edge edge : edges) {
                    String dinhKe = edge.dinhDen;
                    if (!set.contains(dinhKe) && !duongDi.containsKey(dinhKe)) {
                        int nextHeuristic = hm.getOrDefault(dinhKe, 0);
                        Node dinhTiepTheo = new Node(dinhKe, nextHeuristic);
                        PQ.add(dinhTiepTheo);
                        duongDi.put(dinhKe, dinhHienTai.dinh);
                        ttKe += dinhKe + "-" + nextHeuristic + " ";
                    }
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
    private void inDuongDi(Map<String, String> path, String start, String goal) throws IOException {
        List<String> duongDi = new ArrayList<>();
        String v = goal;
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
