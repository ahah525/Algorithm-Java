package programmers.level2;


/**
 * [문제명] 피로도
 * [풀이시간] 30분 / 16분
 * [한줄평] 입력예시를 보고 완전탐색으로 풀어야겠다는 생각이 들어서 순열을 재귀로 구현하여 문제를 풀었다. / 쉽게 풀 수 있는 기초 문제였다.
 * 1_v1. DFS(성공)
 * - 유저가 탐험할 수 있는 최대 던전수 = n 개에서 r 개를 뽑아 나열할 수 있는 경우의 수들 중 r의 최댓값
 * - r 의 최댓값 = n, 최솟값 = 1 -> 최댓값(n)부터 최솟값(1)까지 for 문으로 탐색하기
 * 2_v1. 완전탐색, DFS(성공) -> 빠름
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/87946">문제</a>
 */
class Solution87946 {
    public static void main(String[] args) {
        // 3
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        System.out.println(solution(80, dungeons));
    }

    static boolean isFind = false;  // n 개에서 r 개를 뽑아 나열할 수 있는 경우의 수를 찾았는지 여부를 나타내는 플래그

    /**
     * @param k 유저의 현재 피로도
     * @param dungeons 각 던전별 "최소 필요 피로도", "소모 피로도"가 담긴 2차원 배열
     * @return 유저가 탐험할수 있는 최대 던전 수
     */
    public static int solution(int k, int[][] dungeons) {
        int n = dungeons.length;
        // 1. n 개에서 r 개를 뽑아 나열할 수 있는 경우의 수를 최초로 찾은 r 값 구하기
        for(int r = n; r >= 1; r--) {
            boolean[] visited = new boolean[n];
            perm(0, visited, k, n, r, dungeons);
            // 플래그값이 true 면 n 개에서 r 개를 뽑아 나열하는 경우의 수를 찾았으므로 바로 r 리턴
            if(isFind)
                return r;
        }
        return 0;
    }

    /**
     * n 개에서 r 개를 뽑아 나열하는 경우의 수 찾기
     * @param depth 방문한 던전 개수(깊이)
     * @param visited 던전 방문여부
     * @param k 유저의 현재 피로도
     * @param n 던전 개수
     * @param r 방문해야하는 던전 개수
     * @param dungeons 각 던전별 "최소 필요 피로도", "소모 피로도"가 담긴 2차원 배열
     */
    public static void perm(int depth, boolean[] visited, int k, int n, int r, int[][] dungeons) {
        // 1. 경우의 수를 찾았으면 바로 종료
        if(isFind) return;
        // 2. n 개에서 r 개를 뽑아 나열하는 경우의 수를 찾았으면, 플래그값 true 로 바꾸고 종료
        if(depth == r) {
            isFind = true;
            return;
        }
        // 3. 아직 방문하지 않았고 유저의 현재 피로도가 던전의 최소 필요 피로도이상의 값인 던전 방문처리
        for(int i = 0; i < n; i++) {
            if(!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                perm(depth + 1, visited, k - dungeons[i][1], n, r, dungeons);
                visited[i] = false;
            }
        }
    }

    // 2_v1
    int answer;
    public int solution2(int k, int[][] dungeons) {
        answer = 0;
        dfs(0, k, new boolean[dungeons.length], dungeons);
        return answer;
    }

    /**
     * @param depth 탐험한 던전 개수
     * @param power 현재 피로도
     * @param visited 던전 탐험 여부
     */
    public void dfs(int depth, int power, boolean[] visited, int[][] dungeons) {
        // 1. 모두 탐험했다면 종료
        if(depth == dungeons.length + 1) return;
        // 2. 탐헌한 던전 최대 개수 업데이트
        answer = Math.max(answer, depth);
        // 3. 아직 탐험하지 않았고 현재 필요도가 최소 필요도 이상이면, 탐험
        for(int i = 0; i < dungeons.length; i++) {
            if(!visited[i] && power >= dungeons[i][0]) {
                visited[i] = true;
                dfs(depth + 1, power - dungeons[i][1], visited, dungeons);
                visited[i] = false;
            }
        }
    }
}