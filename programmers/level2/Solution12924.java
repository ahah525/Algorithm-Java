package programmers.level2;


/**
 * [문제명] 숫자의 표현
 * [풀이시간] 30분
 * [한줄평] 보자마자 투포인터로 풀어야겠다고 떠올렸는데, 투포인터 문제는 항상 풀 때 조건 순서가 헷갈린다..
 * v1. 투포인터(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12924">문제</a>
 */
class Solution12924 {
    public static void main(String[] args) {
        // 4
        System.out.println(solution(15));
    }

    /**
     * @param n 10,000 이하의 자연수
     * @return 연속된 자연수들로 n을 표현하는 방법의 수
     */
    public static int solution(int n) {
        int answer = 0;
        int left = 1;
        int right = 1;
        int sum = 0;

        while(true) {
            if(sum == n) {
//                System.out.println("left = " + left);
//                System.out.println("right = " + right);
//                System.out.println("sum = " + sum);
//                System.out.println("=========");
                answer++;
                sum -= left;
                left++;
            } else if(sum > n) {
                sum -= left;
                left++;
            } else if(right == n + 1) {
                break;
            } else {
                sum += right;
                right++;
            }
        }
        return answer;
    }
}