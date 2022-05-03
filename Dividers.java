import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dividers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        List<Integer> dir = new ArrayList<Integer>();
        for (int i = 1; i<= num; i++){
            if(num%i == 0){
                dir.add(i);
            }
        }
        List<String> res = new ArrayList<>();
        for (int i = dir.size() - 1; i >= 0; i--){
            res.add(dir.get(i).toString()+';');
            List<Integer> g = new ArrayList<>();
            for (int j = i-1; j >= 0;j--){
                if (dir.get(i) % dir.get(j) == 0){
                    boolean flag = true;
                    for (int k : g){
                        if (k%dir.get(j) == 0){
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        res.add(dir.get(i).toString() + "--" + dir.get(j).toString() + ';');
                        g.add(dir.get(j));
                    }
                }
            }
        }
        for(String s : res){
            System.out.println(s);
        }
    }
}
