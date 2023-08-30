package programmers.level4;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [문제명] 무지의 먹방 라이브
 * [풀이시간] 2시간 5분(1시간 33분+32분) / 1시간 20분(20분+1시간)
 * [한줄평] 생각보다 어려웠고 결국 풀이를 보고 이해했던 문제다. 솔직히 답을 안봤으면 정렬을 해야겠다는 생각조차 못했을 것 같다.
 * / 너무 어려워서 풀이를 보고 풀었고 꼭 복습이 필요하다.
 * 1_v1. (실패 - 정확성 14.7, 효율성 0)
 * 1_v2. 구현, 정렬(성공)
 * 2_v1. 구현(실패 - 정확성 16,19,20,23 시간초과, 효율성 1~8 시간초과)
 * [풀이] 무식하게 k번 반복
 * 2_v2. 구현, 정렬(성공)
 * [풀이] 1_v2 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42891">문제</a>
 * @See <a href="https://moonsbeen.tistory.com/371">풀이</a>
 * @See <a href="https://www.youtube.com/watch?v=4MWxAt4fx5I">풀이</a>
 */
class Solution42891 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2, 2_v1
    class Food {
        int idx;
        int time;
        Food(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }

    public int solution(int[] foodTimes, long k) {
        int n = foodTimes.length;
        List<Food> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(new Food(i + 1, foodTimes[i]));
        }
        // 1. 시간 오름차순 정렬
        Collections.sort(list, (o1, o2) -> o1.time - o2.time);
        //
        int prev = 0;   // 이전 음식 소요 시간
        int i = 0;
        for(Food cur : list) {
            int h = cur.time - prev;
            // 2. 이전 음식과 현재 음식의 소요 시간이 같으면 패스
            if(h == 0) {
                i++;
                continue;
            }
            // 3. 한 번에 처리할 수 있는 음식(시간) 개수 계산
            int w = n - i;  // 남은 음식 개수
            long cnt = (long) h * w;
            if(k >= cnt) {
                k -= cnt;
                prev = cur.time;
                i++;
            } else {
                // [i, n) 구간의 서브 리스트 추출
                List<Food> subList = list.subList(i, n);
                Collections.sort(subList, (o1, o2) -> o1.idx - o2.idx);
                k %= w;
                return subList.get((int) k).idx;
            }
        }
        return -1;
    }
}