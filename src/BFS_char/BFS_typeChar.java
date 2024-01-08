package BFS_char;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_typeChar {
    public int n, m;
    public ArrayList<Character>[] adj;
    public boolean[] visited;

    public void input() {
        try {
            FileInputStream fis = new FileInputStream("D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\input2.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            String[] tokens = line.split(" ");
            n = Integer.parseInt(tokens[0]);
            m = Integer.parseInt(tokens[1]);
            adj = new ArrayList[1001];
            visited = new boolean[1001];

            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
            }
            Arrays.fill(visited, false);

            for (int i = 0; i < m; i++) {
                line = br.readLine();
                tokens = line.split(" ");
                char x = tokens[0].charAt(0);
                char y = tokens[1].charAt(0);
                adj[x - 'A'].add(y); 
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void BFS_TimDuong(char u) {
        Queue<Character> q = new LinkedList<>();
        q.add(u);
        visited[u - 'A'] = true;
        System.out.print("=> ");
        while (!q.isEmpty()) {
            char v = q.poll();
            System.out.print(v + " ");

            if (adj[v - 'A'] != null) {
                for (Character x : adj[v - 'A']) {
                    if (!visited[x - 'A']) {
                        q.add(x);
                        visited[x - 'A'] = true;
                    }
                }
            }
        }
        Arrays.fill(visited, false);
    }


    public void BFS_TimDinh(char u) {
        Queue<Character> q = new LinkedList<>();
        char[] path = new char[n + 10];
        boolean[] visited = new boolean[n + 10];
        Arrays.fill(path, '\0');
        Arrays.fill(visited, false);

        q.add('A');
        visited['A' - 'A'] = true;
        path['A' -'A'] = '\0';

        path['A' - 'A'] = '\0'; 
        while (!q.isEmpty()) {
            char x = q.poll();
            if (x == u) {
                printPath(path, u);
                return;
            }
            if (adj[x - 'A'] != null) {
                for (Character k : adj[x - 'A']) {
                    if (!visited[k - 'A']) {
                        q.add(k);
                        visited[k - 'A'] = true;
                        path[k - 'A'] = x;
                    }
                }
            }
        }
    }
    private void printPath(char[] path, char j) {
        if (j == '\0') return;
        printPath(path, path[j - 'A']);
        System.out.print(j + " ");
    }
}
