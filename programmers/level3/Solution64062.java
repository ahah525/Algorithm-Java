package programmers.level3;


/**
 * [문제명] 징검다리 건너기
 * [풀이시간] 1시간 30분(45분 + 45분)
 * [한줄평] 그대로 구현했더니 효율성 테스트가 모두 실패했고 결국 풀이 힌트를 얻고 풀었다. 이분탐색일거라고는 전혀 생각하지 못했어서 다음에 꼭 한번 풀어봐야하는 문제인 것 같다.
 * v1. 구현(실패 - 효율성 테스트 1 ~ 14 실패)
 * v2. 이분탐색(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/64062">문제</a>
 * @See <a href="https://velog.io/@hyunjkluz/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4640464-%EC%A7%95%EA%B2%80%EB%8B%A4%EB%A6%AC-%EA%B1%B4%EB%84%88%EA%B8%B0-Java">풀이 참고</a>
 */
class Solution64062 {
    public static void main(String[] args) {
        // 3
        int[] stones1 = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k1 = 3;
        System.out.println(solution1(stones1, k1));
    }

    //
    public static int solution1(int[] stones, int k) {
        int answer = 0;
        int n = stones.length;  // 디딤돌 수
        while(true) {
            int idx = -1;   // 시작
            while(idx < n) {
                boolean jump = false;   // 건너뛰기 여부
                // k칸 이내로 가장 까운 디딤돌 찾기
                for(int i = idx + 1; i <= idx + k; i++) {
                    if(i == n) {
                        // 도착점으로 건너뛰기
                        jump = true;
                        idx = n;
                        answer++;
                        break;
                    } else if(stones[i] != 0) {
                        // 디딤돌을 갈 수 있는 상태면 건너뛰기
                        jump = true;
                        idx = i;
                        stones[idx]--;
                        break;
                    }
                }
                // 건너뛰지 못했다면
                if(!jump)
                    return answer;
            }
        }
    }
}