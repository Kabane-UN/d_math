import java.util.*;

import static java.lang.Math.min;

public class BridgeNum {
    static int time;
    static int[] tin;
    static int[] fup;
    static boolean[] used;
    static int cal;
    static List<List<Integer>> grath;
    static void gdf(int v, int... ps) {
        int p;
        if (ps.length == 0) {
            p = -1;
        } else {
            p = ps[0];
        }
        used[v] = true;
        tin[v] = fup[v] = time++;
        for (int i = 0; i < grath.get(v).size(); i++) {
            int to = grath.get(v).get(i);
            if (to == p) continue;
            if (used[to])
                fup[v] = min(fup[v], tin[to]);
            else {
                gdf(to, v);
                fup[v] = min(fup[v], fup[to]);
                if (fup[to] > tin[v])
                    cal++;
            }

        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numv = in.nextInt();
        in.nextLine();
        int numr = in.nextInt();
        in.nextLine();
        grath = new ArrayList<>();
        for (int i = 0; i < numv; i++) {
            grath.add(new ArrayList<>());
        }
        for (int i = 0; i < numr; i++) {
            var a = in.nextInt();
            var b = in.nextInt();
            in.nextLine();
            grath.get(a).add(b);
            grath.get(b).add(a);
        }
        time = 0;
        cal = 0;
        tin = new int[numv];
        fup = new int[numv];
        used = new boolean[numv];
        for (int i = 0; i < numv; i++) {
            used[i] = false;
        }
        for (int i = 0; i < numv; i++){
            gdf(i);
        }
        System.out.println(cal);
    }
}
