package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2615 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] dx = {0, 1, 1, -1};
        int[] dy = {1, 0, 1, 1};
        // 1: 검은 바둑알, 2: 흰 바둑알, 빈 곳: 0
        int[][] arr = new int[19][19];
        int[][] check = new int[19][19];
        for (int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 승부 결과: 검은색(1), 2: 흰색(2), 무승부(0)
        int res = 0;
        int winX = -1;
        int winY = -1;

        // 1. -> 방향검사
        d1:
        for (int x = 0; x < 19; x++) {
            for (int y = 0; y < 19; y++) {
                if (arr[x][y] != 0) {
                    // 4 방향 검사
                    for (int i = 0; i < 4; i++) {
                        int cnt = 1;    // 연속된 숫자의 개수
                        int target = arr[x][y];
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        // 해당 방향으로 범위 내에서 계속 이동
                        while (0 <= nx && nx < 19 && 0 <= ny && ny < 19 && arr[nx][ny] == target) {
                            cnt++;
                            if(cnt == 5) {
                                int nextX = nx + dx[i];
                                int nextY = ny + dy[i];
                                int prevX = x - dx[i];
                                int prevY = y - dy[i];
                                // 이전 값이 같으면
                                if (0 <= prevX && prevX < 19 && 0 <= prevY && prevY < 19 && arr[prevX][prevY] == target) {
                                    break;
                                }
                                // 다음 값이 같으면
                                if (0 <= nextX && nextX < 19 && 0 <= nextY && nextY < 19 && arr[nextX][nextY] == target) {
                                    break;
                                }
                                // 다음 값 검사
                                res = target;
                                winX = x;
                                winY = y;
                                break d1;
                            }
                            // 현재 방향으로 1칸 더 이동
                            nx += dx[i];
                            ny += dy[i];
                        }
                    }
                }
            }
       }
        // 결과 출력
        System.out.println(res);
        if(res != 0)
            System.out.println((winX + 1) + " " + (winY + 1));
    }
}
