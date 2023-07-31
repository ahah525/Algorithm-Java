package programmers.level1;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [문제명] 개인정보 수집 유효기간
 * [풀이시간] 19분 / 19분 / 11분
 * [한줄평] 년월일을 일로 변환해서 푸는 것이 구현의 핵심이었다.
 * / .으로 분리할 때는 \\. 으로 써야하는 것을 주의해야하는 문제였다.
 * / 더이상 풀어볼 필요없는 쉬운 문제다.
 * 1_v1. 구현(성공)
 * 2_v1. 구현(성공)
 * 3_v1. 구현(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/150370">문제</a>
 */
class Solution150370 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1, 3_v1
    /**
     * @param today 오늘 날짜를 의미하는 문자열
     * @param terms 약관의 유효기간을 담은 1차원 문자열 배열
     * @param privacies 수집된 개인정보의 정보를 담은 1차원 문자열 배열
     * @return 파기해야 할 개인정보의 번호를 오름차순으로 1차원 정수 배열에 담아 return
     */
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 1. (약관 종류, 일로 변환한 유효기간) 초기화
        Map<String, Integer> map = new HashMap<>();
        for(String term : terms) {
            String[] t = term.split(" ");
            map.put(t[0], Integer.parseInt(t[1]) * 28);
        }
        // 2. 오늘 날짜를 일로 변환
        int now = getDay(today);
        // 3. 각 약관의 유효기간이 지났는지 검사
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < privacies.length; i++) {
            String[] p = privacies[i].split(" ");
            int day = getDay(p[0]);
            int term = map.get(p[1]);
            if(day + term <= now) {
                list.add(i + 1);
            }
        }
        // 4. 리스트를 배열로 변환
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    // 년월일을 일로 변환
    public int getDay(String date) {
        // \\. 으로 사용해야 . 기준으로 분리됨
        String[] arr = date.split("\\.");
        int year = Integer.parseInt(arr[0]) * 12 * 28;
        int month = Integer.parseInt(arr[1]) * 28;
        int day = Integer.parseInt(arr[2]);
        return year + month + day;
    }
}