package programmers.level1;


/**
 * [문제명] 콜라 문제
 * [풀이시간] 25분 / 5분
 * [한줄평] 진짜 쉬운 문제였는데, 입력예제만 생각하고 빈병 a개를 주면 콜라 1개를 주는 걸로 식을 잘못설정했다. 오류를 알아차리기까지 너무 오래걸렸다..
 * / 수식만 세우면 되는 쉬운 문제였다.
 * 1_v1. (성공)
 * 2_v1. (성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/132267">문제</a>
 */
class Solution132267 {
    public static void main(String[] args) {
        // 19
        System.out.println(solution(2, 1, 20));

        // 9
        System.out.println(solution(3, 1, 20));
    }

    /**
     * @param a 콜라를 받기 위해 마트에 주어야 하는 병 수
     * @param b 빈 병 a개를 가져다 주면 마트가 주는 콜라 병 수
     * @param n 상빈이가 가지고 있는 빈 병의 개수
     * @return 상빈이가 받을 수 있는 콜라의 병 수
     */
    public static int solution(int a, int b, int n) {
        int answer = 0;
        // 빈병 a개 -> 콜라 b개
        while(n >= a) {
            int coke = (n / a) * b;    // 빈병을 교환해 받은 콜라개수
            int bottle = n % a;        // 교환하고 남은 빈병개수
            answer += coke;
            n = coke + bottle;
        }
        return answer;
    }

    // 2_v1
    public int solution2(int a, int b, int n) {
        int answer = 0;// 받는 병 수
        while(n >= a) {
            int coke = (n / a) * b;
            answer += coke;
            // 교환한 결과 = 기존 빈병 개수 + 새로 받은 콜라 개수 - 교환을 위해 제출한 빈병 개수
            n = n + coke - (n / a) * a;
        }
        return answer;
    }
}