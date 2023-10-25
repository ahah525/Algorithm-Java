package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 나무 재테크
 * [풀이시간] 1시간 25분
 * [한줄평]
 * 1_v1. 구현(실패- 43%에서 시간초과)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/16235">문제</a>
 */
class Boj16235 {
    // 1_v1
    static int n;
    public static void main(String[] args) throws IOException {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 땅 크기
        int m = Integer.parseInt(st.nextToken());   // 나무 개수
        int k = Integer.parseInt(st.nextToken());
        int[][] a = new int[n][n];  // 각 칸에 추가되는 양분의 양
        int[][] arr = new int[n][n];  // 각 칸의 양분의 양
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = 5;
            }
        }
        Map<Integer, Queue<Integer>> map = new HashMap<>(); // (칸 번호, 해당 칸에 있는 나무들)
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());   // 나이
            int key = x * n + y;
            if(!map.containsKey(key)) {
                map.put(key, new PriorityQueue<>());
            }
            map.get(key).add(age);
        }
        int cnt = m;
        for(int i = 0; i < k; i++) {
            Map<Integer, Integer> five = new HashMap<>();
            // 1. 나무가 자신의 나이만큼 양분을 먹고, 나이 1 증가
            for(int key : map.keySet()) {
                Queue<Integer> pq = map.get(key);
                int x = key / n;
                int y = key % n;
                int add = 0;
                // 한 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분 먹기
                Queue<Integer> res = new PriorityQueue<>();
                while(!pq.isEmpty()) {
                    int age = pq.poll();   // 가장 나이가 어린 나무의 나이
                    // 자신의 나이만큼 양분을 먹을 수 없다면 죽음
                    if(arr[x][y] < age) {
                        add += age / 2;
                        cnt--;
                        continue;
                    }
                    res.add(age + 1);
                    arr[x][y] -= age;
                    if((age + 1) % 5 == 0) {
                        five.put(key, five.getOrDefault(key, 0) + 1);
                    }
                }
                map.put(key, res);
                // 2. 죽은 나무가 있다면, (죽은 나무 나이 / 2)만큼 해당 칸에 양분 추가
                arr[x][y] += add;
            }
            // 3. 나이가 5의 배수인 나무의 인접 8칸에 나이가 1인 나무 생김
            for(int key : five.keySet()) {
                int x = key / n;
                int y = key % n;
                for(int j = 0; j < 8; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    int nKey = nx * n + ny;
                    if(!inRange(nx, ny)) continue;
                    if(!map.containsKey(nKey)) {
                        map.put(nKey, new PriorityQueue<>());
                    }
                    for(int p = 0; p < five.get(key); p++) {
                        map.get(nKey).add(1);
                        cnt++;
                    }
                }
            }
            // 4. 각 칸에 a[x][y]만큼 양분 추가
            for(int x = 0; x < n; x++) {
                for(int y = 0; y < n; y++) {
                    arr[x][y] += a[x][y];
                }

            }
        }
        System.out.println(cnt);
    }

    public static boolean inRange(int x, int y) {
        if(0 <= x && x < n && 0 <= y && y < n) return true;
        return false;
    }
}