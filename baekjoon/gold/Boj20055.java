package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 컨베이어 벨트 위의 로봇
 * [풀이시간] 1시간 15분
 * [한줄평] 문제를 이해하지 못해서 문제 설명에 대한 힌트를 보고 풀었다. 구현도 생각보다 조금 복잡해서 어려웠던 문제였다.
 * 1_v1. 구현(성공)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/">문제</a>
 * @See <a href="https://hongjuzzang.github.io/solution/code_b20055/">문제 설명</a>
 */
class Boj20055 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] a = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        //
        int cnt = 0;
        int level = 1;  // 단계
        int s = 0;  // 올리는 위치 번호
        int e = n - 1;  // 내리는 위치 번호
        boolean[] robot = new boolean[n];   // i 위치에 로봇이 있는지 여부
        while (true) {
            // 1. 벨트와 로봇이 함께 오른쪽으로 1칸 이동
            robot[n - 1] = false; // 내리는 위치 로봇 바로 내림
            for (int cur = n - 2; cur > 0; cur--) {
                robot[cur] = robot[cur - 1];
            }
            robot[0] = false;   // 올리는 위치는 비어있음
            // 올리는 위치, 내리는 위치 번호 갱신
            s = getPrev(s);
            e = getPrev(e);
            // 2. 가장 먼저 벨트에 올라간 로봇부터 오른쪽으로 1칸씩 이동(이동할 수 있으면)
            robot[n - 1] = false; // 내리는 위치 로봇 바로 내림
            // (n - 2)부터 0까지
            for (int cur = n - 2, next = e; cur >= 0; cur--, next = getPrev(next)) {
                // 현재 칸에 로봇이 없으면 패스
                if(!robot[cur]) continue;
                // 다음 칸에 로봇이 없고 그 칸의 내구도가 1이상이면, 현재 칸의 로봇을 오른쪽으로 1칸 이동
                if(!robot[cur + 1] && a[next] >= 1) {
                    robot[cur] = false;
                    robot[cur + 1] = true;
                    a[next]--;
                    // 내구도가 0이면 카운트
                    if(a[next] == 0) cnt++;
                }
            }
            // 3. 올리는 위치의 내구도가 0이 아니면 로봇 올리기
            if (!robot[0] && a[s] != 0) {
                robot[0] = true;
                a[s]--;
                // 내구도 0이면 카운트
                if (a[s] == 0) cnt++;
            }
            // 4. 내구도가 0인 칸의 개수가 k개 이상이면 종료
            if (cnt >= k) break;
            level++;
        }
        System.out.println(level);
    }

    public static int getPrev(int num) {
        if (num == 0) return 2 * n - 1;
        return num - 1;
    }
}