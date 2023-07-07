package programmers.level2;


/**
 * [문제명] 이모티콘 할인행사
 * [풀이시간] (35분)
 * [한줄평]
 * 1_v1. DFS, 완전탐색(실패 - 13,15,18 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/150368">문제</a>
 */
class Solution150368 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    int[] discounts = {10, 20, 30, 40};
    double[] per = {0.9, 0.8, 0.7, 0.6};
    int[] costs;
    int maxCnt; // 최대 가입자수
    int maxCost; // 최대 가입자수일 때, 판매액
    public int[] solution(int[][] users, int[] emoticons) {
        maxCnt = -1;
        maxCost = -1;
        costs = new int[users.length];
        dfs(0, users, emoticons);
        return new int[] {maxCnt, maxCost};
    }

    // 중복 순열
    public void dfs(int depth, int[][] users, int[] emoticons) {
        if(depth == emoticons.length) {
            int totalCnt = 0; // 총가입자수
            int totalCost = 0; // 총비용
            for(int i = 0; i < users.length; i++) {
                // 기준 금액 이상 나오면, 이모티콘 플러스 가입
                if(costs[i] >= users[i][1]) totalCnt++;
                else totalCost += costs[i];
            }
            // System.out.println(String.format("%d %d", totalCnt, totalCost));
            //
            if(maxCnt < totalCnt ||
                    (maxCnt == totalCnt && maxCost < totalCost)) {
                maxCnt = totalCnt;
                maxCost = totalCost;
            }
            return;
        }

        for(int i = 0; i < discounts.length; i++) {
            int[] extraCosts = new int[users.length];
            // 유저들 계산
            for(int j = 0; j < users.length; j++) {
                if(discounts[i] >= users[j][0]) {
                    extraCosts[j] = (int) (emoticons[depth] * per[i]);
                    costs[j] += extraCosts[j];
                }
            }
            dfs(depth + 1, users, emoticons);
            // 원상복귀 계산
            for(int j = 0; j < users.length; j++) {
                if(discounts[i] > 0) {
                    costs[j] -= extraCosts[j];
                }
            }
        }
    }
}