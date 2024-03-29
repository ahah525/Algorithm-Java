package programmers.level2;


import java.util.*;

/**
 * [문제명] 순위 검색
 * [풀이시간] 2시간 / 45분(42분+3분) / 40분
 * [한줄평] 쉬울 거라 생각했는데 생각보다 복잡했고 감을 못잡아서 결국 문제 접근에 대한 힌트를 보고 풀었다.
 * / query의 크기가 최대 100,000이고 info의 최대 크기는 50,000이기 때문에 완전탐색으로 풀면 O(5*10^9)으로 시간초과가 나는게 뻔해서 이전에 풀었던 방식을 떠올려서 빨리 풀 수 있었다.
 * / 이전에 풀어봤던 기억이 있어서 접근법을 빨리 떠올렸지만 다시 풀어봐도 좋을 문제다.
 * 1_v1. (실패 - 효율성 3,4 시간초과)
 * [풀이] 리스트에서 특정 점수 이상인 사람수 구할 때 => 완전탐색 수행
 * 1_v2. DFS, 이분탐색(성공)
 * [풀이] 리스트에서 특정 점수 이상인 사람수 구할 때 => 이분탐색 수행
 * 2_v1. DFS, 이분탐색(실패 - 효율성 3,4 시간초과)
 * [해결] map의 value에 해당하는 점수 리스트를 먼저 정렬한다.
 * 2_v2. DFS, 이분탐색(성공)
 * [풀이] 1_v2 동일
 * 3_v1. DFS, 이분탐색(성공)
 * [풀이] 1_v2 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/72412">문제</a>
 * @See <a href="https://record-developer.tistory.com/68">풀이 참고</a>
 */
class Solution72412 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2, 2_v2, 3_v1
    Map<String, List<Integer>> map; // (조합을 이어붙인 문자열, 조합에 해당하는 점수 리스트)
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        map = new HashMap<>();
        // 1. 사람의 정보와 맞는 쿼리 경우의 수 모두 구하기
        for(String s : info) {
            String[] arr = s.split(" ");
            dfs(0, "", arr, Integer.parseInt(arr[4]));
        }
        // 2. 키에 대한 점수 리스트 오름차순 정렬
        for(String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        //
        int idx = 0;
        for(String q : query) {
            // 3. 개발언어+직군+경력+소울푸드 이어붙인 문자열(키) 만들기
            String[] arr = q.replace(" and ", " ").split(" ");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 4; i++) {
                sb.append(arr[i]);
            }
            // 4. 해당 쿼리 조건에 해당하는 사람수 계산, 저장
            int cnt = getCount(Integer.parseInt(arr[4]), sb.toString());
            answer[idx++] = cnt;
        }
        return answer;
    }

    /**
     * @param score 기준 점수
     * @param key 쿼리 문자열
     * @return 쿼리 조건을 만족하는 리스트에서 score 점수 이상인 사람수 구하기
     */
    public int getCount(int score, String key) {
        if(!map.containsKey(key)) return 0;
        // 5. 기준 점수 이상인 사람의 수 구하기
        List<Integer> scores = map.get(key);
        int n = scores.size();
        int idx = n;    // score 점수 이상인 사람 중 가장 점수가 낮은 사람의 인덱스 번호
        int start = 0;
        int end = n - 1;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(scores.get(mid) >= score) {
                // 현재점수 >= 기준점수, 왼쪽 탐색
                end = mid - 1;
                idx = mid;
            } else {
                // 현재점수 < 기준점수, 오른쪽 탐색
                start = mid + 1;
            }
        }
        return n - idx;
    }

    // 4개의 조합(2의 4제곱) 16개 경우의 수 구하기
    public void dfs(int depth, String path, String[] info, int score) {
        if(depth == 4) {
            if(!map.containsKey(path)) map.put(path, new ArrayList<>());
            map.get(path).add(score);
            return;
        }
        dfs(depth + 1, path + info[depth], info, score);
        dfs(depth + 1, path + "-", info, score);
    }
}