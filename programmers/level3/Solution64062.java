package programmers.level3;


/**
 * [문제명] 징검다리 건너기
 * [풀이시간] 1시간 30분(45분 + 45분) / 50분 / 23분
 * [한줄평] 그대로 구현했더니 효율성 테스트가 모두 실패했고 결국 풀이 힌트를 얻고 풀었다. 이분탐색일거라고는 전혀 생각하지 못했어서 다음에 꼭 한번 풀어봐야하는 문제인 것 같다.
 * / 이분탐색이라는 것을 알고 풀어서 전보다는 쉽게 풀었다.
 * / stones 배열의 원소들의 값이 최대 200,000,000 이하라고 했기 때문에 이진탐색을 사용해서 쉽게 풀었다.
 * 1_v1. 구현(실패 - 효율성 테스트 1 ~ 14 실패)
 * 1_v2. 이분탐색(성공)
 * 2_v1. 이분탐색(성공)
 * 3_v1. 이분탐색(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/64062">문제</a>
 * @See <a href="https://velog.io/@hyunjkluz/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4640464-%EC%A7%95%EA%B2%80%EB%8B%A4%EB%A6%AC-%EA%B1%B4%EB%84%88%EA%B8%B0-Java">풀이 참고</a>
 */
class Solution64062 {
    public static void main(String[] args) {
        // 3
        int[] stones1 = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k1 = 3;
        System.out.println(solution2(stones1, k1));
    }

    // 1_v1, 3_v1
    /**
     * @param stones 디딤돌에 적힌 숫자가 순서대로 담긴 배열
     * @param k 한 번에 건너뛸 수 있는 디딤돌의 최대 칸수
     * @return 최대 몇 명까지 징검다리를 건널 수 있는지
     */
    public static int solution2(int[] stones, int k) {
        int left = 1;            // 건널 수 있는 최소 인원
        int right = 200000000;    // 건널 수 있는 최대 인원
        int answer = 0;
        // left, right 가 엇갈릴 때까지 반복
        while(left <= right) {
            int mid = (left + right) / 2;  // 건너야 하는 인원
            // 1.해당 인원이 모두 다리를 건널 수 있는지 검사
            if(canPass(stones, k, mid)) {
                // 1-1. 다리를 건널 수 있으면 오른쪽 탐색
                left = mid + 1;
                answer = Math.max(answer, mid); // 현재까지 최대 디딤돌 수 = mid
            } else {
                // 1-2. 다리를 건널 수 없으면 왼쪽 탐색
                right = mid - 1;
            }
        }
        return answer;
    }

    // n 명이 모두 다리를 건널 수있는지 여부
    public static boolean canPass(int[] stones, int k, int n) {
        int cnt = 0;    // 연속되는 0 이하의 값
        for(int i = 0; i < stones.length; i++) {
            // 1. (n - 1) 명이 건너고 난 후 디딤돌 값 구하기
            int num = stones[i] - (n - 1);
            // 2. 디딤돌 값이 0 이하인 디딤돌 개수 구하기(연속적인 구간에 속한 디딤돌)
            if (num <= 0) cnt++;
            else cnt = 0;
            // 3. 해당 연속 구간에 속한 디딤돌이 k개 이상이면 한 번에 k칸을 점프해서 0이 아닌 디딤돌로 갈 수가 없다
            if(cnt >= k) return false;
        }
        return true;
    }

    // 2_v1
    public int solution(int[] stones, int k) {
        int answer = 0;
        // 1. 건널 수 있는 최소, 최대인원 초기화
        int start = 1;
        int end = 200000000;
        // 2. start, end 엇갈릴 때까지 반복
        while(start <= end) {
            int mid = (start + end) / 2;
            int jump = 0;   // 건너뛴(밟지 않은) 돌의 개수
            boolean possible = true;    // mid 명이 건널 수 있는지 여부
            // mid 명이 건너고 난 후 각 돌의 수를 검증
            for(int stone : stones) {
                if(stone - mid >= 0) {
                    // 3. 돌의 숫자가 0 이상이면, 이전 돌에서 해당 돌로 올 수 있음 -> 0개를 건너뜀(이전 돌에서 현재 돌로 바로 옴)
                    jump = 0;
                } else {
                    // 4. 돌의 숫자가 음수면, 이전 돌에서 해당 돌로 올수 없음 -> 1개를 건너뜀
                    jump++;
                    // 5. 건너뛴 돌의 개수가 k 개 이면 mid 명이 건널 수는 없음
                    if(jump == k) {
                        possible = false;
                        break;
                    }
                }
            }
            if(possible) {
                // 6. mid 명이 건널 수 있으면, mid 오른쪽으로 범위 조정
                start = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                // 7. mid 명이 건널 수 없으면, mid 왼쪽으로 범위 조정
                end = mid - 1;
            }
        }
        return answer;
    }
}