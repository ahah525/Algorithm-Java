package programmers.level1;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * [문제명] 신고 결과 받기
 * [풀이시간] 18분
 * [한줄평] 해시로 구현하면 쉽게 풀 수 있는 문제였다.
 * 1_v1. HashMap(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/92334">문제</a>
 */
class Solution92334 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    /**
     * @param idList 이용자의 ID가 담긴 문자열 배열
     * @param report 각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열
     * @param k 정지 기준이 되는 신고 횟수
     * @return 각 유저별로 처리 결과 메일을 받은 횟수
     */
    public int[] solution(String[] idList, String[] report, int k) {
        // 1. (id, 번호) 초기화
        Map<String, Integer> idMap = new HashMap<>();
        for(int i = 0; i < idList.length; i++) {
            idMap.put(idList[i], i);
        }
        // 2. (신고 당한 유저 id, 신고한 유저 id의 집합) 초기화
        Map<String, Set<String>> map = new HashMap<>();
        for(String r : report) {
            String[] ids = r.split(" ");
            if(!map.containsKey(ids[1]))
                map.put(ids[1], new HashSet<>());
            map.get(ids[1]).add(ids[0]);
        }
        // 3. 본인이 신고한 회원 중 신고 처리된 회원수 계산
        int[] answer = new int[idList.length];
        for(String key : map.keySet()) {
            // k번 이하 신고된 id는 패스
            if(map.get(key).size() < k) continue;
            for(String id : map.get(key)) {
                answer[idMap.get(id)]++;
            }
        }
        return answer;
    }
}