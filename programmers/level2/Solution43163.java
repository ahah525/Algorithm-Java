package programmers.level2;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [문제명] 단어 변환
 * [풀이시간] 30분 / 30분 / 20분 / 25분
 * [한줄평] DFS로 풀 수 있는 간단한 문제였다.
 * / 최단 경로 문제로 생각하고 BFS 로 풀었다. 이론상으로는 2번이 더 효율적이지만 실제 테스트 케이스에서는 1번이 빨랐다.
 * / 아주 기초적인 BFS 문제였다.
 * / 최단거리를 구하는 기초 BFS 문제였고 다시 복습해봐도 좋을 문제다.
 * 1_v1. DFS(성공)
 * [풀이] 최소 변환 횟수 = target 을 만들 수 있는 모든 경우의 수 중 최소 횟수 -> 모든 경우의 수를 구해봐야 앎
 * 2_v1. BFS(성공)
 * [풀이] 최소 변환 횟수 = 최초로 target 에 도달했을 때 횟수 -> 최초로 찾은 것이 정답
 * 3_v1. BFS(성공) -> 빠름
 * 4_v1. BFS(성공)
 * [풀이] 2_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43163">문제</a>
 * @See <a href="https://real-012.tistory.com/202">DFS BFS 비교</a>
 */
class Solution43163 {
    public static void main(String[] args) {
        // 4
        System.out.println(solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"}));

        // 0
        System.out.println(solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"}));
    }

    // 1_v1
    static int min; // 최소 과정수

    /**
     * @param begin 시작 단어
     * @param target 목표 단어
     * @param words 단어의 집합
     * @return 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지(변환할 수 없는 경우에는 0)
     */
    public static int solution(String begin, String target, String[] words) {
        int n = words.length;
        min = n + 1;    //
        permu(0, begin, new boolean[n], n, target, words);
        if(min == n + 1)
            return 0;
        return min;
    }

    /**
     * 순열
     * @param depth 지금까지 선택한 단어개수
     * @param prev 직전에 선택한 단어
     * @param visited 단어 선택 여부
     * @param n 단어 개수
     * @param target 목표 단어
     * @param words 단어의 집합
     */
    public static void permu(int depth, String prev, boolean[] visited, int n, String target, String[] words) {
        // n개를 모두 선택헀으면 종료
        if(depth == n) {
            return;
        }
        // 이전 단어가 목표 단어와 같다면 최솟값 업데이트
        if(prev.equals(target)) {
            min = Math.min(min, depth);
            return;
        }
        // 아직 선택하지 않고 변환될 수 있는 단어에 대해 재귀
        for(int i = 0; i < n; i++) {
            if(!visited[i] && canChange(prev, words[i])) {
                visited[i] = true;
                permu(depth + 1, words[i], visited, n, target, words);
                visited[i] = false;
            }
        }
    }

    // s1 -> s2 로 변환가능한지 체크
    public static boolean canChange(String s1, String s2) {
        if(s1.equals(s2)) return false;
        int diff = 0;   // 다른 문자의 개수
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
            // 1. 다른 문자가 2개 이상이면 변환X
            if(diff >= 2)
                return false;
        }
        // 2. 같은 문자로는 변환X
        return diff == 0  ? false : true;
    }

    // 2_v1, 4_v1
    class Word {
        String name;
        int cnt;

        Word(String name, int cnt) {
            this.name = name;
            this.cnt = cnt;
        }
    }

    public int solution2(String begin, String target, String[] words) {
        // 1. words 에 target 이 없으면 0 리턴(생략가능)
        boolean hasTarget = false;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(target)) {
                hasTarget = true;
                break;
            }
        }
        if(!hasTarget) return 0;
        // 2. 시작 단어 삽입, 방문처리
        Queue<Word> q = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        q.add(new Word(begin, 0));
        // 3. 큐가 빌 때까지 반복
        while(!q.isEmpty()) {
            Word w = q.poll();
            String name = w.name;
            int cnt = w.cnt;
            // 4. target 찾으면 cnt 리턴
            if(name.equals(target)) return cnt;
            for(int i = 0; i < words.length; i++) {
                // 5. 아직 선택하지 않았고 변환할 수 있는 단어이면 큐에 삽입, 방문처리
                if(!visited[i] && isChangeable(name, words[i])) {
                    q.add(new Word(words[i], cnt + 1));
                    visited[i] = true;
                }
            }
        }
        // target 만들 수 없는 경우 0 리턴
        return 0;
    }

    // a 를 b 로 변환할 수 있는지 여부
    public boolean isChangeable(String a, String b) {
        int cnt = 0;
        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) == b.charAt(i)) continue;
            cnt++;
            if(cnt == 2) return false;
        }
        return true;
    }

    // 3_v1
    public int solution3(String begin, String target, String[] words) {
        // 1. 리스트에 begin 넣기
        List<String> list = new ArrayList<>();
        list.add(begin);
        int idx = 1;
        int targetIdx = -1;
        // 2. target 인덱스 찾기
        for(String w : words) {
            list.add(w);
            if(w.equals(target)) targetIdx = idx;
            idx++;
        }
        // 3. target 이 없으면 0 리턴
        if(targetIdx == -1) return 0;
        //
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[list.size()];
        // 4. 시작 단어 방문 처리
        q.add(0);
        visited[0] = 1;
        //
        while(!q.isEmpty()) {
            int v = q.poll();
            // 5. target 을 찾으면 리턴
            if(v == targetIdx) return visited[v] - 1;
            // 6. 아직 방문하지 않고 바꿀 수 있는 단어 선택하기
            for(int i = 0; i < list.size(); i++) {
                if(visited[i] == 0 && isPossible(list.get(v), list.get(i))) {
                    q.add(i);
                    visited[i] = visited[v] + 1;
                }
            }
        }
        return 0;
    }

    public boolean isPossible(String a, String b) {
        int cnt = 0;
        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) cnt++;
            if(cnt == 2) return false;
        }
        return true;
    }
}