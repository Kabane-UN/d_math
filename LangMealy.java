import java.util.*;

import static java.lang.Math.min;

public class LangMealy {
    static Set<String> foo(Set<String> res,String s,  int n,int m, List<List<Integer>> g, List<List<String>> f, int start, int num){
        if (s.length() < num){
            for (int i = 0; i < m; i++){
                if (!(i == start && Objects.equals(f.get(start).get(i), "-"))){
                    String ns;
                    if (!Objects.equals(f.get(start).get(i), "-")){
                        ns = s+f.get(start).get(i);
                    } else {
                        ns = s;
                    }
                    res.add(ns);
                    res.addAll(foo(res, ns, n, m, g, f, g.get(start).get(i), num));
                }
            }
            return res;
        } else {
            return res;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int m = 0;
        List<List<Integer>> g = new ArrayList<>();
        List<List<String>> f = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<Integer>());
            String line = in.nextLine();
            String[] outs = line.split(" ");
            m = outs.length;
            for (String out : outs) {
                g.get(i).add(Integer.valueOf(out));
            }
        }
        for (int i = 0; i < n; i++) {
            f.add(new ArrayList<>());
            String line = in.nextLine();
            String[] outs = line.split(" ");
            for (String out : outs) {
                f.get(i).add(out);
            }
        }
        int start = in.nextInt();
        in.nextLine();
        int num = in.nextInt();
        in.nextLine();
        Set<String> res = new HashSet<>();
        Set<String> rez = foo(res, "", n,m, g, f, start, num);
        System.out.println(rez);
    }
}
