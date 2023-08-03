package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [문제명] [HSAT 2회 정기 코딩 인증평가 기출] 사물인식 최소 면적 산출 프로그램
 * [풀이시간] (30분)
 * [한줄평]
 * 1_v1. DFS(실패 - 시간초과)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=531">문제</a>
 * @See <a href="https://door-of-tabris.tistory.com/entry/Softeer-%EC%9D%B8%EC%A6%9D%ED%8F%89%EA%B0%802%EC%B0%A8-%EA%B8%B0%EC%B6%9C%EC%82%AC%EB%AC%BC%EC%9D%B8%EC%8B%9D-%EC%B5%9C%EC%86%8C-%EB%A9%B4%EC%A0%81-%EC%82%B0%EC%B6%9C-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8">풀이 참고</a>
 */
class Solution531 {
    static int K;
    static int min;
    static List<int[]>[] points;
    public static void main(String args[]) throws IOException {
        min = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 점 개수
        K = Integer.parseInt(st.nextToken());   // 색깔 개수
        points = new ArrayList[K + 1];
        for(int i = 1; i <= K; i++) {
            points[i] = new ArrayList<>();
        }
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            points[k].add(new int[] {x, y});
        }
        dfs(0, 1000, 1000, -1000, -1000);
        System.out.println(min);
    }

    public static void dfs(int depth, int minX, int minY, int maxX, int maxY) {
        if(depth == K) {
            min = Math.min(min, (maxX - minX) * (maxY - minY));
            return;
        }
        for(int[] p : points[depth + 1]) {
            dfs(depth + 1, Math.min(minX, p[0]), Math.min(minY, p[1]), Math.max(maxX, p[0]), Math.max(maxY, p[1]));
        }
    }
}