package programmers.level2;


/**
 * [문제명] 다음 큰 숫자
 * [풀이시간] 30분
 * [한줄평]
 * v1. 그냥 구현(실패 - 효율성 테스트 모두 시간초과)
 * - (n + 1)부터 값을 하나씩 늘려가며 2진수로 변환했을 때 1의 개수를 비교하며 정답을 찾는 방식으로 구현했다.
 * 시간초과가 날 것이라고 예상을 못한 것은 아니지만 규칙을 찾는 것이 쉽지 않아 이 방식으로 구현했다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12911">문제</a>
 */
class Solution12911 {
    public static void main(String[] args) {
        // 83
        System.out.println(solution(78));

        // 23
        System.out.println(solution(15));
    }

    public static int solution(int n) {
        // n 의 1의 개수
        String s = Integer.toString(n, 2);
        int a = s.length() - s.replaceAll("1", "").length();
        int num = n + 1;
        while(true) {
            String s1 = Integer.toString(num, 2);
            int b = s1.length() - s1.replaceAll("1", "").length();
            if(a == b)
                break;
            num++;
        }
        return num;
    }
}