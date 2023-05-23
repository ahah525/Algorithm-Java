package programmers.level3;


import java.util.HashSet;
import java.util.Set;

/**
 * [문제명] N으로 표현
 * [풀이시간] 1시간 10분 / 40분(21분 + 19분)
 * [한줄평] 풀이를 봐도 이해가 어려웠던 문제였다. / 감을 찾기는 했지만 결국 반례를 해결하지 못해 풀이를 참고했다.
 * 1_v1. DP(성공)
 * 2_v1. (실패 - 5~9 실패)
 * [접근법] 하나의 경우만 고려
 * 1) n을 i번 사용해서 만든 수의 집합 = n을 (i - 1)번 사용해서 만든 수의 집합 X n을 1번 사용해서 만든 수의 집합
 * 2_v2. (성공)
 * [해결법] 모든 경우의 수 계산
 * 1) n을 i번 사용해서 만든 수의 집합 = n을 (i - 1)번 사용해서 만든 수의 집합 X n을 1번 사용해서 만든 수의 집합
 * 2) n을 i번 사용해서 만든 수의 집합 = n을 (i - 2)번 사용해서 만든 수의 집합 X n을 2번 사용해서 만든 수의 집합
 * ...
 * 3) n을 i번 사용해서 만든 수의 집합 = n을 1번 사용해서 만든 수의 집합 X n을 (i - 1)번 사용해서 만든 수의 집합
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42895">문제</a>
 * @See <a href="https://alreadyusedadress.tistory.com/115">풀이</a>
 */
class Solution42895 {
    public static void main(String[] args) {
        // 4
        System.out.println(solution(5, 12));

        // 3
        System.out.println(solution(2, 11));
    }

    // 1_v1, 2_v2
    /**
     * @param N 숫자(1 이상 9 이하)
     * @param number 숫자(1 이상 32,000 이하)
     * @return N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값
     */
    public static int solution(int N, int number) {
        Set<Integer>[] set = new HashSet[9];
        int tmp = N;
        for(int i = 1; i <= 8; i++) {
            set[i] = new HashSet<>();
            // 1. N을 i번 연달아 사용한 수
            set[i].add(tmp);
            // j 를 1 ~ (i - 1)까지 반복
            for(int j = 1; j < i; j++) {
                // 2. N을 j번 사용한 집합 & N을 (i - j)번 사용한 집합의 사칙연산 결과 => N을 i번 사용한 결과
                for(int a : set[j]) {
                    for(int b : set[i - j]) {
                        set[i].add(a + b);
                        set[i].add(a - b);
                        set[i].add(a * b);
                        if(b != 0)
                            set[i].add(a / b);
                    }
                }
            }
//            System.out.println(set[i]);
            // 3. N을 i번 사용한 결과값에 number 가 있으면 i 리턴
            if(set[i].contains(number)) return i;
            tmp = tmp * 10 + N;
        }
        // 4. N을 8번 사용해도 number 를 만들수 없으면 -1 리턴
        return -1;
    }
}