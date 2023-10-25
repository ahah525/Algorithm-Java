package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 나무 재테크
 * [풀이시간] 2시간(1시간 25분+35분)
 * [한줄평] 그대로 구현하는 문제였는데 자료구조를 잘못 써서 시간초과가 났고 결국 풀이를 보고 문제를 해결했다.
 * 1_v1. 구현(실패- 43%에서 시간초과)
 * 1_v2. 구현(성공)
 * [풀이]
 * - PriorityQueue<Tree> liveQ : 살아있는 나무를 저장하는 큐(나이 오름차순 정렬)
 * - PriorityQueue<Tree> tmpQ : 살아있는 나무를 임시 저장하는 큐(나이 오름차순 정렬)
 * - ArrayList<Tree> dies : 죽은 나무 리스트
 * @See <a href="https://www.acmicpc.net/problem/16235">문제</a>
 * @See <a href="https://excited-hyun.tistory.com/223">풀이</a>
 */
class Boj16235 {
    // 1_v2
    static class Tree {
        int x;
        int y;
        int age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }
    static int n;
    public static void main(String[] args) throws IOException {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 땅 크기
        int m = Integer.parseInt(st.nextToken());   // 나무 개수
        int k = Integer.parseInt(st.nextToken());
        // 1. 각 칸에 추가되는 양분의 양과 현재 양분의 양 초기화
        int[][] a = new int[n][n];  // 각 칸에 추가되는 양분의 양
        int[][] arr = new int[n][n];  // 각 칸의 양분의 양
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = 5;
            }
        }
        // 2. 나무 초기화
        Queue<Tree> liveQ = new PriorityQueue<>((o1, o2) -> o1.age - o2.age); // 나이가 어린 나무부터 양분을 먹기 위해 나이 기준 오름차순 정렬
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            liveQ.add(new Tree(x, y, age));
        }
        // 3. k번 반복
        for (int i = 0; i < k; i++) {
            // 4. 봄
            Queue<Tree> tmpQ = new PriorityQueue<>((o1, o2) -> o1.age - o2.age);
            List<Tree> dies = new ArrayList<>();
            while (!liveQ.isEmpty()) {
                Tree tree = liveQ.poll();
                // 자신의 나이만큼 양분을 먹을 수 없다면 죽음
                if (arr[tree.x][tree.y] < tree.age) {
                    dies.add(tree);
                    continue;
                }
                // 자신의 나이만큼 양분을 먹고, 나이 1증가
                arr[tree.x][tree.y] -= tree.age;
                tree.age++;
                tmpQ.add(tree);
            }
            liveQ = new PriorityQueue<>(tmpQ);
            // 5. 여름
            for (Tree tree : dies) {
                // 죽은 나무들의 위치에 (나이 / 2)만큼 양분 추가
                arr[tree.x][tree.y] += tree.age / 2;
            }
            // 6. 가을
            for (Tree tree : tmpQ) {
                // 나이가 5의 배수인 나무의 인접 8칸에 나이가 1인 나무 생김
                if (tree.age % 5 != 0) continue;
                for (int j = 0; j < 8; j++) {
                    int nx = tree.x + dx[j];
                    int ny = tree.y + dy[j];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                    liveQ.add(new Tree(nx, ny, 1));
                }
            }
            // 7. 겨울
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    // 각 칸에 a[x][y]만큼 양분 추가
                    arr[x][y] += a[x][y];
                }
            }
        }
        System.out.println(liveQ.size());
    }
}