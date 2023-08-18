package programmers.level2;


import java.util.*;

/**
 * [문제명] 순위 검색
 * [풀이시간] 2시간 / 45분(42분+3분)
 * [한줄평] 쉬울 거라 생각했는데 생각보다 복잡했고 감을 못잡아서 결국 문제 접근에 대한 힌트를 보고 풀었다.
 * 1_v1. (실패 - 효율성 3,4 시간초과)
 * [풀이] 리스트에서 특정 점수 이상인 사람수 구할 때 => 완전탐색 수행
 * 1_v2. DFS, 이분탐색(성공)
 * [풀이] 리스트에서 특정 점수 이상인 사람수 구할 때 => 이분탐색 수행
 * 2_v1. DFS, 이분탐색(실패 - 효율성 3,4 시간초과)
 * [해결] map의 value에 해당하는 점수 리스트를 먼저 정렬한다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/72412">문제</a>
 * @See <a href="https://record-developer.tistory.com/68">풀이 참고</a>
 */
class Solution72412 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2
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
        int cnt = 0;
        if(map.containsKey(key)) {
            // 5. 기준 점수 이상인 사람의 수 구하기
            List<Integer> scores = map.get(key);
            int start = 0;
            int end = scores.size() - 1;
            while(start <= end) {
                int mid = (start + end) / 2;
                if(scores.get(mid) >= score) {
                    end = mid - 1;
                    cnt = scores.size() - mid;
                } else {
                    start = mid + 1;
                }
            }
        }
        return cnt;
    }

    // 4개의 조합(2의 4제곱) 16개 경우의 수 구하기
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

    // 2_v1
    Map<String, List<Integer>> map;
    public int[] solution2(String[] info, String[] query) {
        //
        map = new HashMap<>();
        for(String s : info) {
            String[] arr = s.split(" ");
            int score = Integer.parseInt(arr[4]);
            dfs(0, "", score, arr);
        }
        //
        int[] answer = new int[query.length];
        for(int i = 0; i < query.length; i++) {
            //
            String[] q = query[i].split(" ");
            StringBuilder sb = new StringBuilder();
            sb.append(q[0]).append(q[2]).append(q[4]).append(q[6]);
            String path = sb.toString();
            int score = Integer.parseInt(q[7]);
            int cnt = 0;
            if(map.containsKey(path)) {
                List<Integer> list = map.get(path);
                Collections.sort(list);
                //
                int start = 0;
                int end = list.size() - 1;
                int idx = list.size();
                while(start <= end) {
                    int mid = (start + end) / 2;
                    if(list.get(mid) >= score) {
                        end = mid - 1;
                        idx = mid;
                    } else {
                        start = mid + 1;
                    }
                }
                cnt = list.size() - idx;
            }
            answer[i] = cnt;
        }

        return answer;
    }

    public void dfs(int depth, String path, int score, String[] arr) {
        if(depth == 4) {
            if(!map.containsKey(path)) map.put(path, new ArrayList<>());
            map.get(path).add(score);
            return;
        }
        //
        dfs(depth + 1, path + arr[depth], score, arr);
        dfs(depth + 1, path + "-", score, arr);
    }
}