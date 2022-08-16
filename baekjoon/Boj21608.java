package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj21608 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 교실 크기
        StringTokenizer st;
        int[][] map = new int[n][n];    // 교실
        // 상하좌우(인접한 4칸)
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        int res = 0;    // 만족도 총합
        // (학생 번호, 좋아하는 학생의 번호 리스트)
        Map<Integer, List<Integer>> likeList = new HashMap<>();
        // 입력받은 후에 바로 배치
        for (int k = 0; k < n * n; k++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()); // 학생 번호
            List<Integer> likes = new ArrayList<>();    // 좋아하는 학생 번호
            for (int i = 0; i < 4; i++) {
                likes.add(Integer.parseInt(st.nextToken()));
            }
            // map에 넣기(배치 후에 만족도 계산을 위해)
            likeList.put(num, likes);

            int maxX = -1, maxY = -1;
            // 0으로 초기화해놓을 경우, 둘다 0인 값을 가질 때 maxX, maxY 업데이트 안됨
            int maxLike = -1, maxEmpty = -1;

            // 모든 칸에 대해 검사
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    // 빈칸이면 검사
                    if (map[x][y] == 0) {
                        // 인접한 칸 중 좋아하는 학생 수, 빈칸 수
                        int like = 0, empty = 0;
                        // 현재 칸 기준 인접한 칸들 검사하여 좋아하는 학생수, 빈칸 수 구함
                        for (int i = 0; i < 4; i++) {
                            int nx = x + dx[i];
                            int ny = y + dy[i];
                            // 범위 내 좌표이면
                            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                                // 빈칸 개수 세기
                                if (map[nx][ny] == 0) {
                                    empty++;
                                }
                                // 좋아하는 학생 세기
                                if (likes.contains(map[nx][ny])) {
                                    like++;
                                }
                            }
                        }
                        // 1번, 2번 조건에 해당하는 최선의 자리 업데이트(3번 조건은 굳이 검사할 필요X)
                        if (maxLike < like || (maxLike == like && maxEmpty < empty)) {
                            maxX = x;
                            maxY = y;
                            maxLike = like;
                            maxEmpty = empty;
                        }
                    }
                }
            }
            // 배치하기
            map[maxX][maxY] = num;
        }
        // 만족도 조사
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                int cnt = 0;    // 좋아하는 학생 수
                int cur = map[x][y];    // 현재 좌표 값
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                        int near = map[nx][ny]; // 인접 좌표 값
                        if (likeList.get(cur).contains(near)) {
                            cnt++;
                        }
                    }
                }
                res += Math.pow(10, cnt - 1);
            }
        }
        System.out.println(res);
    }
}
