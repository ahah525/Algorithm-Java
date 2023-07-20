package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 스마트 물류
 * [풀이시간] 15분
 * [한줄평] 쉽게 풀 수 있는 그리디 문제였다.
 * 1_v1. 그리디(성공)
 * [풀이] k범위 이내에서 왼->오 방향으로 탐색해서 가장 첫번째 부품 선택하는 것을 반복한다.
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=414">문제</a>
 */
class Solution414 {
    static int n, k;
    static String s;
    static boolean[] visited; // 부품 사용 여부
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        s = br.readLine();
        int cnt = 0;
        visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            // 해당 로봇 위치에서 부품을 가져갈 수 있으면
            if(s.charAt(i) == 'P' && isPossible(i)) cnt++;
        }
        System.out.println(cnt);
    }

    // idx 를 기준으로 양쪽 k 범위 이내에서 부품을 가져올 수 있는지
    public static boolean isPossible(int idx) {
        int l = Math.max(0, idx - k);       // 탐색 시작 위치
        int r = Math.min(n - 1, idx + k);   // 탐색 종료 위치
        // 왼쪽에서 오른쪽 방향으로 차례로 탐색
        for(int i = l; i <= r; i++) {
            if(i == idx) continue;
            if(s.charAt(i) == 'H' && !visited[i]) {
                visited[i] = true;
                return true;
            }
        }
        return false;
    }
}