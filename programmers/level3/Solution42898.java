package programmers.level3;


/**
 * [문제명] 등굣길
 * [풀이시간] 1시간(40분 + 20분)
 * [한줄평] 최단 경로 개수를 찾기 위해 당연하게도 BFS 를 썼는데, 단순하게 DP 로 풀어야 하는 문제였다. 예전에 비슷한 풀어봤었는데 오랜만에 푸니까 어려웠다.
 * 1_v1. DP, BFS(실패- 1, 5 시간초과)
 * 1_v2. DP(성공)
 * [점화식] d[i][j] = d[i-1][j] + d[i][j-1]
 * - d[i][j]: (1, 1)에서 (i, j)까지 가는 최단경로수
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42898">문제</a>
 * @See <a href="https://moonsbeen.tistory.com/75">풀이 참고</a>
 */
class Solution42898 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param m 격자의 가로 길이
     * @param n 격자의 세로 길이
     * @param puddles 물이 잠긴 지역의 좌표를 담은 2차원 배열
     * @return 오른쪽과 아래쪽으로만 움직여 집에서 학교까지 갈 수 있는 최단경로의 개수를 1,000,000,007로 나눈 나머지
     */
    public int solution(int m, int n, int[][] puddles) {
        int mod = 1000000007;
        int[][] map = new int[n + 1][m + 1];
        for(int[] p : puddles) {
            map[p[1]][p[0]] = -1;
        }
        // 원래는 map[1][1] = 1 로 해야하지만 그렇게 하면 아래 반복문에서 map[1][1] = 0 + 0 = 0 으로 값이 덮어쓰여지는 문제가 발생한다.
        map[1][0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                // 현재 좌표가 웅덩이면 갈 수 있는 경로수 = 0 -> 패스
                if(map[i][j] == -1) continue;
                // 왼쪽 좌표가 웅덩이가 아닐 때만 경로수 더하기
                if(map[i - 1][j] != -1) {
                    map[i][j] += map[i - 1][j] % mod;
                }
                // 오른쪽 좌표가 웅덩이가 아닐 때만 경로수 더하기
                if(map[i][j - 1] != -1) {
                    map[i][j] += map[i][j - 1] % mod;
                }
            }
        }
        // for(int i = 1; i <= n; i++) {
        //     for(int j = 1; j <= m; j++) {
        //         System.out.print(map[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        return map[n][m] % 1000000007;
    }
}