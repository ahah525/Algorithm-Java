package programmers.level2;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 할인 행사
 * [풀이시간] / 28분(23분 + 5분) / 20분
 * [한줄평] 쉬운 구현 문제였다. map 2개를 사용해 풀었지만 다른 풀이 방법도 있을 것 같아서 실행 결과를 비교해보고 싶다.
 * /
 * / 쉽게 풀 수 있는 문제였다.
 * 1_v1. 원하는 (제품명, 개수), 10일간 할인하는 (제품명, 개수) 를 위한 map 2개 사용(성공)
 * -  편의상 원하는 (제품명, 개수)를 저장하기 위해 map 을 사용했지만 굳이 따로 정보를 저장할 필요는 없을 것 같다.
 * 2_v1. (실패 - 1~11 실패)
 * 2_v2. HashMap, 슬라이딩 윈도우(성공)
 * 3_v1. HashMap, 슬라이딩 윈도우(성공) -> 빠름
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/131127">문제</a>
 */
class Solution131127 {
    private static int n;   // 원하는 제품 종류 수
    private static Map<String, Integer> wants = new HashMap<>();        // 원하는 (제품명, 개수)
    private static Map<String, Integer> discounts = new HashMap<>();    // 10 일간 할인하는 (제품명, 개수)

    public static void main(String[] args) {
        // 3
        String[] want1 = {"banana", "apple", "rice", "pork", "pot"};
        int[] number1 = {3, 2, 2, 2, 1};
        String[] discount1 = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
        System.out.println(solution(want1, number1, discount1));

        // 0
        String[] want2 = {"apple"};
        int[] number2 = {10};
        String[] discount2 = {"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"};
        System.out.println(solution(want2, number2, discount2));
    }

    // 1_v1
    /**
     * @param want 정현이가 원하는 제품을 나타내는 문자열 배열
     * @param number 정현이가 원하는 제품의 수량을 나타내는 정수 배열
     * @param discount XYZ 마트에서 할인하는 제품을 나타내는 문자열 배열
     * @return 회원등록시 정현이가 원하는 제품을 모두 할인 받을 수 있는 회원등록 날짜의 총 일수를 return(가능한 날이 없으면 0을 return)
     */
    public static int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        n = want.length;
        // 1. 원하는 (제품명, 개수) map 에 저장
        for(int i = 0; i < n; i++) {
            wants.put(want[i], number[i]);
        }
        int days = discount.length; // 총 할인 기간
        // 2. 10개 먼저 넣고 가능 여부 검사
        for(int i = 0; i < 10; i++) {
            discounts.put(discount[i], discounts.getOrDefault(discount[i], 0) + 1);
        }
        if(isPossible()) answer++;
        // 3. 11번째 부터는 맨 앞에 1개 빼고, 맨 뒤에 1개 넣은 후에 가능 여부 검사
        for(int i = 10; i < days; i++) {
            discounts.put(discount[i - 10], discounts.get(discount[i - 10]) - 1);
            discounts.put(discount[i], discounts.getOrDefault(discount[i], 0) + 1);
            if(isPossible()) answer++;
        }
        return answer;
    }

    // 원하는 제품을 모두 할인 받을 수 있는지 여부 검사
    public static boolean isPossible() {
        for(String name : wants.keySet()) {
            int n1 = wants.get(name);   // 원하는 목록에서 해당 제품의 개수
            int n2 = discounts.getOrDefault(name, 0);   // 할인 목록에서 해당 제품의 개수
            if(n1 > n2)
                return false;
        }
        return true;
    }

    // 2_v1
    public int solution2(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < 10; i++) {
            map.put(discount[i], map.getOrDefault(discount[i], 0) + 1);
        }
        int n = discount.length - 10;
        for(int i = 0; i <= n; i++) {
            // 모두 할인받을 수 있는지 검사
            if(isPossible(want, number, map)) answer++;
            // 시작 포인터 오른쪽으로 옮기기
            if(map.get(discount[i]) == 1) map.remove(discount[i]);
            else map.put(discount[i], map.get(discount[i]) - 1);
            // 끝 포인터 오른쪽으로 옮기기
            if(i + 10 < discount.length)
                map.put(discount[i + 10], map.getOrDefault(discount[i + 10], 0) + 1);
        }
        return answer;
    }

    public boolean isPossible(String[] want, int[] number, Map<String, Integer> map) {
        for(int i = 0; i < want.length; i++) {
            if(map.getOrDefault(want[i], 0) < number[i]) return false;
        }
        return true;
    }

    // 3_v1
    Map<String, Integer> map;
    public int solution3(String[] want, int[] number, String[] discount) {
        int answer = 0;
        // (제품 종류, 수량)
        map = new HashMap<>();
        for(int i = 0; i < 10; i++) {
            map.put(discount[i], map.getOrDefault(discount[i], 0) + 1);
        }
        // 첫번째 경우 무조건 검사 검사
        if(isPossible(want, number)) answer++;
        for(int i = 10; i < discount.length; i++) {
            map.put(discount[i], map.getOrDefault(discount[i], 0) + 1);
            map.put(discount[i - 10], map.get(discount[i - 10]) - 1);
            if(isPossible(want, number)) answer++;
        }
        return answer;
    }

    public boolean isPossible(String[] want, int[] number) {
        for(int i = 0; i < want.length; i++) {
            int cnt = map.getOrDefault(want[i], 0);
            if(cnt < number[i]) return false;
        }
        return true;
    }
}