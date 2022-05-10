import java.util.*;

import static java.lang.Math.min;

public class MinMealy {
    static Dictionary<Integer, List<Integer>>  split1(int n, int m, List<List<Integer>> g, List<List<String>> f){
        List<Set<Integer>> res = new ArrayList<>();
        for(int i = 0; i < n; i++){
            res.add(new HashSet<>());
            res.get(i).add(i);

        }
        for(int i = 0; i < n-1; i++){
            for(int j = i+1; j < n; j++){
                boolean eq = true;
                for(int k = 0; j < m; j++){
                    if (!Objects.equals(f.get(i).get(k), f.get(j).get(k))){
                        eq = false;
                        break;
                    }
                }
                if (eq){
                    var c = res.get(i);
                    res.get(i).addAll(res.get(j));
                    res.get(j).addAll(c);
                }
            }
        }
        Set<Set<Integer>> union = new HashSet<>(res);
        Dictionary<Integer, List<Integer>> rez = new Hashtable<>();
        for (var i : union){
            rez.put((Integer) i.toArray()[0], new ArrayList<>(i));
        }
        return rez;
    }
    static int find(Dictionary<Integer, List<Integer>> dict, int v){
        var c = dict.keys();
        while (c.hasMoreElements()){
            var d = c.nextElement();
            if (dict.get(d).stream().anyMatch(x -> x == v)){
                return d;
            }
        }
        return 0;
    }
    static Dictionary<Integer, List<Integer>>  split(int n, int m, List<List<Integer>> g, List<List<String>> f, Dictionary<Integer, List<Integer>> p){
        List<Set<Integer>> res = new ArrayList<>();
        for(int i = 0; i < n; i++){
            res.add(new HashSet<>());
            res.get(i).add(i);

        }
        for(int i = 0; i < n-1; i++){
            for(int j = i+1; j < n; j++){
                if (p.get(res.get(i).toArray()[0]) == p.get(res.get(j).toArray()[0])) {
                    boolean eq = true;
                    for (int k = 0; j < m; j++) {
                        if (find(p, g.get(i).get(k)) != find(p, g.get(j).get(k))) {
                            eq = false;
                            break;
                        }
                    }
                    if (eq) {
                        var c = res.get(i);
                        res.get(i).addAll(res.get(j));
                        res.get(j).addAll(c);
                    }
                }
            }
        }
        Set<Set<Integer>> union = new HashSet<>(res);
        Dictionary<Integer, List<Integer>> rez = new Hashtable<>();
        for (var i : union){
            rez.put((Integer) i.toArray()[0], new ArrayList<>(i));
        }
        return rez;
    }
    static Dictionary<Integer, List<Integer>> AufenkampHohn(int n, int m, List<List<Integer>> g, List<List<String>> f){
        var p = split1(n, m, g, f);
        var c = p.keys();
        int m1 = 0;
        while(c.hasMoreElements()){
            c.nextElement();
            m1++;
        }
        while (true){
            p = split(n, m, g, f, p);
            c = p.keys();
            int m2 = 0;
            while (c.hasMoreElements()){
                c.nextElement();
                m2++;
            }
            if (m2 == m1){
                break;
            }
            m1 = m2;
        }
        return p;
    }
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
            for (String out : outs) {
                f.get(i).add(out);
            }
        }
        var resclasses = AufenkampHohn(n, m, g, f);
        List<List<Integer>> newg = new ArrayList<>();
        List<List<String>> newf = new ArrayList<>();
        int newstart = start;
        var c = resclasses.keys();
        int cal = 0;
        while(c.hasMoreElements()){
            newg.add(new ArrayList<Integer>());
            newf.add(new ArrayList<String>());
            var d = c.nextElement();
            if (resclasses.get(d).stream().anyMatch(x -> x == start)){
                newstart = d;
            }
            for(int i = 0; i < m; i++){
                newg.get(cal).add(find(resclasses, g.get(cal).get(i)));
                newf.get(cal).add(f.get(cal).get(i));
            }
            cal++;
        }
        List<Integer> done = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(newstart);
        while (stack.size() != 0) {
            var i = stack.pop();
            if (done.stream().anyMatch(x -> Objects.equals(x, i))){
                continue;
            }
            done.add(i);
            Stack<Integer> gstack = new Stack<>();
            List<Integer> doneable = new ArrayList<>();
            for(int w: newg.get(i)){
                if (done.stream().noneMatch(x -> x == w) && doneable.stream().noneMatch(x -> x == w)){
                    gstack.push(w);
                    doneable.add(w);
                }
            }
            while (gstack.size() != 0){
                stack.push(gstack.pop());
            }
        }
        System.out.println("digraph {");
        System.out.println("    rankdir = LR");
        for (int i = 0; i < newg.size(); i++) {
            for (int j = 0; j < newg.get(i).size(); j++) {
                System.out.println("    "+done.indexOf(i)+" -> "+done.indexOf(newg.get(i).get(j))+" [label = \""+(char) (97+j)+"("+newf.get(i).get(j)+")\"]");
            }
        }
        System.out.println("}");
    }
}
