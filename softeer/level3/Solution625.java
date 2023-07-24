package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] [21년 재직자 대회 예선] 좌석 관리
 * [풀이시간] 1시간 50분(1시간 35분 + 15분)
 * [한줄평] 처음에 문제 이해를 잘못해서 푸는데 오래걸렸다. 시간초과를 해결을 하지 못해서 결국 풀이를 참고했다.
 * 1_v1. 완전탐색, 구현(실패 - 시간초과)
 * [해결] 안전도를 계산할 때 Math.sqrt(?)로 계산하지 않고 그냥 ?값 으로만 계산하고 비교한다.
 * 1_v2. 완전탐색, 구현(성공)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=625">문제</a>
 * @See <a href="https://velog.io/@yibangwon/Softeer-21%EB%85%84-%EC%9E%AC%EC%A7%81%EC%9E%90-%EB%8C%80%ED%9A%8C-%EC%98%88%EC%84%A0-%EC%A2%8C%EC%84%9D-%EA%B4%80%EB%A6%AC-%EB%82%9C%EC%9D%B4%EB%8F%84-3">풀이 참고</a>
 */
class Solution625 {
    static int n, m;    // 행, 열
    static boolean[][] visited;     // [x, y] 에 앉을 수 있는지 여부
    static Map<Integer, int[]> map; // (id, 해당 사원이 식사한 좌표[x,y])
    static Set<Integer> end;    // 식사 완료한 사원 id 집합
    static Set<Integer> now;    // 식사 중인 사원 id 집합
    public static void main(String args[]) throws IOException {
        end = new HashSet<>();
        now = new HashSet<>();
        map = new HashMap<>();
        // 입렵 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        visited = new boolean[n][m];
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int id = Integer.parseInt(st.nextToken());
            if(type.equals("In")) {
                // In
                if(now.contains(id)) {
                    // 현재 식사 중인 경우
                    System.out.println(id + " already seated.");
                } else if(end.contains(id)) {
                    // 이미 식사 완료한 경우
                    System.out.println(id + " already ate lunch.");
                } else {
                    if(!seat(id)) {
                        // 앉을 수 있는 자리가 없는 경우
                        System.out.println("There are no more seats.");
                    } else {
                        // 앉을 수 있는 자리가 있는 경우
                        int[] arr = map.get(id);
                        System.out.println(String.format("%d gets the seat (%d, %d).", id, arr[0] + 1, arr[1] + 1));
                    }
                }
            } else {
                // Out
                if(end.contains(id)) {
                    // 이미 식사 완료한 경우
                    System.out.println(id + " already left seat.");
                } else if(now.contains(id)) {
                    // 현재 식사 중인 경우
                    int[] arr = map.get(id);
                    now.remove(id);
                    end.add(id);
                    visited[arr[0]][arr[1]] = false;
                    System.out.println(String.format("%d leaves from the seat (%d, %d).", id, arr[0] + 1, arr[1] + 1));
                } else {
                    // 아직 식사하지 않은 경우
                    System.out.println(id + " didn't eat lunch.");
                }
            }
        }
    }

    // id 회원에게 좌석 배정하기
    public static boolean seat(int id) {
        int max = 0;    // 가장 높은 안전도
        int x = 0;
        int y = 0;
        // 앉을 수 있는 좌석 중 가장 높은 안전도를 가진 좌석에 앉히기
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!canSeat(i, j)) continue;
                int d = calc(i, j);
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
        map.put(id, new int[] {x, y});
        return true;
    }

    // (x, y)의 안전도 계산
    public static int calc(int x, int y) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!visited[i][j]) continue;
                min = Math.min(min, (x - i) * (x - i) + (y - j) * (y - j));
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