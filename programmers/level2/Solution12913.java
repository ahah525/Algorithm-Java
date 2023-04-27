package programmers.level2;


/**
 * [문제명] 땅따먹기
 * [풀이시간] 30분 / 7분
 * [한줄평] 보자마자 DP 로 풀어야겠다고 생각이 들었던 문제였는데, 알수없는 런타임에러 문제로 시간을 많이 허비했다.. / 기초 DP 문제였다.
 * 1_v1. DP(성공)
 * [점화식] d[i][j] = land[i][j] + Math.max(d[i - 1][k]) (단 k != j)
 * - d[i][j]: i행에서 j열을 선택했을 때 점수의 최댓값
 * 2_v1. DP(성공)
 * [점화식] land[i][j] += Math.max(d[i-1][k]) (k!=j)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12913">문제</a>
 */
class Solution12913 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    /**
     * @param land 땅따먹기 게임의 땅
     * @return 마지막 행까지 모두 내려왔을 때, 얻을 수 있는 점수의 최대값
     */
    public static int solution(int[][] land) {
        int n = land.length;
        int[][] d = new int[n][4];
        // 1. 0행 초기화
        for(int i = 0; i < 4; i++) {
            d[0][i] = land[0][i];
        }
        // 2. 1 ~ (n - 1) 행 계산
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < 4; j++) {
                int max = -1;
                for(int k = 0; k < 4; k++) {
                    // 이전에 선택했던 열은 이번 턴에서 선택할 수 없음
                    if(j == k) continue;
                    max = Math.max(max, d[i - 1][k]);
                }
                d[i][j] = land[i][j] + max;
            }
        }
        // 3. 마지막 행에서 최댓값 구하기
        int answer = 0;
        for(int i = 0; i < 4; i++) {
            answer = Math.max(answer, d[n - 1][i]);
        }
        return answer;
    }

    // 2_v1
    public int solution2(int[][] land) {
        int answer = 0;
        for(int i = 1; i < land.length; i++) {
            for(int j = 0; j < 4; j++) {
                int max = 0;
                for(int k = 0; k < 4; k++) {
                    if(j == k) continue;
                    max = Math.max(max, land[i - 1][k]);
                }
                land[i][j] += max;
            }
        }
        for(int i = 0; i < 4; i++) {
            answer = Math.max(answer, land[land.length - 1][i]);
        }
        return answer;
    }
}