package programmers.level2;


import java.util.Arrays;
import java.util.Stack;

/**
 * [문제명] 뒤에 있는 큰 수 찾기
 * [풀이시간] 1시간 10분(4분 + 1시간 6분)
 * [한줄평] 2중 for문으로 풀면 시간초과가 난다는 것을 알았으나 어떻게 접근해야할지 몰라 결국 풀이를 봤는데, 답을 보고도 이해하기 어려웠던 문제였다.
 * 1_v1. 2중 for문(실패-20, 21, 22, 23 시간초과)
 * 1_v2. Stack(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/154539">문제</a>
 * @See <a href="https://velog.io/@ujone/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%92%A4%EC%97%90-%EC%9E%88%EB%8A%94-%ED%81%B0-%EC%88%98-%EC%B0%BE%EA%B8%B0-JAVA">풀이 참고</a>
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
        int n = numbers.length;
        // 1. 뒷큰수 배열을 -1로 초기화
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        // 2. 뒷큰수를 정해야하는 인덱스(0은 넣고 시작)
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for(int i = 1; i < n; i++) {
            while(!stack.isEmpty()) {
                // 3. 스택에서 인덱스 꺼내기
                int idx = stack.peek();
                if(numbers[i] > numbers[idx]) {
                    // 4. 현재값 > 스택에서 꺼낸 인덱스가 가리키는 값 이면 idx 번째 뒤큰수 결정하기
                    answer[idx] = numbers[i];
                    stack.pop();
                } else {
                    // 5. 현재값 <= 스택에서 꺼낸 인덱스가 가리키는 값 이면
                    break;
                }
            }
            // 6. 스택에 현재값 인덱스 넣기
            stack.push(i);
        }
        return answer;
    }
}