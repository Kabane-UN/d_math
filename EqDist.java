import java.util.*;

public class EqDist {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numv = in.nextInt();
        in.nextLine();
        int numr = in.nextInt();
        in.nextLine();
        List<List<List<Integer>>> grath = new ArrayList<>();
        for (int i = 0; i < numv; i++) {
            grath.add(new ArrayList<>());
        }
        for (int i = 0; i < numr; i++) {
            var a = in.nextInt();
            var b = in.nextInt();
            var c = in.nextInt();
            in.nextLine();
            grath.get(a).add(List.of(b, c));
            grath.get(b).add(List.of(a, c));
        }
        int numop = in.nextInt();
        in.nextLine();
        List <Integer> opvert = new ArrayList<>();
        for (int i = 0; i < numop; i++){
            opvert.add(in.nextInt());
            in.nextLine();
        }
        List<Integer> res = new ArrayList<>();
        for (var o : opvert) {
            Dictionary<Integer, Integer> ways = new Hashtable<>();
            Dictionary<Integer, Boolean> marks = new Hashtable<>();
            for (int i = 0; i < numv; i++) {
                if (i == o) {
                    ways.put(i, 0);
                } else {
                    ways.put(i, Integer.MAX_VALUE);
                }
                marks.put(i, false);
            }
            boolean flag = true;
            while (flag) {
                var c = ways.keys();
                int minway = Integer.MAX_VALUE;
                int minvert = 0;
                while (c.hasMoreElements()) {
                    var d = c.nextElement();
                    if (!marks.get(d) && ways.get(d) < minway) {
                        minway = ways.get(d);
                        minvert = d;
                    }
                }
                for (int i = 0; i < grath.get(minvert).size(); i++) {
                    int m = Math.min(ways.get(grath.get(minvert).get(i).get(0)), grath.get(minvert).get(i).get(1) + minway);
                    ways.put(grath.get(minvert).get(i).get(0), m);
                }
                marks.put(minvert, true);
                flag = false;
                c = ways.keys();
                while (c.hasMoreElements()) {
                    var d = c.nextElement();
                    if (!marks.get(d)) {
                        flag = true;
                        break;
                    }
                }
            }
            Dictionary<Integer, List<Integer>> counter = new Hashtable<>();
            var c = ways.keys();
            while (c.hasMoreElements()) {
                var d = c.nextElement();
                if (counter.get(ways.get(d)) != null){
                    counter.get(ways.get(d)).add(d);
                } else {
                    counter.put(ways.get(d), new ArrayList<>(List.of(d)));
                }
            }
            c = counter.keys();
            while (c.hasMoreElements()) {
                var d = c.nextElement();
                if (counter.get(d).size() > 1){
                    res.addAll(counter.get(d));
                }

            }
        }
        res.sort(Comparator.naturalOrder());
        if (res.isEmpty()){
            System.out.println("-");
        } else {
            for (var y : res){
                System.out.println(y);
            }
        }
    }
}
