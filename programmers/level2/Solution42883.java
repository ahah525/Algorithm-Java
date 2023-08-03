package programmers.level2;


import java.util.Stack;

/**
 * [문제명] 큰 수 만들기
 * [풀이시간] 2시간 / 32분(30분 + 2분) / 18분(13분 + 5분) / 12분
 * [한줄평] stack 을 사용해야겠다는 것은 떠올렸는데 구현하는 과정에서 경우의 수를 처리하는 부분이 어려웠다.
 * 결국 풀이를 보고 해결했는데, 생각보다 너무 코드가 짧아서 놀랐고 꼭 다시 한번 풀어봐야할 문제인 것 같다. stack 이 아닌 for 문으로 해결한 풀이법도 있었다.
 * / 결국 stack 이라는 힌트를 보고 해결했던 문제였다.
 * / 두 번째 풀 때와 마찬가지로 같은 반례를 해결하지 못해서 시간이 좀 걸렸다.
 * / 여러 번 풀어서 쉽게 풀 수 있었던 문제였다.
 * 1_v1. stack(실패)
 * - 경우에 따라서 스택에 선택적으로 넣고 빼는 방식으로 구현했다가 실패했다.
 * 1_v2. stack(성공)
 * - 1 -> 2 순서로 진행하고 경우의 수를 따지지 않고 일단 모두 넣는다는 점이 핵심이다.
 * - 반례를 고려해서 3번 과정을 수행하는 것이 필수적이다!!
 * [로직]
 * 1. 스택에서 제거하기(1, 2 조건을 모두 만족할 때)
 *  1) 제거 가능한 횟수가 아직 0보다 큼
 *  2) 스택이 비어있지 않은 상태에서 스택의 peek() 값 > 현재값
 * 2. 다 제거했으면 현재값 넣기
 * 3. 제거 가능한 횟수가 아직 0보다 크면 스택에서 k개 제거하기
 * 4. 스택에 있는 값들을 string 으로 변환
 * 2_v1. stack(실패 - 12 실패)
 * 2_v2. stack(성공)
 * [반례] "4321" , 2 >> "43"
 * [해결] k번 pop 되지 않은 경우 남은 횟수 만큼 pop 해야한다.
 * 3_v1. stack(실패 - 12 실패)
 * 3_v2. stack(성공)
 * [해결] 2_v2 반례와 동일
 * 4_v1. stack(성공)
 * [풀이] 1_v2 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42883">문제</a>
 * @See <a href="https://school.programmers.co.kr/questions/25858">반례</a>
 * @See <a href="https://velog.io/@soo5717/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%81%B0-%EC%88%98-%EB%A7%8C%EB%93%A4%EA%B8%B0-%ED%8C%8C%EC%9D%B4%EC%8D%AC">문제 힌트</a>
 * @See <a href="https://mungto.tistory.com/45">다른 풀이 - for문</a>
 */
class Solution42883 {

    public static void main(String[] args) {
        // "94"
        String number1 = "1924";
        int k1 = 2;
        System.out.println(solution(number1, k1));

        // "3234"
        String number2 = "1231234";
        int k2 = 3;
        System.out.println(solution(number2, k2));

        // "775841"
        String number3 = "4177252841";
        int k3 = 4;
        System.out.println(solution(number3, k3));
    }

    // 1_v2, 2_v2, 3_v2, 4_v1
    /**
     * @param number 2자리 이상, 1,000,000자리 이하인 숫자
     * @param k 제거할 수의 개수
     * @return number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로
     */
    public static String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();
        for(char c : number.toCharArray()) {
            // 1. 스택에서 제거하기
            while(k > 0 && !stack.isEmpty() && stack.peek() < c) {
                stack.pop();
                k--;
            }
            // 2. 다 제거했으면 현재값 넣기
            stack.push(c);
        }
        // 3. 제거 가능한 횟수가 아직 0보다 크면 스택에서 k개 제거하기
        for(int i = 0; i < k; i++) {
            stack.pop();
        }
        // 4. 스택에 있는 값들을 string 으로 변환
        StringBuilder sb = new StringBuilder();
        for(char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}