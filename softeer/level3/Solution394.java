package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [문제명] 우물 안 개구리
 * [풀이시간] 13분
 * [한줄평] 문제 이해하는데 시간이 좀 걸리긴 했지만 구현은 아주 쉬운 문제였다.
 * 1_v1. 구현(성공)
 * [풀이] 본인이 최고라고 생각하는 사람 = 친분관계가 있는 다른 사람들보다 들 수 있는 무게값이 모두 클 때(하나라도 작거나 같으면 안됨)
 * @Se <a href="https://softeer.ai/practice/info.do?idx=1&eid=394">문제</a>
 */
class Solution394 {
    // 1_v1
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] w = new int[n];
        for(int i = 0; i < n; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }
        // 1. 본인이 최고라고 생각하는지 여부 판단
        boolean[] best = new boolean[n];
        Arrays.fill(best, true);
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if(w[a] > w[b]) {
                best[b] = false;
            } else if(w[a] < w[b]) {
                best[a] = false;
            } else {
                best[a] = false;
                best[b] = false;
            }
        }
        int answer = 0;
        for(boolean isBest : best) {
            if(isBest) answer++;
        }
        System.out.println(answer);
    }
}