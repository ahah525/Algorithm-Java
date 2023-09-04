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
 * @See <a href="https://www.acmicpc.net/problem/1038">문제</a>
 */
class Boj1038 {
    static List<Long> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        // 1. DFS로 0 ~ 9 로 시작하는 모든 감소하는 수를 리스트에 저장
        dfs(0, 10, 0);
        // 2. 리스트 오름차순 정렬
        Collections.sort(list);
        long res = -1;
        // 3. n번째 수를 구할 수 없으면 -1
        if(n < list.size()) res = list.get(n);
        System.out.println(res);
    }

    public static void dfs(int depth, int prev, long num) {
        for(int i = 0; i < prev; i++) {
            list.add(num * 10 + i);
            dfs(depth + 1, i, num * 10 + i);
        }
    }
}