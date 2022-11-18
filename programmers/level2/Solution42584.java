package programmers.level2;


import java.util.ArrayList;
import java.util.List;

/**
 * [한줄평] 구현은 어렵지 않으나, 문제에 대한 이해가 부족하면 풀 수 없는 문제였다.
 * - "가격이 떨어지지 않은 기간" 을 구하는 것이기 때문에, 가격이 떨어진 이후에 대해서는 카운팅할 필요가 없다는 것이 중요 포인트!
 * - 5 -> 4로 가격이 떨어진다고 했을 때, 실제로 1초간 가격이 5로 유지된 것이므로 0이 아닌 1로 계산해야 한다
 *
 * v1. 2중 for 문 사용 (실패: 테스트 1 제외하고 모두 실패)
 * - i : 0 ~ n - 1
 * - j : i + 1 ~ n - 1
 * v2. 2중 for 문 사용 (성공)
 *  - i : 0 ~ n - 1
 *  - j : i ~ n - 2
 *
 * @see <a href="https://school.programmers.co.kr/questions/39058">질문 힌트</a>
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12916/solution_groups?language=java">다른 풀이</a>
 */
class Solution42584 {
    public static void main(String[] args) {
        // [4, 3, 1, 1, 0]
        int[] prices1 = {1, 2, 3, 2, 3};
        List<Integer> answer1 = solution(prices1);
        System.out.println(answer1);

        // 반례) [1, 1, 1, 1, 0]
        int[] prices2 = {5, 4, 3, 2, 5};
        List<Integer> answer2 = solution(prices2);
        System.out.println(answer2);
    }

    /**
     * 초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수
     * @param prices 초 단위로 기록된 주식가격이 담긴 배열
     * - 길이는 2 이상 100,000 이하
     * - 각 가격은 1 이상 10,000 이하인 자연수
     * @return 가격이 떨어지지 않은 기간은 몇 초인지를 return
     */
    public static List<Integer> solution(int[] prices) {
        List<Integer> answer = new ArrayList<>();
        int n = prices.length;
        for(int i = 0; i < n; i++) {
            int t = 0;
            for(int j = i; j < n - 1; j++) {
                if(prices[i] <= prices[j]) t++;
                else break;
            }
            answer.add(t);
        }
        return answer;
    }
}