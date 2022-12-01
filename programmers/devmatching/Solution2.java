package programmers.devmatching;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution2 {

    public static void main(String[] args) {

    }

    public static List<String> solution(String[] record) {
        List<String> answer = new ArrayList<>();
        int num = record.length;    // 참가자수
        List<Person> people = new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();   // 코스별 개인 기록
        for(int i = 0; i < 5; i++) {
            list.add(new ArrayList<>());
        }

        for(String s : record) {
            String[] info = s.split(":");
            String name = info[0];
            String[] records = info[1].split(",");
            int success = 0;
            int sum = 0;
            int best = 0;
            int bestIdx = -1;
            int[] t = new int[5];
            for(int i = 0; i < 5; i++) {
                int n = Integer.parseInt(records[i]);    // 코스 기록
                if(n != 0) {
                    success++;
                    sum += n;
                    list.get(i).add(n);
                    t[i] = n;
                    if(best < n) {
                        best = n;
                        bestIdx = i;
                    }
                }
            }
            people.add(new Person(name, success, bestIdx, sum, t, 0, 0, 0));
        }
        // 코스별 메달 개수 계산
        int[][] medals = new int[5][3];
        for(int i = 0; i < 5; i++) {
            medals[i][0] = (int) Math.ceil((double) list.get(i).size() / 12);  // 금
            medals[i][0] = (int) Math.ceil((double) list.get(i).size() / 4);   // 은
            medals[i][0] = (int) Math.ceil((double) list.get(i).size() / 2);   // 동
        }
        // 정렬
        for(List<Integer> l : list) {
            Collections.sort(l);
        }
        // 코스별 개인 메달 개수 계산
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < num; j++) {
                Person p = people.get(j);
                int n = p.t[i];
                int idx = list.get(i).indexOf(n);   // i번째 코스에서 해당 기록이 몇번째순서인지
                if(idx != -1) {
                    idx += 1;
                    if(idx <= medals[i][0]) {
                        p.gold++;
                    } else if(idx <= medals[i][1]) {
                        p.silver++;
                    } else if(idx <= medals[i][2]) {
                        p.brass++;
                    }
                }
            }
        }

        // 정렬
        Collections.sort(people, ((o1, o2) -> {
            if(o1.success == o2.success) {
                if(o1.best == o2.best) {
                    if(o1.gold == o2.gold) {
                        if(o1.silver == o2.silver) {
                            if(o1.brass == o2.brass) {
                                if(o1.sum == o2.sum) {
                                    return o1.name.compareTo(o2.name);
                                }
                                return o2.sum - o1.sum;
                            }
                        }
                        return o1.silver - o2.silver;
                    }
                    return o1.gold - o2.gold;
                }
                return o1.best - o2.best;
            }
            return o1.success - o2.success;
        }));

        for(Person p : people) {
            System.out.println("p.success = " + p.success);
            System.out.println("p.best = " + p.best);
            System.out.println("p.gold = " + p.gold);
            System.out.println("p.silver = " + p.silver);
            System.out.println("p.brass = " + p.brass);
            System.out.println("p.sum = " + p.sum);
            System.out.println("p.name = " + p.name);
            System.out.println("======================");
            answer.add(p.name);
        }
        return answer;
    }

    public static class Person {
        String name;
        int success;    // 완주 개수
        int best;       // 가장 어려운 코스 번호
        int sum;        // 완주시간총합
        int[] t;
        int gold;       // 금
        int silver;     // 은
        int brass;      // 동

        public Person(String name, int success, int best, int sum, int[] t, int gold, int silver, int brass) {
            this.name = name;
            this.success = success;
            this.best = best;
            this.sum = sum;
            this.t = t;
            this.gold = gold;
            this.silver = silver;
            this.brass = brass;
        }
    }
}