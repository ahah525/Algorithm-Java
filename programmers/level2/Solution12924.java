package programmers.level2;


/**
 * [문제명] 숫자의 표현
 * [풀이시간] 30분 / 9분
 * [한줄평] 보자마자 투포인터로 풀어야겠다고 떠올렸는데, 투포인터 문제는 항상 풀 때 조건 순서가 헷갈린다.. / 투포인터 기초 문제였다.
 * 1_v1. 투포인터(성공)
 * 2_v1. 투포인터(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12924">문제</a>
 */
class Solution12924 {
    public static void main(String[] args) {
        // 4
        System.out.println(solution(15));
    }

    // 1_v1
    /**
     * @param n 10,000 이하의 자연수
     * @return 연속된 자연수들로 n을 표현하는 방법의 수
     */
    public static int solution(int n) {
        int answer = 0;
        int sum = 0;
        int l = 1;  // 왼쪽 포인터
        int r = 1;  // 오른쪽 포인터
        // 종료조건 명시(r = n + 2 이면, 끝구간값이 n + 1 이 되므로 종료)
        while(r <= n + 1) {
            // 1. 전처리 작업(누적합 계산) -> 포인터 이동(순서 중요!)
            if(sum >= n) {
                // 왼쪽 포인터를 오른쪽으로 1칸 이동
                sum -= l;
                l++;
            } else {
                // 오른쪽 포인터를 오른쪽으로 1칸 이동
                sum += r;
                r++;
            }
            // 2. 정답을 찾았을 때 처리
            if(sum == n) {
                answer++;
            }
        }
        return answer;
    }

    // 2_v1
    public int solution2(int n) {
        int answer = 1; // n 자체 1개 초기화
        int l = 1;
        int r = 1;
        int sum = 0;
        while(r <= n) {
            if(sum < n) {
                sum += r++;
            } else if(sum == n) {
                answer++;
                sum += r++;
            } else {
                sum -= l++;
            }
        }
        return answer;
    }
}