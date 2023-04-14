package programmers.level3;


import java.util.*;

/**
 * [문제명] 불량 사용자
 * [풀이시간] 50분
 * [한줄평] 예전에 풀다가 포기한 적이 있었는데, 어렵긴했지만 혼자서 푸는데 성공했다.
 * 1_v1. DFS(성공)
 * [접근법] visited 배열을 사용해 사용 여부를 0, 1 로 나타내고 그 값을 문자열로 이어 붙여 비교
 * 1_v2. DFS(성공)
 * [접근법] 비트마스크
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/64064">문제</a>
 */
class Solution64064 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    Set<String> set;
    List<List<Integer>> list;

    /**
     * @param userId 이벤트 응모자 아이디 목록이 담긴 배열
     * @param bannedId 불량 사용자 아이디 목록이 담긴 배열
     * @return 당첨에서 제외되어야 할 제재 아이디 목록은 몇가지 경우의 수가 가능한 지 return
     */
    public int solution(String[] userId, String[] bannedId) {
        set = new HashSet<>();
        int n = userId.length; //
        int m = bannedId.length;//
        list = new ArrayList<>();
        for(String id : bannedId) {
            list.add(getBannedList(userId, id));
        }
        //
        char[] visited = new char[n];
        Arrays.fill(visited, '0');
        dfs(0, visited, m);
        return set.size();
    }

    public void dfs(int depth, char[] visited, int m) {
        if(depth == m) {
            StringBuilder sb = new StringBuilder();
            for(char c : visited) {
                sb.append(c);
            }
            set.add(sb.toString());
            return;
        }
        for(int i : list.get(depth)) {
            if(visited[i] == '0') {
                visited[i] = '1';
                dfs(depth + 1, visited, m);
                visited[i] = '0';
            }
        }
    }

    // 응모자 아이디 목록에서 불량 사용자 아이디 형식과 같은 아이디의 인덱스 조회
    public List<Integer> getBannedList(String[] userIds, String bannedId) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < userIds.length; i++) {
            String userId = userIds[i];
            if(userId.length() != bannedId.length()) continue;
            boolean isBanned = true;
            for(int j = 0; j < bannedId.length(); j++) {
                if(bannedId.charAt(j) == '*') continue;
                if(userId.charAt(j) != bannedId.charAt(j)) {
                    isBanned = false;
                    break;
                }
            }
            if(isBanned) list.add(i);
        }
        return list;
    }

    // 2_v1
    Set<Integer> sets;
    public int solution2(String[] userId, String[] bannedId) {
        sets = new HashSet<>();
        int n = userId.length; //
        int m = bannedId.length;//
        list = new ArrayList<>();
        for(String id : bannedId) {
            list.add(getBannedList(userId, id));
        }
        //
        dfs(0, 0, new boolean[n], m);
        return sets.size();
    }

    public void dfs(int depth, int res, boolean[] visited, int m) {
        if(depth == m) {
            sets.add(res);
            return;
        }
        for(int i : list.get(depth)) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, (res | (1 << i)), visited, m);
                visited[i] = false;
            }
        }
    }
}