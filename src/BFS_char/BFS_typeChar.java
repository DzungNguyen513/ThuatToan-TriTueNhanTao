package BFS_char;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
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
    public String filePath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\output.txt";
    public FileWriter writer;

    public BFS_typeChar() {
        try {
            writer = new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        try {
            Queue<Character> q = new LinkedList<>();
            char[] path = new char[n + 10];
            boolean[] visited = new boolean[n + 10];
            Arrays.fill(path, '\0');
            Arrays.fill(visited, false);

            q.add('A');
            visited['A' - 'A'] = true;
            path['A' - 'A'] = '\0';

            System.out.println("Phat trien trang thai\tTrang thai ke\t\tDanh Sach L");
            writer.write("Phat trien trang thai\tTrang thai ke\t\tDanh Sach L" + System.lineSeparator());
            System.out.println("==================================================================================");
            writer.write("==================================================================================" + System.lineSeparator());

            boolean kt = false;

            while (!q.isEmpty()) {
                char x = q.poll();

                if (x == u) {
                    kt = true;
                    System.out.print("Found " + u);
                    writer.write("Found " + u);
                    System.out.println();
                    writer.write(System.lineSeparator());
                    System.out.print("=> ");
                    writer.write("=> ");
                    inDuongDi(path, u, true);
                    System.out.println();
                    writer.write(System.lineSeparator());
                    break;
                }
                if (adj[x - 'A'] != null) {
                    System.out.print(x + "\t\t\t");
                    writer.write(x + "\t\t\t");
                    System.out.print("|");
                    writer.write("|");
                    for (char c : adj[x - 'A']) {
                        System.out.print(c + " ");
                        writer.write(c + " ");
                    }
                    System.out.print("\t\t\t|");
                    writer.write("\t\t\t|");

                    for (char k : adj[x - 'A']) {
                        if (!visited[k - 'A']) {
                            q.add(k);
                            visited[k - 'A'] = true;
                            path[k - 'A'] = x;
                        }
                    }
                    inDSachL(q);
                }
            }
            if (!kt) {
                System.out.print("Not Found " + u + " !");
                writer.write("Not Found " + u + " !");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void inDuongDi(char[] p, char i, boolean isLast) {
        try {
            if (i == '\0') return;
            inDuongDi(p, p[i - 'A'], false);
            System.out.print(i);
            writer.write(String.valueOf(i));
            if (!isLast) {
                System.out.print("->");
                writer.write("->");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inDSachL(Queue<Character> queue) {
        try {
            System.out.print("|");
            writer.write("|");

            for (char c : queue) {
                System.out.print(c + " ");
                writer.write(c + " ");
            }
            System.out.println();
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
