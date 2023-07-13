package programmers.level3;


/**
 * [문제명] 최고의 집합
 * [풀이시간] / 43분(16분 + 27분) / 8분
 * [한줄평] 처음에는 재귀로 완전탐색을 하는 방식으로 접근했다가 할 때마다 배열 값을 저장하는게 부담이 커져서 규칙을 찾아서 아예 다른 방식으로 접근해서 풀었던 문제였다.
 * / 처음에 완전탐색으로 풀다가 시간초과가 났고 결국에는 풀이를 참고해서 풀었다.
 * / 공평하게 수를 분배할 때 곱이 가장 커진다는 것을 알면 쉽게 풀 수 있는 문제다.
 * 1_v1. 수학(성공)
 * [풀이] 원소의 개수 = n, 합 = s 일때, 곱이 최대가 되는 중복집합은 (n // s) 값을 반복해서 구함 => 가장 공평하게 나눈다!
 * (가정) n = 3, s = 8
 * n = 3, s = 8         -> 8 // 3 = 2
 * n = 2, s = 8 - 2 = 6 -> 6 // 2 = 3
 * n = 1, s = 6 - 3 = 3 -> 3 // 1 = 3
 * 2_v1. DFS, 완전탐색(실패 - 1~14 시간초과)
 * [풀이] 중복조합
 * 2_v2. 수학(성공)
 * [풀이] 1_v1 동일
 * 3_v1. 수학(성공)
 * [풀이] 1_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12938">문제</a>
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12938/solution_groups?language=java">다른 풀이</a>
 * @See <a href="https://escapefromcoding.tistory.com/181">풀이 참고</a>
 */
class Solution12938 {
    public static void main(String[] args) {
        // [4, 5]
        int n1 = 2;
        int s1 = 9;
        System.out.println(solution(n1, s1));

        // [-1]
        int n2 = 2;
        int s2 = 1;
        System.out.println(solution(n2, s2));

        // [4, 4]
        int n3 = 2;
        int s3 = 8;
        System.out.println(solution(n3, s3));
    }

    // 1_v1, 2_v2
    /**
     * @param n 집합의 원소의 개수
     * @param s 모든 원소들의 합
     * @return 자연수 n 개로 이루어진 중복 집합 중 아래 2 조건을 만족하는 최고의 집합
     * 1. 각 원소의 합이 S가 되는 수의 집합
     * 2. 위 조건을 만족하면서 각 원소의 곱이 최대가 되는 집합
     */
    public static int[] solution(int n, int s) {
        // 1. n > s 이면 답이 존재하지 않음
        if(n > s) return new int[] {-1};
        int[] answer = new int[n];
        int cnt = n; // 뽑아야하는 원소 수
        // 2. n개 숫자 뽑기
        for(int i = 0; i < n; i++) {
            int res = s / cnt; // cnt개의 숫자로 s를 만들기위한 가장 작은 수
            answer[i] = res;
            s -= res;
            cnt--;
        }
        return answer;
    }

    // 3_v1
    public int[] solution2(int n, int s) {
        int num = s / n;
        if(num == 0) return new int[] {-1};
        int cnt = s - (num * n);
        int[] answer = new int[n];
        for(int i = 0; i < n; i++) {
            // 1. 0번째부터 (n - cnt)개는 num
            if(i < n - cnt) answer[i] = num;
            // 2. (n - cnt)번째 부터 cnt개는 (num + 1)
            else answer[i] = num + 1;
        }
        return answer;
    }
}