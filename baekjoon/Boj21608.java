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
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        int res = 0;
        Map<Integer, List<Integer>> likeList = new HashMap<>(); //

        for (int k = 0; k < n * n; k++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()); // 학생 번호
            List<Integer> likes = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                likes.add(Integer.parseInt(st.nextToken()));
            }
            likeList.put(num, likes);
            int maxX = -1, maxY = -1;
            int maxLike = 0, maxEmpty = 0;
            int firstX = -1, firstY = -1;
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    // 빈칸이면
                    if (map[x][y] == 0) {
                        if(firstX == -1 && firstY == -1){
                            firstX = x;
                            firstY = y;
                        }
                        int like = 0, empty = 0;
                        // 인접한 4칸 검사
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
                        // 1.
                        if (maxLike < like || (maxLike == like && maxEmpty < empty)) {
                            maxX = x;
                            maxY = y;
                            maxLike = like;
                            maxEmpty = empty;
                        }
                    }
                }
            }
            // like, blank 모두 0인 경우, 최초 발견한 빈칸에 배치하기
            if(maxX == -1 && maxY == -1) {
                maxX = firstX;
                maxY = firstY;
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
