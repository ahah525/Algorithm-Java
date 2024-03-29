package programmers.level2;


/**
 * [문제명] 이모티콘 할인행사
 * [풀이시간] 40분(35분, 5분) / 23분 / 30분
 * [한줄평] 모든 경우를 완전탐색하면 쉽게 풀 수 있는 문제였는데, 부동소수점 문제를 몰랐다면 반례를 해결하지 못했을 것 같다.
 * / 완전탐색만 하면 쉽게 풀 수 있는 문제였다.
 * / 할인을 적용한 값을 계산하는 식을 잘못 세워서 좀 시간이 걸리긴 했지만, 쉽게 풀 수 있었다. 다시 풀어봐도 좋을 것 같다.
 * 1_v1. DFS, 완전탐색(실패 - 13,15,18 실패)
 * [해결] 할인율을 적용한 구매비용 계산식 수정
 * 1_v2. DFS, 완전탐색(성공)
 * 2_v1. DFS, 완전탐색(성공) -> 빠름
 * [풀이] 모든 이모티콘의 할인율을 선택했을 때 각 사용자의 이모티콘 구매 금액을 계산
 * 3_v1. DFS,완전탐색(성공)
 * [풀이] 2_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/150368">문제</a>
 * @See <a href="https://school.programmers.co.kr/questions/48792">반례</a>
 */
class Solution150368 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
//    int[] discounts = {10, 20, 30, 40};
//    int[] costs; // 각 사용자의 이모티콘 구매 비용
//    int maxCnt;  // 최대 가입자수
//    int maxCost; // 최대 가입자수일 때의 판매액
//
//    public int[] solution(int[][] users, int[] emoticons) {
//        maxCnt = -1;
//        maxCost = -1;
//        costs = new int[users.length];
//        dfs(0, users, emoticons);
//        return new int[] {maxCnt, maxCost};
//    }
//
//    // 중복 순열
//    public void dfs(int depth, int[][] users, int[] emoticons) {
//        // 모든 이모티콘 할인율이 정해진 경우
//        if(depth == emoticons.length) {
//            int cnt = 0;   // 총 가입자수
//            int cost = 0;  // 총 판매액
//            // 1. 각 사용자의 이모티콘 플러스 가입 여부 체크
//            for(int i = 0; i < users.length; i++) {
//                // 2. 이모티콘 구매비용이 기준 금액 이상이면, 이모티콘 플러스 가입
//                if(costs[i] >= users[i][1]) cnt++;
//                // 3. 이모티콘 구매비용이 기준 금액 미만이면, 총 판매액 합산
//                else cost += costs[i];
//            }
//            // 4. 최적의 목표 달성 여부 확인
//            // 1) 가입자 수가 더 많은 경우
//            // 2) 가입자 수가 동일한데 판매액이 더 많을 경우
//            if(maxCnt < cnt || (maxCnt == cnt && maxCost < cost)) {
//                maxCnt = cnt;
//                maxCost = cost;
//            }
//            return;
//        }
//        //
//        for(int i = 0; i < discounts.length; i++) {
//            // 1. depth 번째 이모티콘에 해당 할인율을 적용했을 때, 각 사용자들의 구매 비용 계산
//            int[] extraCosts = new int[users.length]; // 해당 이모티콘에 대한 각 사용자의 구매 비용
//            for(int j = 0; j < users.length; j++) {
//                // 2. 할인율이 본인 기준 할인율보다 높을 경우 구매
//                if(discounts[i] >= users[j][0]) {
//                    extraCosts[j] = (int) (emoticons[depth] * (double) (100 - discounts[i]) / 100);
//                    costs[j] += extraCosts[j];
//                }
//            }
//            dfs(depth + 1, users, emoticons);
//            // 3. 구매 비용 원상 복귀
//            for(int j = 0; j < users.length; j++) {
//                if(discounts[i] > 0) {
//                    costs[j] -= extraCosts[j];
//                }
//            }
//        }
//    }

    // 2_v1, 3_v1
    int[] discount = {10, 20, 30 ,40};
    int maxCnt;
    int maxPrice;

    public int[] solution2(int[][] users, int[] emoticons) {
        maxCnt = 0;
        maxPrice = 0;
        int[] path = new int[emoticons.length];
        dfs(0, path, users, emoticons);

        return new int[] {maxCnt, maxPrice};
    }

    public void dfs(int depth, int[] path, int[][] users, int[] emoticons) {
        if(depth == emoticons.length) {
            // 1. 각 사용자의 이모티콘 판매액, 플러스 가입여부 확인
            int totalCnt = 0;    // 이모티콘 플러스 가입자
            int totalPrice = 0;  // 이모티콘 판매액
            for(int[] user : users) {
                int price = getPrice(user[0], path, emoticons);
                if(price >= user[1]) totalCnt++;
                else totalPrice += price;
            }
            // 2.
            if(maxCnt < totalCnt || (maxCnt == totalCnt && maxPrice < totalPrice)) {
                maxCnt = totalCnt;
                maxPrice = totalPrice;
            }
            return;
        }
        // 3. 이모티콘 할인율 선택
        for(int i = 0; i < discount.length; i++) {
            path[depth] = discount[i];
            dfs(depth + 1, path, users, emoticons);
        }
    }

    // 한 유저의 이모티콘 구매 금액 계산
    public int getPrice(int stPer, int[] path, int[] emoticons) {
        int price = 0;
        for(int i = 0; i < emoticons.length; i++) {
            if(path[i] < stPer) continue;
            price += emoticons[i] * (100 - path[i]) / 100;
        }
        return price;
    }
}