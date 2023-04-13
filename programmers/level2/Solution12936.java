package programmers.level2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [문제명] 줄 서는 방법
 * [풀이시간] 1시간 20분(20분 + 1시간) / 1시간 4분(57분 + 7분)
 * [한줄평] 아이디어는 떠올렸는데 그것을 수식화하지 못해서 결국 정답 풀이를 보고 풀었다. 생각보다 너무 어려웠다.. / 접근방법을 알아도 수식으로 나타내기가 어려웠던 문제다.
 * 1_v1. 순열(실패-효율성 테스트 모두 실패)
 * - 단순 재귀로 k번째 경우의 수를 찾을 때까지 모든 경우의 수 탐색하는 방법
 * 1_v2. 수학(성공)
 * [접근법] 선택할 수 있는 숫자 리스트로 관리
 * 2_v1. 수학(실패)
 * 2_v2. 수학(성공) -> 빠름
 * [접근법] 선택할 수 있는 숫자 boolean[] 관리
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12936">문제</a>
 * @See <a href="https://tosuccess.tistory.com/75">풀이 참고</a>
 */
class Solution12936 {
    public static void main(String[] args) {
        //
        System.out.println(Arrays.toString(solution(3, 5)));
    }

    /**
     * @param n 사람의 수
     * @param k 자연수
     * @return 사람을 나열 하는 방법을 사전 순으로 나열 했을 때, k번째 방법
     */
    public static int[] solution(int n, long k) {
        int[] answer = new int[n];
        // 선택할 수 있는 숫자 리스트
        List<Integer> list = new ArrayList<>();
        long total = 1; // 전체 경우의 수
        for(int i = 1; i <= n; i++) {
            total *= i;
            list.add(i);
        }
        k--;    // 인덱스를 맞추기 위해

        int idx = 0;
        while(n > 0) {
            long group = total / n; // 한 그룹에 속한 숫자 개수(첫숫자가 같은 것끼리 묶는다고 가정했을 때)
            // 전체(total)중에서 k가 몇번째 그룹에 속하는지
            int order = (int) (k / group);
            answer[idx++] = list.get(order);
            list.remove(order);
            // 전체 개수, 전체 중에서 몇번째인지 갱신
            total = group;
            k %= total;
            n--;
        }
        return answer;
    }

    // 2_v1
    public int[] solution2(int n, long k) {
        int[] answer = new int[n];
        // 숫자 선택여부
        boolean[] visited = new boolean[n + 1];
        long total = 1;
        for(int i = 2; i <= n; i++) {
            total *= i;
        }
        int num = n;    // 사람수
        --k;
        for(int i = 0; i < n; i++) {
            // 1. k 가 몇번째 부모의 자식인지
            int parent = (int) (k * num / total);
            // 2. 선택한 숫자를 제외하고 parent 번째 숫자 찾기
            int cnt = 0;
            for(int j = 1; j <= n; j++) {
                if(!visited[j]) cnt++;
                if(cnt == parent + 1) {
                    answer[i] = j;
                    visited[j] = true;
                    break;
                }
            }
            // System.out.println(parent);
            // 3.
            total /= num;
            k %= total;
            num--;
        }
        return answer;
    }
}