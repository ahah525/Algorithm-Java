package programmers.level3;


import java.util.*;

/**
 * [문제명] 불량 사용자
 * [풀이시간] 50분 / 19분 / 14분 / 14분
 * [한줄평] 예전에 풀다가 포기한 적이 있었는데, 어렵긴했지만 혼자서 푸는데 성공했다.
 * / 비트마스크로 풀어야겠다는 것을 바로 알고 푸니 쉽게 풀 수 있었다.
 * / 몇 비트마스크로
 * / 몇 번째 단어를 선택했는지 기록하기 위해 비트마스크를 사용해야겠다는 생각이 들어서 쉽게 풀 수 있었다.
 * 1_v1. DFS(성공)
 * [풀이] visited 배열을 사용해 사용 여부를 0, 1 로 나타내고 그 값을 문자열로 이어 붙여 비교
 * 1_v2. DFS(성공)
 * [풀이] 비트마스크
 * 2_v1. DFS, 완전탐색(성공) -> 빠름
 * [풀이] 몇 번째 인덱스를 선택했는지 비트마스크로 기록한다.(0: 선택X, 1: 선택O)
 * 3_v1. DFS, 완전탐색(성공)
 * [풀이] 2_v1 동일
 * 4_v1. DFS, 완전탐색(성공)
 * [풀이] 2_v1 동일
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

    // 1_v2
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


    // 2_v1, 3_v1, 4_v1
    public int solution3(String[] userId, String[] bannedId) {
        sets = new HashSet<>();
        permu(0, 0, new boolean[userId.length], userId, bannedId);
        return set.size();
    }

    // userId 목록에서 bannedId 목록의 개수만큼 뽑기
    public void permu(int depth, int path, boolean[] visited, String[] userId, String[] bannedId) {
        // 다 뽑았으면 set에 넣기
        if(depth == bannedId.length) {
            sets.add(path);
            return;
        }
        //
        for(int i = 0; i < userId.length; i++) {
            if(!visited[i] && isBannedId(userId[i], bannedId[depth])) {
                visited[i] = true;
                permu(depth + 1, path | (1 << i), visited, userId, bannedId);
                visited[i] = false;
            }
        }
    }

    // 해당 userId가 bannedId 형식에 맞는지 검사
    public boolean isBannedId(String userId, String bannedId) {
        if(userId.length() != bannedId.length()) return false;
        for(int i = 0; i < userId.length(); i++) {
            if(bannedId.charAt(i) == '*') continue;
            if(userId.charAt(i) != bannedId.charAt(i)) return false;
        }
        return true;
    }
}