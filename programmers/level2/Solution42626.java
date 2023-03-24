package programmers.level2;


import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] 더 맵게
 * [풀이시간] / 15분
 * [한줄평] 매번 최솟값 2개를 구해야한다는 부분에서 최소힙 풀이를 쉽게 떠올렸던 문제였다. / 쉽게 풀수 있었던 기본 문제였다.
 * 1_v1. PriorityQueue(최소힙)(성공)
 * 2_v1. PriorityQueue(최소힙)(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42626">문제</a>
 */
class Solution42626 {
    public static void main(String[] args) {
        // 2
        int scoville1[] = {1, 2, 3, 9, 10, 12};
        int k1 = 7;
        int answer1 = solution(scoville1, k1);
        System.out.println(answer1);
    }

    // 1_v1, 2_v1
    /**
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
        // 1. 스코빌 지수 오름차순 정렬
        Queue<Integer> minHeap = new PriorityQueue<>(); // 최소힙
        for(int scv : scoville) {
            minHeap.add(scv);
        }
        while(true) {
            // 2. 스코빌 지수 최솟값이 k 이상이면 종료
            if(minHeap.peek() >= K) break;
            // 3. 스코빌 지수 최솟값이 k 보다 작은데 남은 음식이 1개면, 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없음
            if(minHeap.size() < 2) {
                answer = -1;
                break;
            }
            // 4. 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
            minHeap.add(minHeap.poll() + minHeap.poll() * 2);
            answer++;
        }
        return answer;
    }
}