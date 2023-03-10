package programmers.level2;


import java.util.Arrays;
import java.util.Stack;

/**
 * [문제명] 뒤에 있는 큰 수 찾기
 * [풀이시간] 1시간 10분(4분 + 1시간 6분) / 6분
 * [한줄평] 2중 for문으로 풀면 시간초과가 난다는 것을 알았으나 어떻게 접근해야할지 몰라 결국 풀이를 봤는데, 답을 보고도 이해하기 어려웠던 문제였다. / 유사 문제를 풀고 풀었더니 빨리 풀 수 있었다.
 * 1_v1. 2중 for문(실패-20, 21, 22, 23 시간초과)
 * 1_v2. Stack(성공)
 * 2_v1. Stack(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/154539">문제</a>
 * @See <a href="https://velog.io/@ujone/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%92%A4%EC%97%90-%EC%9E%88%EB%8A%94-%ED%81%B0-%EC%88%98-%EC%B0%BE%EA%B8%B0-JAVA">풀이 참고</a>
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42584">유사 문제</a>
 */
class Solution154539 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param numbers 정수로 이루어진 배열(4 ≤ numbers의 길이 ≤ 1,000,000)
     * @return 모든 원소에 대한 뒷 큰수들을 차례로 담은 배열
     * - 뒷큰수 = 배열 의 각 원소들에 대해 자신보다 뒤에 있는 숫자 중에서 자신보다 크면서 가장 가까이 있는 수
     */
    public int[] solution(int[] numbers) {
        // 1. 배열을 -1 로 초기화
        int n = numbers.length;
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++) {
            int cur = numbers[i]; // 현재 숫자
            // 증가시점에 배열에 기록하고 스택에서 꺼내기
            while(!stack.isEmpty() && numbers[stack.peek()] < cur) {
                answer[stack.peek()] = cur;
                stack.pop();
            }
            stack.push(i);
        }
        return answer;
    }
}