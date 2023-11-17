package programmers.level3;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 다단계 칫솔 판매
 * [풀이시간] 52분(47분 + 5분) / 29분 / 24분 / 18분
 * [한줄평] Map 을 이용해서 푸는 구현 문제로 어렵지는 않았으나 반례를 찾지 못해 힌트를 보고 해결했다.
 * / 쉽게 풀 수 있는 구현 문제였다.
 * / DFS 로 풀 수 있는 쉬운 문제였지만 복습해도 좋을 문제다.
 * / DFS로 쉽게 풀 수 있는 문제였고 더 안풀어봐도 될 것 같다.
 * 1_v1. HashMap 2(실패-11~13 시간초과)
 * 1_v2. 구현(성공)
 * [풀이] 반복문
 * 2_v1. DFS(성공)
 * 3_v1. DFS(성공)
 * 4_v1. DFS(성공)
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

    // 2_v1
    int[] answer;
    Map<String, Integer> map;
    public int[] solution2(String[] enroll, String[] referral, String[] seller, int[] amount) {
        answer = new int[enroll.length];
        // (이름, 번호)
        map = new HashMap<>();
        for(int i = 0; i < enroll.length; i++) {
            map.put(enroll[i], i);
        }
        //
        for(int i = 0; i < seller.length; i++) {
            dfs(seller[i], amount[i] * 100, referral);
        }
        return answer;
    }

    public void dfs(String child, int amount, String[] referral) {
        if(amount == 0 || child.equals("-")) return;
        int parentAmount = amount / 10; // 부모 정산 금액
        // 자식 정산
        int c = map.get(child);
        answer[c] += amount - parentAmount;
        // 부모 재귀 호출
        dfs(referral[c], parentAmount, referral);
    }

    // 3_v1
//    Map<String, Integer> map;
//    int[] answer;
//    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
//        int n = enroll.length;
//        //
//        map = new HashMap<>();
//        for(int i = 0; i < n; i++) {
//            map.put(enroll[i], i);
//        }
//        //
//        answer = new int[n];
//        for(int i = 0; i < seller.length; i++) {
//            int sellerIdx = map.get(seller[i]);
//            int profit = 100 * amount[i];
//            dfs(0, sellerIdx, profit, referral);
//        }
//
//        return answer;
//    }
//
//    // child 의 부모 인덱스 조회
//    public int getParentIdx(int childIdx, String[] referral) {
//        String parent = referral[childIdx];
//        if(parent.equals("-")) return -1;
//        return map.get(parent);
//    }
//
//    // child 이익 정산
//    public void dfs(int depth, int childIdx, int profit, String[] referral) {
//        int parentIdx = getParentIdx(childIdx, referral);
//        int profitP = profit / 10;
//        int profitC = profit - profitP;
//        // 본인 이익 정산
//        answer[childIdx] += profitC;
//        // 부모 이익 정산
//        if(parentIdx == -1 || profitP == 0) return;
//        dfs(depth + 1, parentIdx, profitP, referral);
//    }

    // 4_v1
//    Map<String, String> parentMap;
//    Map<String, Integer> resultMap;
//    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
//        int[] answer = new int[enroll.length];
//        parentMap = new HashMap<>();
//        resultMap = new HashMap<>();
//        for(int i = 0; i < enroll.length; i++) {
//            parentMap.put(enroll[i], referral[i]);
//            resultMap.put(enroll[i], 0);
//        }
//        //
//        for(int i = 0; i < seller.length; i++) {
//            dfs(seller[i], amount[i] * 100);
//        }
//        //
//        for(int i = 0; i < enroll.length; i++) {
//            answer[i] = resultMap.get(enroll[i]);
//        }
//        return answer;
//    }
//
//    public void dfs(String childName, int profit) {
//        if(profit == 0 || childName.equals("-")) return;
//        int pp = profit / 10;
//        int cp = profit - pp;
//        // 본인 정산
//        resultMap.put(childName, resultMap.get(childName) + cp);
//        // 부모 정산
//        String parentName = parentMap.get(childName);
//        dfs(parentName, pp);
//    }
}