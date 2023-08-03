package programmers.level2;


import java.util.Arrays;
import java.util.Stack;

/**
 * [문제명] 주식가격
 * [풀이시간] / 26분, 40분 / 28분 / 21분
 * [한줄평] 구현은 어렵지 않으나, 문제에 대한 이해가 부족하면 풀 수 없는 문제였다.
 * / 반복문으로 풀면 시간초과가 날 것 같아서 접근법을 고민하다가 결국 해답을 찾지 못하고 반복문으로 풀었다.
 * / 3번째 풀었지만 스택 문제인 줄 몰랐다면 못풀었을 것 같은 문제다.
 * / 스택 유형의 문제라는 것을 몰랐으면 또 못풀었을 것 같다. 꼭 복습이 필요하다!
 * 1_v1. 2중 for 문(실패: 테스트 1 제외하고 모두 실패)
 * - i : 0 ~ n - 1
 * - j : i + 1 ~ n - 1
 * 1_v2. 2중 for 문(성공)
 *  - i : 0 ~ n - 1
 *  - j : i ~ n - 2
 *  [반례]
 *  - "가격이 떨어지지 않은 기간" 을 구하는 것이기 때문에, 가격이 떨어진 이후에 대해서는 카운팅할 필요가 없다는 것이 중요 포인트!
 *  - 5 -> 4로 가격이 떨어진다고 했을 때, 실제로 1초간 가격이 5로 유지된 것이므로 0이 아닌 1로 계산해야 한다
 * 2_v1. 2중 for 문(성공)
 * 2_v2. Stack(성공) -> 추천
 * [풀이] 주식 감소 시점에 이전 시점들의 떨어지지 않은 기간을 계산하는 것이 핵심이다!!!
 * - A, B, C 시점까지 계속 증가하다가 D 시점에 처음 감소했다면, 시점(C -> B -> A)순으로 prices[시점] > prices[D] 인 곳의 떨어지지 않은 기간을 계산하는 방식
 * 3_v1. Stack(성공)
 * [풀이] 2_v2 동일
 * 4_v1. Stack(성공)
 * [풀이] 2_v2 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42584">문제</a>
 * @see <a href="https://school.programmers.co.kr/questions/39058">질문 힌트</a>
 * @see <a href="https://girawhale.tistory.com/7">Stack 풀이</a>
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/154539">유사 문제</a>
 */
class Solution42584 {
    public static void main(String[] args) {
        // [4, 3, 1, 1, 0]
        System.out.println(Arrays.toString(solution2(new int[]{1, 2, 3, 2, 3})));

        // 반례) [1, 1, 1, 1, 0]
        System.out.println(Arrays.toString(solution2(new int[]{5, 4, 3, 2, 5})));
    }

    // 1_v2, 2_v1
    /**
     * 초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수
     * @param prices 초 단위로 기록된 주식가격이 담긴 배열
     * - 길이는 2 이상 100,000 이하
     * - 각 가격은 1 이상 10,000 이하인 자연수
     * @return 가격이 떨어지지 않은 기간은 몇 초인지를 return
     */
    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for(int i = 0; i < prices.length; i++) {
            int t = 0;  // 가격이 떨어지지 않은 기간
            for(int j = i + 1; j < prices.length; j++) {
                t++;    // 시간 먼저 카운팅
                if(prices[i] > prices[j]) break;    // 떨어지면 바로 종료
            }
            answer[i] = t;
        }
        return answer;
    }

    // 2_v2, 3_v1, 4_v1
    public static int[] solution2(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();   // 떨어지지 않은 기간을 계산해야하는 시점들
        // i = 현재 시점
        for(int i = 0; i < prices.length; i++) {
            int cur = prices[i];    // 현재 주식 가격
            // 1. 스택이 비지않고 현재 시점에 주식 가격이 감소했다면
            while(!stack.isEmpty() && prices[stack.peek()] > cur) {
                // 2. 이전 주식 가격들 중 현재 주식 가격보다 높은 가격을 가진 시점의 떨어지지 않은 기간 계산
                answer[stack.peek()] = i - stack.peek();    // 이전 주식 가격의 떨어지지 않은 기간 = 현재 주식 가격의 시점 - 이전 주식 가격의 시점
                stack.pop();    // 떨어지지 않은 기간을 기록했으니 stack 에서 꺼내기
            }
            // 3. 미래의 주식 감소 시점에 현재 시점(i)의 떨어지지 않은 기간을 계산하기 위해 stack 에 삽입
            stack.push(i);
        }
        // 4. 마지막 주식 시점까지 주식 감소 시점이 없는 경우, 아직 스택에 남은 시점을 대상으로 떨어지지 않은 기간을 계산해주어야 한다!
        while(!stack.isEmpty()) {
            // 2. 이전 주식 가격들 중 현재 주식 가격보다 높은 가격을 가진 시점의 떨어지지 않은 기간 계산
            answer[stack.peek()] = (prices.length - 1) - stack.peek();    // 이전 주식 가격의 떨어지지 않은 기간 = 현재 주식 가격의 시점 - 이전 주식 가격의 시점
            stack.pop();    // 떨어지지 않은 기간을 기록했으니 stack 에서 꺼내기
        }
        return answer;
    }
}