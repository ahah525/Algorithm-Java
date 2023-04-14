package programmers.level2;


import java.util.Arrays;

/**
 * [문제명] 구명보트
 * [풀이시간] 18분 / 37분
 * [한줄평] 문제를 대충 읽고 잘못 풀어서 시간이 좀 더 걸렸지만 아주 쉬운 그리디 문제였다. / 어떻게 해야 최솟값을 구할 수 있을지 고민하다가 처음보다 푸는데 오래걸렸다.
 * 1_v1. 그리디(성공)
 * - 그리디 문제는 정렬이 핵심이다. 오름차순 정렬 후 왼쪽, 오른쪽 포인터 2개를 사용해 최솟값을 구할 수 있다.
 * [구명보트를 최소한으로 사용하는 방법]
 * 1) (최소 + 최대 몸무게) <= 무게 제한 : 최소, 최대 같이 태우기
 * 2) (최소 + 최대 몸무게) > 무게 제한 : 최대 혼자 태우기
 * 2_v1. 그리디(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42885">문제</a>
 */
class Solution42885 {
    public static void main(String[] args) {
        // 3
        int[] people1 = {70, 50, 80, 50};
        System.out.println(solution(people1, 100));

        // 3
        int[] people2 = {70, 80, 50};
        System.out.println(solution(people2, 100));
    }

    // 1_v1, 2_v1
    /**
     * @param people 사람들의 몸무게를 담은 배열
     * @param limit 구명보트의 무게 제한
     * @return 모든 사람을 구출하기 위해 필요한 구명보트 개수의 최솟값
     */
    public static int solution(int[] people, int limit) {
        int answer = 0;
        // 1. 몸무게 오름차순 정렬
        Arrays.sort(people);
        int l = 0;                  // 왼쪽 포인터(현재 기준 최소 몸무게를 가진 사람의 인덱스)
        int r = people.length - 1;  // 오른쪽 포인터(현재 기준 최대 몸무게를 가진 사람의 인덱스)
        // 2. 포인터가 엇갈릴 때까지 반복
        while(l <= r) {
            if(people[l] + people[r] <= limit) {
                // 2-1. l, r 같이 태우기
                l++;
                r--;
            } else {
                // 2-1. r 혼자 태우기
                r--;
            }
            answer++;
        }
        return answer;
    }
}