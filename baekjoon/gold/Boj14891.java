package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 톱니바퀴
 * [풀이시간] 50분
 * [한줄평] 어려운 문제는 아니었지만 푸는데 시간이 조금 오래걸렸다. 좋은 구현 문제로 다시 풀어봐도 좋을 것 같다.
 * 1_v1. 구현(성공)
 * [풀이] 먼저 각 톱니바퀴의 회전 방향을 구하고, 결과를 갱신한다.
 * @See <a href="https://www.acmicpc.net/problem/14891">문제</a>
 */
class Boj14891 {
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 0: N극, 1: S극
        arr = new int[4][8];
        for(int i = 0; i < 4; i++) {
            String s = br.readLine();
            for(int j = 0; j < s.length(); j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        int k = Integer.parseInt(br.readLine());    // 회전 횟수
        StringTokenizer st;
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()) - 1;   // 번호
            int d = Integer.parseInt(st.nextToken());       // 회전 방향
            // 1. 현재 톱니바퀴를 회전했을 때 다른 톱니바퀴들의 회전 방향 구하기
            int[] turn = check(n, d);
            // 2. 각 톱니바퀴 회전 결과 갱신
            for(int j = 0; j < 4; j++) {
                if(turn[j] == 0) continue;
                if(turn[j] == 1) turnRight(j);
                else turnLeft(j);
            }
        }
        // 3. 톱니바퀴 점수의 합 계산
        int sum = 0;
        for(int i = 0, s = 1; i < 4; i++, s *= 2) {
            sum += arr[i][0] * s;
//            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println(sum);
    }

    // n번째 톱니바퀴를 d 방향으로 회전했을 때, 모든 톱니바퀴의 회전 방향 조회
    public static int[] check(int n, int d) {
        int[] turn = new int[4];    // 각 톱니바퀴의 회전 방향(0: 회전X, 1: 시계 방향 회전, -1: 반시계 방향 회전)
        // 1. n번째 톱니바퀴 회전방향 = d
        turn[n] = d;
        // 2. n번째 톱니바퀴 기준으로 왼쪽에 있는 톱니바퀴 회전 방향 검사
        for(int j = n - 1; j >= 0; j--) {
            // 1. 이전 톱니바퀴가 회전하지 않았거나 같은 극이면, j번째 톱니바퀴 회전X
            if(turn[j + 1] == 0 || arr[j][2] == arr[j + 1][6]) break;
            // 2. 다른 극이면, j번째 톱니바퀴는 (j+1)번째 톱니바퀴 회전 방향의 반대로 회전
            turn[j] = -turn[j + 1];
        }
        // 3. n번째 톱니바퀴 기준으로 왼쪽에 있는 톱니바퀴 회전 방향 검사
        for(int j = n + 1; j < 4; j++) {
            // 1. 이전 톱니바퀴가 회전하지 않았거나 같은 극이면, j번째 톱니바퀴 회전X
            if(turn[j - 1] == 0 || arr[j][6] == arr[j - 1][2]) break;
            // 2. 다른 극이면, j번째 톱니바퀴는 (j+1)번째 톱니바퀴 회전 방향의 반대로 회전
            turn[j] = -turn[j - 1];
        }
        return turn;
    }

    // 반시계 방향으로 회전
    public static void turnLeft(int n) {
        int tmp = arr[n][0];
        for(int i = 0; i < 7; i++) {
            arr[n][i] = arr[n][i + 1];
        }
        arr[n][7] = tmp;
    }

    // 시계 방향으로 회전
    public static void turnRight(int n) {
        int tmp = arr[n][7];
        for(int i = 7; i > 0; i--) {
            arr[n][i] = arr[n][i - 1];
        }
        arr[n][0] = tmp;
    }
}