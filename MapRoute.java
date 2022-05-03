import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.*;

public class MapRoute {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        num = num*num;
        in.nextLine();
        Dictionary<List<Integer>, List<List<Integer>>> grath = new Hashtable<>();
        List<Integer> l= new ArrayList<>();
        int a = 0;
        for (int i = 0; i < (int)Math.sqrt((double) num); i++) {
            String line = in.nextLine();
            line = line.replaceAll("\\s+", "");
            for (int j = 0; j < line.length(); j++) {
                if (grath.isEmpty()){
                    a = Integer.parseInt(String.valueOf(line.charAt(j)));
                    grath.put(List.of(i, j), new ArrayList<>());
                    l.add(a);
                } else{
                    int b = Integer.parseInt(String.valueOf(line.charAt(j)));
                    l.add(b);
                    grath.put(List.of(i, j), new ArrayList<>());
                    var c = grath.keys();
                    while (c.hasMoreElements()){
                        var d = c.nextElement();
                        if(( Math.abs(d.get(0) - i) == 1 && d.get(1) == j) || ( Math.abs(d.get(1) - j) == 1 && d.get(0) == i)) {
                            grath.get(d).add(new ArrayList<>(List.of(i, j, b)));
                            grath.get(List.of(i, j)).add(new ArrayList<>(List.of(d.get(0), d.get(1), l.get(d.get(0)*(int)Math.sqrt((double) num) + d.get(1)))));
                        }
                    }
                }
            }
        }
        var c = grath.keys();
        Dictionary<List<Integer>, Integer> ways = new Hashtable<>();
        Dictionary<List<Integer>, Boolean> marks = new Hashtable<>();
        while (c.hasMoreElements()){
            var d = c.nextElement();
            if (d.get(0) == 0 && d.get(1) == 0){
                ways.put(d, a);
            }
            else {
                ways.put(d, Integer.MAX_VALUE);
            }
            marks.put(d, false);
        }
        boolean flag = true;
        while (flag){
            c = ways.keys();
            int minway = Integer.MAX_VALUE;
            List<Integer> minvert = null;
            while (c.hasMoreElements()) {
                var d = c.nextElement();
                if (!marks.get(d) && ways.get(d) < minway){
                    minway = ways.get(d);
                    minvert = d;
                }
            }
            for (int i = 0; i < grath.get(minvert).size(); i++){
                int m = Math.min(ways.get(List.of(grath.get(minvert).get(i).get(0), grath.get(minvert).get(i).get(1))), grath.get(minvert).get(i).get(2)+minway);
                ways.put(List.of(grath.get(minvert).get(i).get(0), grath.get(minvert).get(i).get(1)), m);
            }
            marks.put(minvert, true);
            flag = false;
            c = ways.keys();
            while (c.hasMoreElements()) {
                var d = c.nextElement();
                if (!marks.get(d)){
                    flag = true;
                    break;
                }
            }
        }
        System.out.println(ways.get(List.of((int)Math.sqrt((double) num)-1, (int)Math.sqrt((double) num)-1)));
    }
}
