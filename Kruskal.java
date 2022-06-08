

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.*;

public class Kruskal {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        List<List<Integer>> negrath = new ArrayList<>();
        List<List<Double>> grath = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            var a = in.nextInt();
            var b = in.nextInt();
            in.nextLine();
            var j = 0;
            for (List<Integer> integers : negrath) {
                grath.add(List.of((double) j, (double) i, Math.sqrt(Math.pow(a - integers.get(0), 2) + Math.pow(b - integers.get(1), 2))));
                j++;
            }
            negrath.add(List.of(a, b));
        }
        grath.sort(new Comparator<List<Double>>() {
            @Override
            public int compare(List<Double> doubles, List<Double> t1) {
                return doubles.get(2).compareTo(t1.get(2));
            }
        });
        double sum = 0;
        List<Double> done = new ArrayList<>();
        while (done.size() != num) {
            if (done.isEmpty()){
                done.add(grath.get(0).get(0));
                done.add(grath.get(0).get(1));
                sum+=grath.get(0).get(2);
            }
            for (List<Double> doubles : grath) {
                if ((done.contains(doubles.get(0)) && !done.contains(doubles.get(1))) || (!done.contains(doubles.get(0)) && done.contains(doubles.get(1)))) {
                    sum += doubles.get(2);
                    if (done.contains(doubles.get(0))) {
                        done.add(doubles.get(1));
                    } else {
                        done.add(doubles.get(0));
                    }
                    break;
                }
            }
        }
        System.out.printf("%.2f%n", sum);
    }
}
