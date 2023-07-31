package programmers.level4;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [문제명] 무지의 먹방 라이브
 * [풀이시간] (1시간 33분)
 * [한줄평]
 * 1_v1. (실패 - 정확성 14.7, 효율성 0)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42891">문제</a>
 */
class Solution42891 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    class Food {
        int idx;
        int time;
        Food(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
    public int solution(int[] foodTimes, long k) {
        int answer = 0;
        //
        List<Food> list = new ArrayList<>();
        for(int i = 0; i < foodTimes.length; i++) {
            list.add(new Food(i + 1, foodTimes[i]));
        }
        //
        Collections.sort(list, (o1, o2) -> {
            if(o1.time == o2.time) return o1.idx - o2.idx;
            return o1.time - o2.time;
        });
        //
        int prev = 0;
        int p = 0;
        int cnt = foodTimes.length;
        k++;
        while(p < foodTimes.length) {
            int dis = list.get(p).time - prev;
            if(k - (long) dis * cnt > 0) {
                k -= (long) dis * cnt;
                prev = list.get(p).time;
                p++;
                cnt--;
            } else {
                return list.get((int) (p + (k % cnt))).idx;
            }
        }
        return -1;
    }
}