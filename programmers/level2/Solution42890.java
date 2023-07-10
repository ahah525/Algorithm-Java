package programmers.level2;


import java.util.*;

/**
 * [문제명] 후보키
 * [풀이시간] 1시간(47분 + 13분) / (50분) / 1시간 12분
 * [한줄평] 그대로 구현만 하면되는 문제였는데, 반례를 찾지 못해서 어려웠던 문제였다.
 * /
 * / 처음에는 컬럼의 경우의 수를 어떤식으로 저장할지 고민하다가 선택한 컬럼번호를 문자열로 이어붙이는 방향으로 풀었다.
 * 1_v1. 완전탐색, DFS(실패 - 18~20,22,25 실패)
 * 1_v2. 완전탐색, DFS(성공)
 * [해결] 후보키A가 후보키B에 있는 모든 문자를 포함하고 있는지 여부를 검사할 때 contains() 를 사용하면 안된다!
 * [반례] 컬럼이 0,1,2 있다고 가정할 때 "02"가 후보키이므로 "012"는 최소성을 위배해 후보키가 될 수 없지만 "012".contains("02") 는 false 이므로 후보키로 등록되는 문제 발생
 * - "01" : 후보키X
 * - "02" : 후보키O
 * - "12" : 후보키X
 * -> "012" : 후보키O(문제)
 * 2_v1. 완전탐색, DFS(실패 - 18~20,22,25,28 실패)
 * 3_v1. 완전탐색, DFS(성공)
 * [풀이] 유일성을 만족하면 list에 path(컬럼을 i개 선택한 경우의 수)를 추가한다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42890">문제</a>
 * @See <a href="https://school.programmers.co.kr/questions/7476">반례</a>
 * @See <a href="https://velog.io/@yanghl98/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%9B%84%EB%B3%B4%ED%82%A4-JAVA%EC%9E%90%EB%B0%94-2019-%EC%B9%B4%EC%B9%B4%EC%98%A4-%EA%B8%B0%EC%B6%9C">반례</a>
 */
class Solution42890 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2
    int answer;
    boolean[] visited;  // 컬럼 선택 여부
    Set<String> keys;   // 후보키 집합

    /**
     * @param relation 릴레이션을 나타내는 문자열 배열
     * @return 이 릴레이션에서 후보 키의 개수
     */
    public int solution(String[][] relation) {
        int r = relation.length;    // 로우 개수
        int c = relation[0].length; // 컬럼 개수
        answer = 0;
        keys = new HashSet<>();
        visited = new boolean[c];
        // 1. 유니크한 컬럼, 유니크하지 않은 컬럼 구분하기
        List<Integer> list = new ArrayList<>(); // 유니크하지 않은 컬럼 리스트
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
            // 2. j 번째 컬럼이 유니크하면 j 를 후보키에 넣고
            if(isUnique) {
                keys.add(Integer.toString(j));
                answer++;
            }
            // 3. j 번째 컬럼이 유니크하지 않으면 j 를 리스트에 넣기
            else list.add(j);
        }
        // 4. 유니크하지 않은 컬럼 리스트(n개)에서 2, 3, ..., n 개를 선택해 만든 조합중 후보키가 될 수 있는 것의 개수 세기
        for(int i = 2; i <= list.size(); i++) {
            comb(0, -1, "", list, c, i, relation);
        }
        return answer;
    }

    /**
     * n 개에서 r 개를 선택해 만든 조합중 후보키가 될 수 있는 것의 개수 세기
     * @param depth 깊이(선택한 컬럼의 개수)
     * @param prev 이전 컬럼
     * @param path 컬럼 조합(키)
     * @param list 유니크하지 않은 컬럼 리스트
     * @param n 유니크하지 않은 컬럼 개수
     * @param r 선택해야하는 컬럼 개수
     * @param relation 릴레이션을 나타내는 문자열 배열
     */
    public void comb(int depth, int prev, String path, List<Integer> list, int n, int r, String[][] relation) {
        // 1. r 개의 컬럼을 모두 선택했으면
        if(depth == r) {
            // 2. 최소성 검증
            for(String key : keys) {
                boolean flag = true;
                for(char c : key.toCharArray()) {
                    if(path.indexOf(c) == -1) {
                        flag = false;
                        break;
                    }
                }
                // 현재 키가 어떤 후보키의 모든 문자를 포함하고 있으면 최소성을 만족하지 못하므로 리턴
                if(flag) return;
            }
            // 3. 유일성 검증
            Set<String> set = new HashSet<>();  // 현재 키로 만든 튜플 집합
            for(int i = 0; i < relation.length; i++) {
                StringBuilder sb = new StringBuilder();
                // for(char col : path.toCharArray()) {
                //     sb.append(relation[i][col - '0']);
                // }
                for(int j = 0; j < n; j++) {
                    if(visited[j]) sb.append(relation[i][j]);
                }
                // 4. 현재 키로 튜플을 유일하게 식별할 수 없으면 리턴
                if(set.contains(sb.toString())) return;
                set.add(sb.toString());
            }
            // 5. 현재 키로 튜플을 유일하게 식별할 수 있으면 후보키에 추가, 카운트
            keys.add(path);
            answer++;
            return;
        }
        // 6. 이전에 선택한 다음 컬럼부터 선택하기
        for(int i = prev + 1; i < n; i++) {
            if(list.contains(i)) {
                visited[i] = true;
                comb(depth + 1, i, path + i, list, n, r, relation);
                visited[i] = false;
            }
        }
    }

    // 2_v2
    int col;
    List<String> list;
    public int solution2(String[][] relation) {
        col = relation[0].length; // 컬럼 개수
        list = new ArrayList<>();
        visited = new boolean[col];
        // 1. 유일성을 만족하는 컬럼 조합 구하기
        dfs(0, -1, "", relation);
        // 2. 길이 내림차순 정렬
        Collections.sort(list, (o1, o2) -> {
            return o2.length() - o1.length();
        });
        // System.out.println(list);
        // 3. 최소성을 만족하지 않는 컬럼 조합 삭제
        int i = 0;
        while(i < list.size()) {
            int j = i + 1;
            boolean remove = false;
            while(j < list.size()) {
                if(!isMinimal(list.get(j), list.get(i))) {
                    list.remove(i);
                    remove = true;
                    break;
                }
                j++;
            }
            if(!remove) i++;
        }
        // System.out.println(list);
        return list.size();
    }

    //
    public void dfs(int depth, int prev, String path, String[][] relation) {
        // System.out.println(path);
        if(depth >= 1 && isUnique(relation)) {
            list.add(path);
            return;
        }
        if(depth == col) {
            return;
        }
        for(int i = prev + 1; i < col; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, i, path + i, relation);
                visited[i] = false;
            }
        }
    }

    // 최소성을 만족하는지
    public boolean isMinimal(String a, String b) {
        if(a.length() == b.length()) return true;
        // a < b
        int cnt = 0;
        for(char c : a.toCharArray()) {
            if(b.indexOf(c) != -1) cnt++;
        }
        if(cnt == a.length()) return false;
        return true;
    }

    // 유일성을 만족하는지
    public boolean isUnique(String[][] relation) {
        Set<String> set = new HashSet<>();
        for(String[] row : relation) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < row.length; i++) {
                if(visited[i]) {
                    sb.append(row[i]);
                }
            }
            if(set.contains(sb.toString())) return false;
            set.add(sb.toString());
        }
        return true;
    }

    // 3_v1
