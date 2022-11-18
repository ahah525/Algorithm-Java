package programmers.level2;


import java.util.ArrayList;
import java.util.List;

/**
 * v1. 2중 for 사용 (실패: 테스트 1 제외하고 모두 실패)
 */
class Solution42584 {
    public static void main(String[] args) {
        // [4, 3, 1, 1, 0]
        int[] prices = {1, 2, 3, 2, 3};
        List<Integer> answer1 = solution(prices);
        System.out.println(answer1);
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

        for(int i = 0; i < prices.length; i++) {
            int t = 0;
            for(int j = i + 1; j < prices.length; j++) {
                if(prices[i] <= prices[j]) {
                   t++;
                }
            }
            answer.add(t);
        }
        return answer;
    }
}