import java.util.*;


public class DetRec {
    public static Set<Integer> Closure(List<List<String>> g, Set<Integer> z){
        Set<Integer> c = new HashSet<>();
        for (var q : z){
            c = Dfs(g, q , c);
        }
        return c;
    }
    public static Set<Integer> Dfs(List<List<String>> g, int q,  Set<Integer> c){
        if (!c.contains(q)){
            c.add(q);
            for (var w : g){
                if (Integer.parseInt(w.get(0)) == q && Objects.equals(w.get(2), "lambda")){
                    c = Dfs(g, Integer.parseInt(w.get(1)), c);
                }
            }
        }
        return c;
    }
    public static List<Object>  Det(Set<String> x, List<List<String>> g, List<Integer> f, int q){
        var q0 = Closure(g, Set.of(q));
        List<Set<Integer>> Q = new ArrayList<>();
        Q.add(q0);
        List<List<String>>b = new ArrayList<>();
        Set<Integer> F = new HashSet<>();
        Stack<Set<Integer>> s = new Stack<>();
        s.add(q0);
        while (!s.isEmpty()){
            var z = s.pop();
            for(var u : z){
                if (f.get(u) == 1){
                    F.addAll(z);
                    break;
                }
            }
            for (var a : x){
                Set<Integer> union = new HashSet<>();
                for (var w : g) {
                    for (var u : z) {
                        if (Integer.parseInt(w.get(0)) == u && Objects.equals(w.get(2), a)) {
                            union.add(Integer.parseInt(w.get(1)));
                        }
                    }
                }
                var z1 = Closure(g, union);
                if (!Q.contains(z1)){
                    Q.add(z1);
                    s.add(z1);
                }
                b.add(new ArrayList<>(List.of(z.toString(), z1.toString(), a)));
            }
        }
        List<Object> out = new ArrayList<>();
        out.add(Q);
        out.add(b);
        out.add(F);
        out.add(q0);
        return out;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        int n = in.nextInt();
        in.nextLine();
        int m = in.nextInt();
        in.nextLine();
        List<List<String>> g = new ArrayList<>();
        List<Integer> f = new ArrayList<>();
        Set<String> x = new HashSet<>();
        for (int i = 0; i < m; i++) {
            g.add(new ArrayList<>());
            var a = in.nextLine();
            var aa = a.split(" ");
            if (!"lambda".equals(aa[2])) {
                x.add(aa[2]);
            }
            g.get(i).addAll(List.of(aa));
        }
        for (int i = 0; i < n; i++) {
            var a = in.nextInt();
            f.add(a);
        }
        in.nextLine();
        var start = in.nextInt();
        var newpain = Det(x, g, f, start);
        var newQ = (List<Set<Integer>>) newpain.get(0);
        var newg = (List<List<String>>) newpain.get(1);
        var newf = (Set<Integer>) newpain.get(2);
        var q0 = (Set<Integer>) newpain.get(3);
        var newq = q0.toArray()[0];
        System.out.println("digraph {");
//        System.out.println("    rankdir = LR");
        for (var i : newQ) {
            Set<Integer> inter = new HashSet<>(newf);
            inter.retainAll(i);
            if (!inter.isEmpty()) {
                System.out.println("    \"" + i.toString().replaceAll(",", "") + "\""
                        + " [shape = doublecircle]");
            } else{
                System.out.println("    \"" + i + "\""
                        + " [shape = circle]");
            }
            Dictionary<String, List<String>> dict = new Hashtable<>();
            for (var w : newg) {
                if (Objects.equals(w.get(0), i.toString())) {
                    if (dict.get(w.get(1)) == null){
                        dict.put(w.get(1), new ArrayList<>());
                    }
                    dict.get(w.get(1)).add(w.get(2));
                }
            }
            var c = dict.keys();
            while (c.hasMoreElements()) {
                var k = c.nextElement();
                System.out.println("    \"" + i.toString().replaceAll(",", "") + "\" -> \"" + k.replaceAll(",", "")
                        + "\" [label = \"" + dict.get(k).toString().replaceAll("]", "").replaceAll("\\[", "") + "\"]");
            }
        }
        System.out.println("}");
    }
}
