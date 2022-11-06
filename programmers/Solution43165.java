package programmers;

/**
 * numbers[]: 사용할 수 있는 숫자가 담긴 배열(n개의 음이 아닌 정수)
 * target: 목표값
 * answer: 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수
 * ----------------------------------------------------------------------
 * - 주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
 * - 각 숫자는 1 이상 50 이하인 자연수입니다.
 * - 타겟 넘버는 1 이상 1000 이하인 자연수입니다.
 */
class Solution43165 {
    static int answer;

    public static void main(String[] args) {
        int[] numbers1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        // 5
        int answer1 = solution(numbers1, target1);
        System.out.println(answer1);

        int[] numbers2 = {4, 1, 2, 1};
        int target2 = 4;
        // 2
        int answer2 = solution(numbers2, target2);
        System.out.println(answer2);
    }

    public static int solution(int[] numbers, int target) {
        answer = 0;
        int n = numbers.length;
        // 이진 트리
        dfs(numbers, target, n, 0, 0);

        return answer;
    }

    public static void dfs(int[] numbers, int target, int n, int depth, int value) {
        if(depth == n) {
            if(target == value) answer++;
            return;
        }
        dfs(numbers, target, n, depth + 1, value + numbers[depth]);
        dfs(numbers, target, n, depth + 1, value - numbers[depth]);
    }
}