package programmers.level2;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * [문제명] 후보키
 * [풀이시간] (47분)
 * [한줄평]
 * 1_v1. (실패 - 18~20,22,25 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/">문제</a>
 */
class Solution42890 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    int answer;
    // boolean[] visited;
    Set<String> keys = new HashSet<>(); // 후보키
    public int solution(String[][] relation) {
        answer = 0;
        int r = relation.length;    // 로우 개수
        int c = relation[0].length; // 컬럼 개수
        // visited = new boolean[c];
        List<Integer> list = new ArrayList<>();
        // 유니크한 컬럼, 유니크하지 않은 컬럼 구하기
        for(int j = 0; j < c; j++) {
            Set<String> set = new HashSet<>();
            boolean isUnique = true;
            for(int i = 0; i < r; i++) {
                if(set.contains(relation[i][j])) {
                    isUnique = false;
                    break;
                }
                set.add(relation[i][j]);
            }
            if(isUnique) {
                keys.add(Integer.toString(j));
                answer++;
            }
            else list.add(j);
        }
        // System.out.println(list);
        // boolean[] visited = new boolean[c];
        for(int i = 2; i <= list.size(); i++) {
            comb(0, -1, "", list, c, i, relation);
        }
        return answer;
    }

    // n 개에서 r 개 뽑기
    public void comb(int depth, int prev, String path, List<Integer> list, int n, int r, String[][] relation) {
        if(depth == r) {
            // 현재 키가 후보키를 포함하고 있으면 최소성을 만족하지 못하므로 종료
            for(String key : keys) {
                if(path.contains(key)) return;
            }

            Set<String> set = new HashSet<>();
            for(int i = 0; i < relation.length; i++) {
                StringBuilder sb = new StringBuilder();
                for(char col : path.toCharArray()) {
                    sb.append(relation[i][col - '0']);
                }
                // for(int j = 0; j < n; j++) {
                //     if(visited[j]) sb.append(relation[i][j]);
                // }
                if(set.contains(sb.toString())) return;
                // System.out.println(sb);
                set.add(sb.toString());
            }
            keys.add(path);
            answer++;
            // System.out.println();
            return;
        }
        for(int i = prev + 1; i < n; i++) {
            if(list.contains(i)) {
                // visited[i] = true;
                comb(depth + 1, i, path + i, list, n, r, relation);
                // visited[i] = false;
            }
        }
    }
}