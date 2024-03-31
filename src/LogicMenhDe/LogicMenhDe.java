package LogicMenhDe;

import java.io.*;
import java.util.*;

public class LogicMenhDe {
    private String inPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\LogicMenhDe\\input.txt";
    private String outPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\LogicMenhDe\\output.txt";
    public FileWriter writer;
    private List<Set<String>> clauses;

    public LogicMenhDe() {
        clauses = new ArrayList<>();
        try {
            writer = new FileWriter(outPath, false); // Prepare the writer to overwrite the existing file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void input() {
        try (BufferedReader br = new BufferedReader(new FileReader(inPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Set<String> clause = new HashSet<>(Arrays.asList(line.trim().split("\\s*v\\s*")));
                clauses.add(clause);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Prove(String s) throws IOException {
        Set<String> goalClause = new HashSet<>();
        goalClause.add("!" + s);
        clauses.add(goalClause);

        writeToBoth("Bắt đầu quá trình chứng minh...", writer);
        writeToBoth("Mục tiêu chứng minh: "+ s, writer);
        writeToBoth("Phủ định mục tiêu: "+ goalClause, writer);
        writeToBoth("Các mệnh đề ban đầu: "+ clauses, writer);
        writeToBoth("=======================================================", writer);
        writeToBoth(String.format("| %-15s | %-15s | %-15s |", "u", "v", "res(u, v)"), writer);
        writeToBoth("=======================================================", writer);

        boolean isProved = false;

        outerloop:
        for (int i = 0; !isProved && i < clauses.size(); i++) {
            for (int j = i + 1; j < clauses.size(); j++) {
                Set<String> clause1 = clauses.get(i);
                Set<String> clause2 = clauses.get(j);
                Set<String> resolvedClause = resolve(clause1, clause2);
                if (resolvedClause != null) {
                    writeToBoth(String.format("| %-15s | %-15s | %-15s |", clause1, clause2, resolvedClause), writer);
                    if (resolvedClause.isEmpty()) {
                        writeToBoth("=> Tìm thấy mệnh đề rỗng! Chứng minh thành công!", writer);
                        isProved = true;
                        break outerloop;
                    } else if (!clauses.contains(resolvedClause)) {
                        clauses.add(resolvedClause);
                    }
                }
            }
        }
        if (isProved) {
            writeToBoth("=> Công thức " + s + " được chứng minh là đúng.", writer);
        } else {
            writeToBoth("Không thể chứng minh công thức.", writer);
        }
        writer.close();
    }

    private Set<String> resolve(Set<String> clause1, Set<String> clause2) {
        Set<String> resolvent = new HashSet<>(clause1);
        for (String literal : clause2) {
            String complement = literal.startsWith("!") ? literal.substring(1) : "!" + literal;
            if (resolvent.contains(complement)) {
                resolvent.remove(complement);
                resolvent.addAll(clause2);
                resolvent.remove(literal);
                return resolvent;
            }
        }
        return null;
    }

    private void writeToBoth(String text, FileWriter writer) throws IOException {
        System.out.println(text);
        writer.write(text + System.lineSeparator());
    }

}
