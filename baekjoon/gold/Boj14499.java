package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 주사위 굴리기
 * [풀이시간] 45분
 * [한줄평] 4가지 방향으로 굴렸을 때 각 면의 숫자가 어떻게 달라지는지 구해야했기 때문에 구현하는데 시간이 좀 오래결렸던 것 같다.
 * 1_v1. 구현(성공)
 * [풀이] 각 면(0: 위, 1: 뒤, 2: 오, 3: 왼, 4: 앞, 5: 아래)의 숫자를 dice[]에 저장하고 방향대로 굴릴 때 마다 각 면의 값이 어떻게 변하는지 갱신한다.
 * @See <a href="https://www.acmicpc.net/problem/14499">문제</a>
 */
class Boj14499 {
    static int n, m;
    static int[][] map;
    static int[] dice;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 세로
        m = Integer.parseInt(st.nextToken());   // 가로
        int x = Integer.parseInt(st.nextToken());   // 주사위
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());   // 명령 개수
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        dice = new int[6];  // 각 면(0: 위, 1: 뒤, 2: 오, 3: 왼, 4: 앞, 5: 아래)의 숫자
        // 1. 이동한 칸이 0이면, 주사위 바닥 숫자가 칸에 복사됨
        // 2. 이동한 칸이 0이아니면, 칸 숫자가 주사위 바닥에 복사됨, 칸은 0
        for(int i = 0; i < k; i++) {
            int d = Integer.parseInt(st.nextToken());
            int[] tmp = new int[6];
            switch(d) {
                case 1: {
                    // 오른쪽 이동
                    if(!inRange(x, y + 1)) continue;
                    tmp[0] = dice[3];
                    tmp[1] = dice[1];
                    tmp[2] = dice[0];
                    tmp[3] = dice[5];
                    tmp[4] = dice[4];
                    tmp[5] = dice[2];
                    if(map[x][y + 1] == 0) {
                        map[x][y + 1] = tmp[5];
                    } else {
                        tmp[5] = map[x][y + 1];
                        map[x][y + 1] = 0;
                    }
                    y++;
                    break;
                }
                case 2: {
                    // 왼쪽 이동
                    if(!inRange(x, y - 1)) continue;
                    tmp[0] = dice[2];
                    tmp[1] = dice[1];
                    tmp[2] = dice[5];
                    tmp[3] = dice[0];
                    tmp[4] = dice[4];
                    tmp[5] = dice[3];
                    if(map[x][y - 1] == 0) {
                        map[x][y - 1] = tmp[5];
                    } else {
                        tmp[5] = map[x][y - 1];
                        map[x][y - 1] = 0;
                    }
                    y--;
                    break;
                }
                case 3: {
                    // 위로 이동
                    if(!inRange(x - 1, y)) continue;
                    tmp[0] = dice[4];
                    tmp[1] = dice[0];
                    tmp[2] = dice[2];
                    tmp[3] = dice[3];
                    tmp[4] = dice[5];
                    tmp[5] = dice[1];
                    if(map[x - 1][y] == 0) {
                        map[x - 1][y] = tmp[5];
                    } else {
                        tmp[5] = map[x - 1][y];
                        map[x - 1][y] = 0;
                    }
                    x--;
                    break;
                }
                case 4: {
                    // 아래로 이동
                    if(!inRange(x + 1, y)) continue;
                    tmp[0] = dice[1];
                    tmp[1] = dice[5];
                    tmp[2] = dice[2];
                    tmp[3] = dice[3];
                    tmp[4] = dice[0];
                    tmp[5] = dice[4];
                    if(map[x + 1][y] == 0) {
                        map[x + 1][y] = tmp[5];
                    } else {
                        tmp[5] = map[x + 1][y];
                        map[x + 1][y] = 0;
                    }
                    x++;
                    break;
                }
            }
            dice = tmp;
            System.out.println(dice[0]);
        }
    }

    public static boolean inRange(int x, int y) {
        if(0 <= x && x < n && 0 <= y && y < m) return true;
        return false;
    }
}