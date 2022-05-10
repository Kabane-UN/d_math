import java.util.*;

import static java.lang.Math.min;

public class VisMealy {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int m = in.nextInt();
        in.nextLine();
        int start = in.nextInt();
        in.nextLine();
        List<List<Integer>> g = new ArrayList<>();
        List<List<String>> f = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<Integer>());
            for (int j = 0; j < m; j++) {
                var a = in.nextInt();
                g.get(i).add(a);
            }
            in.nextLine();
        }
        for (int i = 0; i < n; i++) {
            f.add(new ArrayList<>());
            String line = in.nextLine();
            String[] outs = line.split(" ");
            for (int j = 0; j < m; j++) {
                f.get(i).add(outs[j]);
            }
        }
        System.out.println("digraph {");
        System.out.println("    rankdir = LR");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.println("    "+i+" -> "+g.get(i).get(j)+" [label = \""+(char) (97+j)+"("+f.get(i).get(j)+")\"]");
            }
        }
        System.out.println("}");
    }
}
