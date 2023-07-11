package programmers.level2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [문제명] 순위 검색
 * [풀이시간] 2시간
 * [한줄평]
 * 1_v1. (실패 - 효율성 3,4 시간초과)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/72412">문제</a>
 */
class Solution72412 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    Map<String, List<Integer>> map; // (조합을 이어붙인 문자열, 조합에 해당하는 점수 리스트)
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        map = new HashMap<>();
        // 1. 사람의 정보와 맞는 쿼리 경우의 수 모두 구하기
        for(String s : info) {
            String[] arr = s.split(" ");
            dfs(0, "", arr, Integer.parseInt(arr[4]));
        }
        //
        int idx = 0;
        for(String q : query) {
            int cnt = 0;
            // 2. 개발언어+직군+경력+소울푸드 이어붙인 문자열 만들기
            String[] arr = q.replace(" and ", " ").split(" ");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 4; i++) {
                sb.append(arr[i]);
            }
            String key = sb.toString();
            // 3. 해당 쿼리로 검색한 결과가 있으면
            if(map.containsKey(key)) {
                // 4. 기준 점수 이상인 사람의 수 구하기
                int score = Integer.parseInt(arr[4]);
                List<Integer> list = map.get(key);
                for(int n : list) {
                    if(score <= n) cnt++;
                }
            }
            // 5. 쿼리 조건에 해당하는 사람수 저장
            answer[idx++] = cnt;
        }
        return answer;
    }

    public void dfs(int depth, String path, String[] info, int score) {
        if(depth == 4) {
            if(!map.containsKey(path)) {
                map.put(path, new ArrayList<>());
            }
            map.get(path).add(score);
            return;
        }
        dfs(depth + 1, path + info[depth], info, score);
        dfs(depth + 1, path + "-", info, score);
    }
}