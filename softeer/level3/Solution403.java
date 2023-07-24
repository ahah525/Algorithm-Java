package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 조립라인
 * [풀이시간] 1시간 30분(27분 + 1시간 3분)
 * [한줄평] 접근 방법 자체가 틀려서 결국 DP 문제라는 힌트를 보고 해결할 수 있었다.
 * 1_v1. 구현(실패 - 오답)
 * [반례] 첫 번째 작업은 A 혹은 B에서 시작 가능하다.
 * 1_v2. DP(성공)
 * [점화식] dpA[i] = playA[i] + Math.min(dpA[i - 1], dpB[i - 1] + moveB[i - 1])
 * dpA[i]: i번째 작업을 A에서 할 때 조립시간의 최솟값
 * playA[i]: A에서 i번째 작업의 수행 시간
 * moveA[i]: Ai -> Bi+1 작업 이동 시간
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=403">문제</a>
 * @See <a href="https://velog.io/@happyyeon/%EC%86%8C%ED%94%84%ED%8B%B0%EC%96%B4-%EC%A1%B0%EB%A6%BD%EB%9D%BC%EC%9D%B8">풀이 참고</a>
 */
class Solution403 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] playA = new int[n]; // Ai 작업장의 작업시간
        int[] playB = new int[n]; // Bi 작업장의 작업시간
        int[] moveA = new int[n - 1]; // Ai -> Bi+1 작업장으로 이동시간
        int[] moveB = new int[n - 1]; // Bi -> Ai+1 작업장으로 이동시간
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            playA[i] = Integer.parseInt(st.nextToken());
            playB[i] = Integer.parseInt(st.nextToken());
            if(i == n - 1) break;
            moveA[i] = Integer.parseInt(st.nextToken());
            moveB[i] = Integer.parseInt(st.nextToken());
        }
        long[] dpA = new long[n];
        long[] dpB = new long[n];
        dpA[0] = playA[0];  // A에서 조립 시작하는 경우
        dpB[0] = playB[0];  // B에서 조립 시작하는 경우
        for(int i = 1; i < n; i++) {
            // 1. (i-1)번째 작업을 A에서 하고 i번째 작업도 A에서 한 경우
            // 2. (i-1)번째 작업을 B에서 하고, i번째 작업은 A에서 한 경우
            dpA[i] = playA[i] + Math.min(dpA[i - 1], dpB[i - 1] + moveB[i - 1]);
            // 1. (i-1)번째 작업을 B에서 하고 i번째 작업도 B에서 한 경우
            // 2. (i-1)번째 작업을 A에서 하고, i번째 작업은 B에서 한 경우
            dpB[i] = playB[i] + Math.min(dpB[i - 1], dpA[i - 1] + moveA[i - 1]);
        }
        // System.out.println(Arrays.toString(dpA));
        // System.out.println(Arrays.toString(dpB));
        // 마지막 작업을 A에서 끝낸 경우 & B에서 끝낸 경우 중 최솟값
        System.out.println(Math.min(dpA[n - 1], dpB[n - 1]));
    }
}