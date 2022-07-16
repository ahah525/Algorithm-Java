package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj21314 {
    static String s;    // 민겸수
    static StringBuilder max;  // 최댓값
    static StringBuilder min;  // 최솟값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        max = new StringBuilder();    // 최댓값
        min = new StringBuilder();    // 최솟값
        s = br.readLine();

        findMax();
        findMin();

        System.out.println(max);
        System.out.println(min);
    }
    static public void findMax() {
        int m = 0;  // M의 개수
        for (int i = 0; i < s.length(); i++) {
            // K뒤에서 자르기
            if (s.charAt(i) == 'K') {
                max.append(5);
                for (int j = 0; j < m; j++) {
                    max.append(0);
                }
                m = 0;
                continue;
            }
            m++;
        }
        // m이 남아있는 경우 처리
        if (m > 0) {
            // m 모두 쪼개기
            for (int i = 0; i < m; i++) {
                max.append(1);
            }
        }
    }
    static public void findMin() {
        int m = 0;  // m의 개수
        for (int i = 0; i < s.length(); i++) {
            // K 앞, 뒤에서 자르기
            if (s.charAt(i) == 'K') {
                if(m > 0) {
                    min.append(1);
                    for (int j = 0; j < m - 1; j++) {
                        min.append(0);
                    }
                }
                min.append(5);
                m = 0;
                continue;
            }
            m++;
        }
        // m이 남아있는 경우 처리
        if (m > 0) {
            // m 쪼개지 않고 원본 유지
            min.append(1);
            for (int i = 0; i < m - 1; i++) {
                min.append(0);
            }
        }
    }
}
