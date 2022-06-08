

import java.lang.reflect.Array;
import java.util.*;

public class Prim {
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
        int sum = 0;
        List<Integer> done = new ArrayList<>();
        while (done.size() != numv) {
            if (done.isEmpty()){
                done.add(0);
            }
            int siz = Integer.MAX_VALUE;
            int nv = 0;
            for (int i = 0; i < done.size(); i++){
                for (int j = 0; j < grath.get(done.get(i)).size(); j++){
                    if (!done.contains(grath.get(done.get(i)).get(j).get(0))){
                        if (grath.get(done.get(i)).get(j).get(1) <= siz){
                            siz = grath.get(done.get(i)).get(j).get(1);
                            nv = grath.get(done.get(i)).get(j).get(0);
                        }
                    }
                }
            }
            sum += siz;
            done.add(nv);
        }
        System.out.println(sum);
    }
}
