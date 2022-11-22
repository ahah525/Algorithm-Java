package programmers.level1;

/**
 * 두 정수 사이의 합
 * v1. for 문 이용
 * v2. 등차수열 공식 이용(빠름)
 * Sn = n * (A1 + An) / 2
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12912/solution_groups?language=java">풀이 참고</a>
 * @See <a href="https://ko.wikihow.com/%EB%93%B1%EC%B0%A8%EC%88%98%EC%97%B4%EC%9D%98-%ED%95%A9%EC%9D%84-%EA%B5%AC%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95">등차수열의 합 구하는 방법</a>
 */
class Solution12912 {
    public static void main(String[] args) {
        // 12
        int a1 = 3;
        int b1 = 5;
        long answer1 = solution1(a1, b1);
        System.out.println(answer1);

        // 3
        int a2 = 3;
        int b2 = 3;
        long answer2 = solution1(a2, b2);
        System.out.println(answer2);

        // 12
        int a3 = 5;
        int b3 = 3;
        long answer3 = solution1(a3, b3);
        System.out.println(answer3);
    }

    /**
     * for 문을 이용한 a ~ b 합 구하기
     * @param a -10,000,000 이상 10,000,000 이하인 정수
     * @param b -10,000,000 이상 10,000,000 이하인 정수
     * @return 두 정수 a, b가 주어졌을 때 a와 b 사이에 속한 모든 정수의 합을 리턴
     */
    public static long solution1(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        long answer = 0;

        for(int i = min; i <= max; i++) {
            answer += i;
        }
        return answer;
    }

    /**
     * 등차수열 공식을 이용한 a ~ b 합 구하기
     */
    public static long solution2(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);

        return (long)(max - min + 1) * (min + max) / 2;
    }
}