package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 행렬
 * [풀이시간] 30분
 * [한줄평] 처음에 완전탐색 문제인줄 알았는데 그리디로 풀어야 한다는 힌트를 보고 풀었다. 다시 한 번 풀어봐도 좋을 문제다.
 * 1_v1. 그리디(성공)
 * [풀이] 왼쪽 위에서부터 오른쪽 아래로 A와 B 행렬의 값이 다르면 뒤집는다.
 * @See <a href="https://www.acmicpc.net/problem/1080">문제</a>
 * @See <a href="https://lotuslee.tistory.com/75">풀이</a>
 */
class Boj1080 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] a = new char[n][m];
        char[][] b = new char[n][m];
        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            for(int j = 0; j < m; j++) {
                a[i][j] = s.charAt(j);
            }
        }
        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            for(int j = 0; j < m; j++) {
                b[i][j] = s.charAt(j);
            }
        }
        //
        int[] dx = {0, 0, 0, 1, 1, 1, 2, 2, 2};
        int[] dy = {0, 1, 2, 0, 1, 2, 0, 1, 2};
        int cnt = 0;
        for(int i = 0; i + 2 < n; i++) {
            for (int j = 0; j + 2 < m; j++) {
                // 1. a와 b의 값이 같으면 패스
                if (a[i][j] == b[i][j]) continue;
                // 2. 다르면 (i,j)좌표 기준 3 * 3 뒤집기
                for (int k = 0; k < 9; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (a[x][y] == '0') a[x][y] = '1';
                    else a[x][y] = '0';
                }
                cnt++;
            }
        }
        // 3. a -> b 될 수 있는지 확인
        l1:
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(a[i][j] != b[i][j]) {
                    cnt = -1;
                    break l1;
                }
            }
        }
        System.out.println(cnt);
    }
}