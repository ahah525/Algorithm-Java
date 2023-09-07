package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 퇴사 2
 * [풀이시간] 40분(35분+5분)
 * [한줄평] 역방향으로 값을 기록한다는 힌트를 보고 풀었던 문제다.
 * 1_v1. DP(실패)
 * [반례] 6,7일의 상담 뿐 아니라 경우에 따라 1일의 상담도 기간 내에 수행하지 못할 수 있음
 * [점화식]
 * 1) i일 상담이 기간 내에 끝나지 않는 경우, d[i] = 0
 * 2) i일 상담이 기간 내에 끝나는 경우, d[i] = Math.max(d[i + 1], p[i] + d[i + t[i]])
 * 1_v2. DP(성공)
 * [해결] i일 상담을 못하는 경우에 (i+1)일의 최댓값을 저장
 * [점화식]
 * 1) i일 상담이 기간 내에 끝나지 않는 경우, d[i] = d[i + 1]
 * 2) i일 상담이 기간 내에 끝나는 경우, d[i] = Math.max(d[i + 1], p[i] + d[i + t[i]])
 * @See <a href="https://www.acmicpc.net/problem/15486">문제</a>
 * @See <a href="https://j2wooooo.tistory.com/42">풀이</a>
 */
class Boj15486 {
    // 1_v1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    //
        int[] t = new int[n + 1];   // 기간
        int[] p = new int[n + 1];   // 이익
        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }
        //
        int[] d = new int[n + 2];   // d[i]: [i, n]일의 범위에서 i일 상담을 선택하거나, 선택하지 않았을 때 최대 이익
        for(int i = n; i >= 1; i--) {
            if(i + t[i] - 1 > n) {
                // i일 상담이 기간 내에 완료하지 못하면, i일 상담은 무조건 선택X
                d[i] = d[i + 1];
            } else {
                // i일 상담을 선택하는 경우 & i일 상담을 선택하지 않는 경우 중 최댓값
                d[i] = Math.max(d[i + 1], p[i] + d[i + t[i]]);
            }
        }
//        System.out.println(Arrays.toString(d));
        System.out.println(d[1]);
    }
}