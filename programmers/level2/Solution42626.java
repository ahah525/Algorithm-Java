package programmers.level2;


import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [한줄평] 매번 최솟값 2개를 구해야한다는 부분에서 최소힙 풀이를 쉽게 떠올렸던 문제였다.
 *
 * v1. PriorityQueue(최소힙) 사용
 */
class Solution42626 {
    public static void main(String[] args) {
        // 2
        int scoville1[] = {1, 2, 3, 9, 10, 12};
        int k1 = 7;
        int answer1 = solution(scoville1, k1);
        System.out.println(answer1);
    }

    /**
     *
     * @param scoville 모든 음식의 스코빌 지수
     * - 길이는 2 이상 1,000,000 이하
     * - 원소는 각각 0 이상 1,000,000 이하
     * @param K 기준이되는 최소 스코빌 지수
     * - 0 이상 1,000,000,000 이하
     * @return 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수
     * - 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return
     */
    public static int solution(int[] scoville, int K) {
        int answer = 0; // 섞은 횟수
        Queue<Integer> minHeap = new PriorityQueue<>(); // 최소힙
        for(int scv : scoville) {
            minHeap.add(scv);
        }

        while(minHeap.peek() < K) {
            // 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우 -1 리턴
            if(minHeap.size() < 2) {
                return -1;
            }
            answer++;
            // 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
            minHeap.add(minHeap.poll() + minHeap.poll() * 2);
        }
        return answer;
    }
}