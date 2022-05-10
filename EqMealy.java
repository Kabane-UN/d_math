import java.util.*;

import static java.lang.Math.min;

public class EqMealy {
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
        int n1 = in.nextInt();
        in.nextLine();
        int m1 = in.nextInt();
        in.nextLine();
        int start1 = in.nextInt();
        in.nextLine();
        List<List<Integer>> g1 = new ArrayList<>();
        List<List<String>> f1 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g1.add(new ArrayList<Integer>());
            for (int j = 0; j < m; j++) {
                var a = in.nextInt();
                g1.get(i).add(a);
            }
            in.nextLine();
        }
        for (int i = 0; i < n; i++) {
            f1.add(new ArrayList<>());
            String line = in.nextLine();
            String[] outs = line.split(" ");
            for (String out : outs) {
                f1.get(i).add(out);
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
        var resclasses1 = AufenkampHohn(n1, m1, g1, f1);
        List<List<Integer>> newg1 = new ArrayList<>();
        List<List<String>> newf1 = new ArrayList<>();
        int newstart1 = start1;
        c = resclasses1.keys();
        cal = 0;
        while(c.hasMoreElements()){
            newg1.add(new ArrayList<Integer>());
            newf1.add(new ArrayList<String>());
            var d = c.nextElement();
            if (resclasses1.get(d).stream().anyMatch(x -> x == start1)){
                newstart1 = d;
            }
            for(int i = 0; i < m1; i++){
                newg1.get(cal).add(find(resclasses1, g1.get(cal).get(i)));
                newf1.get(cal).add(f1.get(cal).get(i));
            }
            cal++;
        }
        List<Integer> done1 = new ArrayList<>();
        Stack<Integer> stack1 = new Stack<>();
        stack1.push(newstart1);
        while (stack1.size() != 0) {
            var i = stack1.pop();
            if (done1.stream().anyMatch(x -> Objects.equals(x, i))){
                continue;
            }
            done1.add(i);
            Stack<Integer> gstack = new Stack<>();
            List<Integer> doneable = new ArrayList<>();
            for(int w: newg1.get(i)){
                if (done1.stream().noneMatch(x -> x == w) && doneable.stream().noneMatch(x -> x == w)){
                    gstack.push(w);
                    doneable.add(w);
                }
            }
            while (gstack.size() != 0){
                stack1.push(gstack.pop());
            }
        }
        var flag = true;
        flag = newg.size() == newg1.size();
        flag = m == m1;
        flag = newstart == newstart1;
        if (flag) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    flag = done1.indexOf(newg1.get(done1.get(i)).get(j)) == done.indexOf(newg.get(done.get(i)).get(j));
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    flag = Objects.equals(newf1.get(done1.get(i)).get(j), newf.get(done.get(i)).get(j));
                }
            }
        }
        if (flag){
            System.out.println("EQUAL");
        } else{
            System.out.println("NOT EQUA");
        }
    }
}
