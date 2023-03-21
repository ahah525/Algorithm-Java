package programmers.level3;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 다단계 칫솔 판매
 * [풀이시간] (47분)
 * [한줄평]
 * 1_v1. HashMap 2(실패-11~13 시간초과)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/77486">문제</a>
 */
class Solution77486 {
    public static void main(String[] args) {
        //
        System.out.println(Arrays.toString(
                solution(
                        new String[] {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                        new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                        new String[] {"young", "john", "tod", "emily", "mary"},
                        new int[] {12, 4, 2, 5, 10}
                ))
        );
    }

    // 1_v1
    /**
     * @param enroll 각 판매원의 이름을 담은 배열
     * @param referral 각 판매원을 다단계 조직에 참여시킨 다른 판매원의 이름을 담은 배열
     * @param seller 판매량 집계 데이터의 판매원 이름을 나열한 배열
     * @param amount 판매량 집계 데이터의 판매 수량을 나열한 배열
     * @return 각 판매원이 득한 이익금을 나열한 배열
     */
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        // 1. 본인
        Map<String, String> recommenders = new HashMap();
        Map<String, Integer> prices = new HashMap<>();
        for(int i = 0; i < enroll.length; i++) {
            recommenders.put(enroll[i], referral[i]);
            prices.put(enroll[i], 0);
        }

        // 2. seller[i] 이익금 배분
        for(int i = 0; i < seller.length; i++) {
            int price = amount[i] * 100;
            String me = seller[i];
            while(recommenders.containsKey(me)) {
                // 3. 추천인 배분 금액 계산(추천인이 있고 최소 1원을 받을 수 있을 때만)
                int rp = 0; // 추천인 배분 금액
                if(price >= 10) rp = (int) (price * 0.1);
                // 4. 본인 배분 금액 계산 및 반영
                int mp = price - rp;
                prices.put(me, prices.get(me) + mp);
                // 5.
                me = recommenders.get(me);
                price = rp;
            }
//            System.out.println(prices);
        }
        for(int j = 0; j < enroll.length; j++) {
            answer[j] = prices.get(enroll[j]);
        }
        return answer;
    }
}