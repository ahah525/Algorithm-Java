package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 반복수열
 * [풀이시간] 9분
 * [한줄평] 너무 쉬워서 다시 풀 필요 없는 문제다.
 * 1_v1. 구현(성공)
 * [풀이] HashMap에 (숫자, 인덱스)를 기록하고 중복된 값이 나올 때 해당 숫자가 위치한 인덱스가 정답이다.
 * @See <a href="https://www.acmicpc.net/problem/2331">문제</a>
 */
class Boj2331 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        Map<Integer, Integer> map = new HashMap<>();
        int num = a;
        int i = 0;
        int cnt = 0;
        while(true) {
            if(map.containsKey(num)) {
                cnt = map.get(num);
                break;
            }
            map.put(num, i++);
            num = calc(num, p);
        }
        System.out.println(cnt);
    }

    public static int calc(int num, int p) {
        int res = 0;
        while(num != 0) {
            res += Math.pow(num % 10, p);
            num /= 10;
        }
        return res;
    }
}