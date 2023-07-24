package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] [21년 재직자 대회 예선] 좌석 관리
 * [풀이시간] (1시간 35분)
 * [한줄평]
 * 1_v1. 구현(실패 - 시간초과)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=625">문제</a>
 */
class Solution625 {
    static int n, m;
    static boolean[][] visited;
    static Map<Integer, int[]> map;
    static Set<Integer> end;
    static Set<Integer> now;
    public static void main(String args[]) throws IOException {
        end = new HashSet<>(); // 밥을 다 먹은 사원 집합
        now = new HashSet<>(); // 밥을 앉아 먹고 있는 사원 집합
        map = new HashMap<>(); // (id, 좌표)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 행
        m = Integer.parseInt(st.nextToken());   // 열
        visited = new boolean[n][m];
        int q = Integer.parseInt(st.nextToken());
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int id = Integer.parseInt(st.nextToken());
            if(type.equals("In")) {
                if(now.contains(id)) {
                    // 현재 좌석에 앉아 밥을 먹고 있다면
                    System.out.println(id + " already seated.");
                } else if(end.contains(id)) {
                    // 이미 밥을 먹고 떠났다면
                    System.out.println(id + " already ate lunch.");
                } else {
                    if(!seat(id)) {
                        // 빈 자리가 없는 경우
                        System.out.println("There are no more seats.");
                    } else {
                        // 빈 자리가 있는 경우
                        int[] arr = map.get(id);
                        System.out.println(String.format("%d gets the seat (%d, %d).", id, arr[0], arr[1]));
                    }
                }
            } else {
                // Out
                if(!end.contains(id) && !now.contains(id)) {
                    System.out.println(id + " didn't eat lunch.");
                } else if(end.contains(id)) {
                    System.out.println(id + " already left seat.");
                } else if(now.contains(id)) {
                    //
                    int[] arr = map.get(id);
                    now.remove(id);
                    end.add(id);
                    visited[arr[0] - 1][arr[1] - 1] = false;
                    System.out.println(String.format("%d leaves from the seat (%d, %d).", id, arr[0], arr[1]));
                }
            }
        }
    }

    //
    public static boolean seat(int id) {
        double max = 0;    // 가장 높은 안전도
        int x = 0;
        int y = 0;
        // 앉을 수 있는 좌석 중 가장 높은 안전도를 가진 좌석에 앉히기
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!canSeat(i, j)) continue;
                double d = calc(i, j);
                if(max < d) {
                    x = i;
                    y = j;
                    max = d;
                }
            }
        }
        // 좌석 배정에 실패했다면
        if(max == 0) return false;
        // 좌석 배정하기
        visited[x][y] = true;
        now.add(id);
        map.put(id, new int[] {x + 1, y + 1});
        return true;
    }

    // (x, y)의 안전도 계산
    public static double calc(int x, int y) {
        double min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!visited[i][j]) continue;
                min = Math.min(min, Math.sqrt(Math.pow(x - i, 2) + Math.pow(y - j, 2)));
            }
        }
        return min;
    }

    // (x, y) 좌석에 앉을 수 있는지
    public static boolean canSeat(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(0 > nx || nx >= n || 0 > ny || ny >= m) continue;
            if(visited[nx][ny]) return false;
        }
        return true;
    }
}