package programmers.level2;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 단체사진 찍기
 * [풀이시간] 35분
 * [한줄평] 범위가 크지 않아 완전탐색, DFS로 풀 수 있는 문제였다.
 * 1_v1. 완전탐색, DFS(성공)
 * [풀이] O(m! * n)은 최대 4 * 10^6 으로 완전탐색으로 풀 수 있다.
 * - m : 카카오프렌즈 수(8)
 * - n : 조건의 개수(최대 100)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/1835">문제</a>
 */
class Solution1835 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    char[] arr = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    Map<Character, Integer> map;
    boolean[] visited;
    int answer;

    public int solution(int n, String[] data) {
        answer = 0;
        map = new HashMap<>();
        visited = new boolean[8];

        dfs(0, data);
        return answer;
    }

    // 8개를 나열하는 순열
    public void dfs(int depth, String[] data) {
        if(depth == 8) {
            if(isPossible(data)) answer++;
            return;
        }
        for(int i = 0; i < 8; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            map.put(arr[i], depth);
            dfs(depth + 1, data);
            visited[i] = false;
        }
    }

    // n개의 조건을 모두 만족하는지 여부
    public boolean isPossible(String[] data) {
        for(String s : data) {
            char[] arr = s.toCharArray();
            // 본인과 상대방 사이에 위치한 프렌즈 수
            int cnt = Math.abs(map.get(arr[0]) - map.get(arr[2])) - 1;
            int standard = arr[4] - '0';    // 기준 간격(수)
            if(arr[3] == '=') {
                if(cnt != standard) return false;
            } else if(arr[3] == '<') {
                if(cnt >= standard) return false;
            } else {
                if(cnt <= standard) return false;
            }
        }
        return true;
    }
}