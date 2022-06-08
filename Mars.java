

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mars {
    public static boolean compare(List<Integer> a, List<Integer> b) {
        int ismin = 0;
        for (int j = 0; j < Math.min(a.size(), b.size());  j++){
            if (a.get(j)<b.get(j)){
                ismin = 1;
                break;
            }
            else if (a.get(j)>b.get(j)){
                ismin = -1;
                break;
            }
        }
        if (ismin == 1){
            return true;
        } else return b.size() > a.size() && ismin == 0;
    }
    public static List<List<List<Integer>>> mars(@NotNull List<List<Integer>> grath, List<Integer> m1, List<Integer>m2, int n, boolean enaver){
        List<Integer> promises = new ArrayList<Integer>();
        List<List<List<Integer>>> promiseshistory = new ArrayList<List<List<Integer>>>();
        for (int i = n; i < grath.size(); i++){
            if (i == 0){
                m1.add(i);
            } else {
               boolean flag1 = true;
                for (int person: m1){
                    if (grath.get(person).get(i) == 1){
                        flag1 = false;
                        break;
                    }
                }
                boolean flag2 = true;
                for (int person: m2){
                    if (grath.get(person).get(i) == 1){
                        flag2 = false;
                        break;
                    }
                }
                if (flag1 ^ flag2){
                    if (flag1){
                        m1.add(i);
                    } else {
                        m2.add(i);
                    }
                } else if (flag1 & flag2) {
                    if (enaver && i == n) {
                        m2.add(i);
                    } else {
                        promises.add(i);
                        List<Integer> copym2 = new ArrayList<Integer>(m2);
                        List<Integer> copym1 = new ArrayList<Integer>(m1);
                        promiseshistory.add(new ArrayList<List<Integer>>(List.of(copym1, copym2)));
                        m1.add(i);
                    }

                } else {
                    List<List<List<Integer>>> res = new ArrayList<>();
                    for (int k = 0; k < promises.size(); k++){
                        res.addAll(mars(grath, promiseshistory.get(k).get(0), promiseshistory.get(k).get(1), promises.get(k), true));
//                        if (parts != null) {
//                            if (res != null) {
//                                if (Math.abs(m1.size() - m2.size()) > Math.abs(parts.get(0).size() - parts.get(1).size())){
//                                    m1 = parts.get(0);
//                                    m2 = parts.get(1);
//                                    if (m1.size()<m2.size()){
//                                        res = m1;
//                                    } else if (m1.size()>m2.size()){
//                                        res = m2;
//                                    } else {
//                                        if (compare(m1, m2)){
//                                            res = m1;
//                                        } else{
//                                            res = m2;
//                                        }
//                                    }
//                                } else if (Math.abs(m1.size() - m2.size()) == Math.abs(parts.get(0).size() - parts.get(1).size())){
//                                    List<Integer> res2 =  parts.get(0).size()<parts.get(1).size()? parts.get(0) : parts.get(1);
//                                    boolean ismin = compare(res2, res);
//                                    if (ismin){
//                                        m1 = parts.get(0);
//                                        m2 = parts.get(1);
//                                        if (m1.size()<m2.size()){
//                                            res = m1;
//                                        } else if (m1.size()>m2.size()){
//                                            res = m2;
//                                        } else {
//                                            if (compare(m1, m2)){
//                                                res = m1;
//                                            } else{
//                                                res = m2;
//                                            }
//                                        }
//                                    }
//                                }
//                            } else {
//                                m1 = parts.get(0);
//                                m2 = parts.get(1);
//                                if (m1.size()<m2.size()){
//                                    res = m1;
//                                } else if (m1.size()>m2.size()){
//                                    res = m2;
//                                } else {
//                                    if (compare(m1, m2)){
//                                        res = m1;
//                                    } else{
//                                        res = m2;
//                                    }
//                                }
//                            }
//                        }
                    }
//                    if (res != null) {
//                        return new ArrayList<List<Integer>>(List.of(m1, m2));
//                    } else {
//                        return null;
//                    }
                    return res;
                }
            }
        }
        List<List<List<Integer>>> res = new ArrayList<>();
//        if (m1.size()<m2.size()){
//            res = m1;
//        } else if (m1.size()>m2.size()){
//            res = m2;
//        } else {
//            if (compare(m1, m2)){
//                res = m1;
//            } else{
//                res = m2;
//            }
//        }
        res.add(new ArrayList<List<Integer>>(List.of(m1, m2)));
        for (int i = 0; i < promises.size(); i++) {
            res.addAll(mars(grath, promiseshistory.get(i).get(0), promiseshistory.get(i).get(1), promises.get(i), true));
//            if (parts != null) {
//                if (Math.abs(m1.size() - m2.size()) > Math.abs(parts.get(0).size() - parts.get(1).size())){
//                    m1 = parts.get(0);
//                    m2 = parts.get(1);
//                    if (m1.size()<m2.size()){
//                        res = m1;
//                    } else if (m1.size()>m2.size()){
//                        res = m2;
//                    } else {
//                        if (compare(m1, m2)){
//                            res = m1;
//                        } else{
//                            res = m2;
//                        }
//                    }
//                } else if (Math.abs(m1.size() - m2.size()) == Math.abs(parts.get(0).size() - parts.get(1).size())){
//                    List<Integer> res2 =  parts.get(0).size()<parts.get(1).size()? parts.get(0) : parts.get(1);
//                    boolean ismin = compare(res2, res);
//                    if (ismin){
//                        m1 = parts.get(0);
//                        m2 = parts.get(1);
//                        if (m1.size()<m2.size()){
//                            res = m1;
//                        } else if (m1.size()>m2.size()){
//                            res = m2;
//                        } else {
//                            if (compare(m1, m2)){
//                                res = m1;
//                            } else{
//                                res = m2;
//                            }
//                        }
//                    }
//                }
//            }
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        List<List<Integer>> grath = new ArrayList<List<Integer>>();
        for (int i = 0; i < num; i++) {
            grath.add(new ArrayList<Integer>());
            String line = in.nextLine();
            for (int j = 0; j < num; j++) {
                if (line.charAt(j) == '+') {
                    grath.get(i).add(1);
                } else {
                    grath.get(i).add(0);
                }
            }
        }
        var res = mars(grath, new ArrayList<>(), new ArrayList<>(), 0, false);
//        System.out.println(grath);
//        System.out.println(res);
        if (res.size() == 0) {
            System.out.println("No solution");
        } else {
            List<Integer> m1 = new ArrayList<>();
            List<Integer> m2 = new ArrayList<>();
            List<Integer> ures = new ArrayList<>();
            for (int i = 0; i < res.size(); i++) {
                if (Math.abs(m1.size() - m2.size()) > Math.abs(res.get(i).get(0).size() - res.get(i).get(1).size()) || i == 0) {
                    m1 = res.get(i).get(0);
                    m2 = res.get(i).get(1);
                    if (m1.size() < m2.size()) {
                        ures = m1;
                    } else if (m1.size() > m2.size()) {
                        ures = m2;
                    } else {
                        if (compare(m1, m2)) {
                            ures = m1;
                        } else {
                            ures = m2;
                        }
                    }
                } else if (Math.abs(m1.size() - m2.size()) == Math.abs(res.get(i).get(0).size() - res.get(i).get(1).size())) {
                    List<Integer> ures2;
                    List<Integer> rm1 = res.get(i).get(0);
                    List<Integer> rm2 = res.get(i).get(1);
                    if (rm1.size() < rm2.size()) {
                        ures2 = rm1;
                    } else if (rm1.size() > rm2.size()) {
                        ures2 = rm2;
                    } else {
                        if (compare(rm1, rm2)) {
                            ures2 = rm1;
                        } else {
                            ures2 = rm2;
                        }
                    }
                    boolean ismin = compare(ures2, ures);
                    if (ismin) {
                        m1 = rm1;
                        m2 = rm2;
                        ures = ures2;
                    }
                }
            }
            for (int i : ures){
                System.out.println(++i);
            }
        }
    }
}
