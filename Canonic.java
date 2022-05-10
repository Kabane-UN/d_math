import java.util.*;

import static java.lang.Math.min;

public class Canonic {
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
        List<Integer> done = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        while (stack.size() != 0) {
            var i = stack.pop();
            Stack<Integer> gstack = new Stack<>();
            for(int c: g.get(i)){
                if (done.stream().noneMatch(x -> x == c)){
                    gstack.push(c);
                }
            }
            while (gstack.size() != 0){
                stack.push(gstack.pop());
            }
            done.add(i);
        }
        System.out.println(n);
        System.out.println(m);
        System.out.println(done.indexOf(start));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(done.indexOf(g.get(done.get(i)).get(j)));
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(f.get(done.get(i)).get(j));
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
}
