package programmers.level4;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [문제명] 무지의 먹방 라이브
 * [풀이시간] 2시간 5분(1시간 33분 + 32분)
 * [한줄평] 생각보다 어려웠고 결국 풀이를 보고 이해했던 문제다. 솔직히 답을 안봤으면 정렬을 해야겠다는 생각조차 못했을 것 같다.
 * 1_v1. (실패 - 정확성 14.7, 효율성 0)
 * 1_v2. 구현, 정렬(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42891">문제</a>
 * @See <a href="https://moonsbeen.tistory.com/371">풀이</a>
 * @See <a href="https://www.youtube.com/watch?v=4MWxAt4fx5I">풀이</a>
 */
class Solution42891 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    class Food {
        int idx;
        int time;
        Food(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }

    public int solution(int[] foodTimes, long k) {
        //
        int len = foodTimes.length;
        List<Food> list = new ArrayList<>();
        for(int i = 0; i < len; i++) {
            list.add(new Food(i + 1, foodTimes[i]));
        }
        //
        Collections.sort(list, (o1, o2) -> o1.time - o2.time);
        //
        int prev = 0;
        int idx = 0;
        for(Food f : list) {
            int diff = f.time - prev;
            if(diff != 0) {
                long cnt = (long) diff * len;
                if(k - cnt >= 0) {
                    k -= cnt;
                    prev = f.time;
                } else {
                    k %= len;
                    List<Food> subList = list.subList(idx, foodTimes.length);
                    Collections.sort(subList, (o1, o2) -> o1.idx - o2.idx);
                    return subList.get((int) k).idx;
                }
            }
            idx++;
            len--;
        }
        return -1;
    }
}