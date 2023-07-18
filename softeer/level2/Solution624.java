package softeer.level2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] [21년 재직자 대회 예선] 전광판
 * [풀이시간] 47분
 * [한줄평] 어렵진 않았으나 각 숫자를 하드코딩하고 두 숫자의 길이가 다를 경우를 고려하느라 시간이 오래걸렸다.
 * 1_v1. 구현(성공)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=624">문제</a>
 */
class Solution624 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int res = 0;
            String a = st.nextToken();
            String b = st.nextToken();
            int diff = Math.abs(a.length() - b.length());
            if(a.length() >= b.length()) {
                for(int j = 0; j < a.length(); j++) {
                    if(j < diff) res += change(a.charAt(j) - '0', 10);
                    else res += change(a.charAt(j) - '0', b.charAt(j - diff) - '0');
                }
            } else {
                for(int j = 0; j < b.length(); j++) {
                    if(j < diff) res += change(10, b.charAt(j) - '0');
                    else res += change(a.charAt(j - diff) - '0', b.charAt(j) - '0');
                }
            }
            System.out.println(res);
        }
    }

    static boolean[][] number = {
            {true, true, true, false, true, true, true},        // 0
            {false, false, true, false, false, true, false},    // 1
            {true, false, true, true, true, false, true},       // 2
            {true, false, true, true, false, true, true},       // 3
            {false, true, true, true, false, true, false},      // 4
            {true, true, false, true, false, true, true},       // 5
            {true, true, false, true, true, true, true},        // 6
            {true, true, true, false, false, true, false},      // 7
            {true, true, true, true, true, true, true},         // 8
            {true, true, true, true, false, true, true},        // 9
            {false, false, false, false, false, false, false}   // 10
    };

    // a -> b 로 만들기 위해서 눌러야하는 최소 횟수
    public static int change(int a, int b) {
        int cnt = 0;
        for(int i = 0; i < 7; i++) {
            if(number[a][i] != number[b][i]) cnt++;
        }
        return cnt;
    }
}