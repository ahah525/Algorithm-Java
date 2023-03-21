package programmers.level3;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 다단계 칫솔 판매
 * [풀이시간] 52분(47분 + 5분)
 * [한줄평] Map 을 이용해서 푸는 구현 문제로 어렵지는 않았으나 반례를 찾지 못해 힌트를 보고 해결했다.
 * 1_v1. HashMap 2(실패-11~13 시간초과)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/77486">문제</a>
 * @See <a href="https://school.programmers.co.kr/questions/29442">반례</a>
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

    // 1_v2
    /**
     * @param enroll 각 판매원의 이름을 담은 배열
     * @param referral 각 판매원을 다단계 조직에 참여시킨 다른 판매원의 이름을 담은 배열
     * @param seller 판매량 집계 데이터의 판매원 이름을 나열한 배열
     * @param amount 판매량 집계 데이터의 판매 수량을 나열한 배열
     * @return 각 판매원이 득한 이익금을 나열한 배열
     */
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        // 1. 본인의 추천인, 이익금 map 초기화
        Map<String, String> recommenders = new HashMap();   // (본인명, 추천인명)
        Map<String, Integer> prices = new HashMap<>();  // (본인명, 이익금)
        for(int i = 0; i < enroll.length; i++) {
            recommenders.put(enroll[i], referral[i]);
            prices.put(enroll[i], 0);
        }
        // 2. 각 판매원의 이익금 배분
        for(int i = 0; i < seller.length; i++) {
            String me = seller[i];  // 본인명
            int price = amount[i] * 100;    // 본인이 획득한 이익금
            while(true) {
                // 3. 본인이 center(민호)이거나 이익금이 0원이면 종료
                if(me.equals("-") || price == 0) break;
                // 4. 추천인에게 배분할 이익금 금액 계산
                int rp = (int) (price * 0.1);
                // 5. 본인에게 배분할 이익금 계산 및 반영
                int mp = price - rp;
                prices.put(me, prices.get(me) + mp);
                // 6. 추천인을 본인으로 대입
                me = recommenders.get(me);
                price = rp;
            }
        }
        // 7. 각 판매원의 이익금을 1차원 배열로 리턴
        for(int j = 0; j < enroll.length; j++)
            answer[j] = prices.get(enroll[j]);
        return answer;
    }
}