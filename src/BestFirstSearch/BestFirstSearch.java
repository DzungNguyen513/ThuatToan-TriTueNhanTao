package BestFirstSearch;

import java.io.*;
import java.util.*;


public class BestFirstSearch {
    private int n, m;
    private ArrayList<Edge>[] adj;
    private Map<Character, Integer> heuristicMap = new HashMap<>();
    private String inputPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\BestFirstSearch\\input.txt"; // Đường dẫn tới file input
    private String outputPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\BestFirstSearch\\output.txt"; // Đường dẫn tới file output

    public void input() throws IOException {
        FileInputStream fis = new FileInputStream(inputPath);
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
            heuristicMap.put(node, heuristicValue);
        }

        for (int i = 0; i < m; i++) {
            line = br.readLine();
            tokens = line.split(" ");
            char source = tokens[0].charAt(0);
            char destination = tokens[1].charAt(0);
            adj[source - 'A'].add(new Edge(destination));
        }

        br.close();
    }

    public void bestFirstSearch(char start, char goal) throws IOException {

    }
}
