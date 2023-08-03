package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] [HSAT 4회 정기 코딩 인증평가 기출] 슈퍼컴퓨터 클러스터
 * [풀이시간] 30분(25분+5분)
 * [한줄평] 이분탐색으로 풀어야겠다는 것을 빨리 떠올려서 쉽게 풀 수 있었다.
 * 1_v1. 성공(실패 - 오답)
 * [해결] 이분탐색을 할 때 최댓값을 10^9에서 Integer.MAX_VALUE로 수정함
 * 1_V2. 이분탐색(성공)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=1204">문제</a>
 */
class Solution1204 {
    static long b;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 컴퓨터 수
        b = Long.parseLong(st.nextToken());   // 예산
        int[] power = new int[n]; // 컴퓨터 성능
        st = new StringTokenizer(br.readLine());
        int start = 0;
        int end = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            power[i] = Integer.parseInt(st.nextToken());
            // start = Math.min(start, power[i]);
        }
        // System.out.println(start);
        // System.out.println(end);

        int max = 0;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(isPossible(mid, power)) {
                start = mid + 1;
                max = Math.max(max, mid);
            } else {
                end = mid - 1;
            }
        }
        System.out.println(max);
    }

    public static boolean isPossible(int target, int[] power) {
        Queue<Integer> pq = new PriorityQueue<>();
        for(int p : power) {
            pq.add(p);
        }
        //
        long budget = b;
        while(!pq.isEmpty()) {
            int p = pq.poll();
            int d = target - p;
            if(d <= 0) return true;
            long cost = (long) d * d;
            if(cost > budget) return false;
            budget -= cost;
        }
        return true;
    }
}