//    int col, row;
//    boolean[] visited;
//    List<String> list;
//    public int solution(String[][] relation) {
//        col = relation[0].length;
//        row = relation.length;
//        visited = new boolean[col]; // 컬럼 선택여부
//        list = new ArrayList<>();   // 컬럼 조합 리스트
//        // 1.
//        dfs(0, -1, "", relation);
//        // 2. lists[i] = 길이가 i인(컬럼을 i개 선택한) 경우의 수 리스트
//        List<String>[] lists = new ArrayList[col + 1];
//        for(int i = 0; i <= col; i++) {
//            lists[i] = new ArrayList<>();
//        }
//        for(String s : list) {
//            lists[s.length()].add(s);
//        }
//        //
//        int answer = 0;
//        int len = 1;
//        while(len < col) {
//            // 3. 컬럼을 len개 선택한 경우의 수 더하기
//            answer += lists[len].size();
//            for(String a : lists[len]) {
//                for(int i = len + 1; i <= col; i++) {
//                    int j = 0;
//                    while(j < lists[i].size()) {
//                        String b = lists[i].get(j);
//                        if(!isMinimal(a, b)) {
//                            // 4. 컬럼을 j개 선택한 경우의 수 원소들이 컬럼을 len개 선택한 경우의 수의 원소들을 모두 포함하고 있다면, 해당 경우의 수 삭제
//                            lists[i].remove(b);
//                            continue;
//                        }
//                        // 5. 다음 경우의 수 검사
//                        j++;
//                    }
//                }
//            }
//            len++;
//        }
//        answer += lists[len].size();
//
//        return answer;
//    }
//
//    // b의 원소들에 a의 모든 원소가 포함되는지 여부
//    public boolean isMinimal(String a, String b) {
//        String[] arr = a.split("");
//        for(String c : arr) {
//            if(!b.contains(c)) return true;
//        }
//        return false;
//    }
//
//    public void dfs(int depth, int prev, String path, String[][] relation) {
//        //
//        if(depth != 0 && isUnique(relation)) {
//            list.add(path);
//        }
//        if(depth == col) return;
//        for(int i = prev + 1; i < col; i++) {
//            visited[i] = true;
//            dfs(depth + 1, i, path + i, relation);
//            visited[i] = false;
//        }
//    }
//
//    public boolean isUnique(String[][] relation) {
//        Set<String> set = new HashSet<>();
//        for(int r = 0; r < row; r++) {
//            StringBuilder sb = new StringBuilder();
//            for(int c = 0; c < col; c++) {
//                if(!visited[c]) continue;
//                sb.append(relation[r][c]);
//            }
//            if(set.contains(sb.toString())) return false;
//            set.add(sb.toString());
//        }
//        return true;
//    }
}