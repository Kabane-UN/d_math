import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        m--;
        List<List<Integer>> grath = new ArrayList<>();
        for(int i = 0; i < n; i++){
            grath.add(new ArrayList<>());
        }
        List<List<Integer>> reb = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int n1 = in.nextInt();
            int n2 = in.nextInt();
            grath.get(n1).add(n2);
            grath.get(n2).add(n1);
        }
        List<Integer> done = new ArrayList<>();
        int counter = 0;
        while (done.size() < n){
            int k = 0;
            for (int i = 0; i < n; i++){
                int finalI = i;
                if (done.stream().noneMatch(x -> x == finalI)){
                    k = i;
                    break;
                }
            }
            Queue<Integer> quot = new LinkedList<>();
            quot.add(k);
            done.add(k);
            while(quot.size() != 0){
                for(int c: grath.get(quot.peek())){
                    if (done.stream().noneMatch(x -> x == c)){
                        quot.add(c);
                        done.add(c);
                    }
                }
                quot.remove();
            }
            counter++;
        }
        System.out.println(counter);
    }
}
