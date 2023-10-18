package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [문제명] 한 줄로 서기
 * [풀이시간] 17분
 * [한줄평] 굉장히 단순한 문제였는데 복잡하게 생각하다가 결국 힌트를 보고 해결했던 문제다.
 * 1_v1. 구현(성공)
 * [풀이] 키가 큰 사람부터 리스트의 (자기보다 큰 사람 수) 위치에 넣는다.
 * @See <a href="https://www.acmicpc.net/problem/1138">문제</a>
 * @See <a href="https://dundung.tistory.com/76">풀이 참고</a>
 */
class Boj1138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //
        List<Integer> list = new LinkedList<>();
        for(int i = n - 1; i >= 0; i--) {
            list.add(arr[i], i + 1);
        }
        StringBuilder sb = new StringBuilder();
        for(int num : list) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}