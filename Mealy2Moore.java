import java.util.*;

import static java.lang.Math.min;

public class Mealy2Moore {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        in.nextLine();
        String inp = in.nextLine();
        var ainp = inp.split(" ");
        int start = in.nextInt();
        in.nextLine();
        var out = in.nextLine();
        var aout = out.split(" ");
        int n = in.nextInt();
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
                for(int k = 0; k<start; k++) {
                    System.out.println("    \"(" + i + ", " + aout[k] + ")\" -> \"(" + g.get(i).get(j) + ", " + aout[Integer.parseInt(f.get(i).get(j))]
                            + ")\" [label = \"" + ainp[j] + "\"]");
                }
            }
        }
        System.out.println("}");
    }
}
