package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [문제명] 감소하는 수
 * [풀이시간] 46분
 * [한줄평] DFS로 전체에서 n번째 값인지를 어떻게 알 수 있는지를 몰라서 결국 결국 풀이 힌트를 보고 해결했던 문제다. 생각보다 쉬운 문제였는데 너무 오래걸린 것 같다.
 * 1_v1. 완전탐색, 백트래킹(성공)
* [풀이] n의 값과 상관없이 DFS로 모든 감소하는 수를 먼저 구한다.
 * 2_v1. 완전탐색, DFS, 백트래킹(성공)
 * [풀이] 1_v1 동일
 * @See <a href="https://www.acmicpc.net/problem/1038">문제</a>
 */
class Boj1038 {
    // 1_v1, 2_v1
    static List<Long> list; // 감소하는 수의 최댓값 = 9876543210(int 범위를 넘기기 때문에 long으로)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        list = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        // 1. DFS로 0 ~ 9 로 시작하는 모든 감소하는 수를 리스트에 저장
        for(int i = 0; i <= 9; i++) {
            dfs(i, i - 1);
        }
        if(list.size() <= n) {
            // 2. n번째 수를 구할 수 없으면 -1 출력
            System.out.println(-1);
        } else {
            // 3. 리스트 오름차순 정렬 후 n번째 수 출력
            Collections.sort(list);
            System.out.println(list.get(n));
        }
    }

    public static void dfs(long num, int start) {
        list.add(num);
        for(int i = start; i >= 0; i--) {
            dfs(num * 10 + i, i - 1);
        }
    }
}